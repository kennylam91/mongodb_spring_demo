package bookmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookmanager.dao.WriterRepository;
import bookmanager.form.WriterForm;
import bookmanager.model.Writer;
import bookmanager.service.SequenceGeneratorService;

@RestController
@RequestMapping("/writers")
public class WriterController {

	@Autowired
	private WriterRepository writerRepository;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@PostMapping
	public ResponseEntity<List<Writer>> saveOrUpdate(@RequestBody List<Writer> writes) {

		try {
			for (Writer writer : writes) {
				if (writer.getWriterId() == null) {
					writer.setWriterId(sequenceGeneratorService.generateSequence(Writer.SEQUENCE_NAME));
				}
			}
			return ResponseEntity.ok(writerRepository.saveAll(writes));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PostMapping("/get-all")
	public ResponseEntity<Page<Writer>> findAll(@RequestBody WriterForm writerForm) {
		Pageable pageable = PageRequest.of(writerForm.getPage() - 1, writerForm.getLimit(),
				Sort.by(Sort.Direction.ASC, "writerName"));
		Page<Writer> writerPage = writerRepository.findByWriterNameContainingAndCountry(writerForm.getWriterName(),
				writerForm.getCountry(), pageable);
		return ResponseEntity.ok(writerPage);
	}

}
