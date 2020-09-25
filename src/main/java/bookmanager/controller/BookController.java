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

import bookmanager.dao.BookRepository;
import bookmanager.dto.BookDetailDto;
import bookmanager.form.BookForm;
import bookmanager.form.WriterForm;
import bookmanager.model.Book;
import bookmanager.model.Writer;
import bookmanager.service.SequenceGeneratorService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	
	@PostMapping
	public ResponseEntity<List<Book>> createOrUpdate(@RequestBody List<Book> books){
		try {
			for (Book book : books) {
				if(book.getBookId() == null) {
					book.setBookId(sequenceGeneratorService.generateSequence(Book.SEQUENCE_NAME));
				}
			}
			return ResponseEntity.ok(bookRepository.saveAll(books));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/get-all")
	public ResponseEntity<Page<Book>> findAll(@RequestBody BookForm form) {
		Pageable pageable = PageRequest.of(form.getPage() - 1, form.getLimit(),
				Sort.by(Sort.Direction.ASC, "title"));
		Page<Book> bookPage = bookRepository.findAll(pageable);
		return ResponseEntity.ok(bookPage);
	}
	
	@PostMapping("/get-details")
	public ResponseEntity<List<BookDetailDto>> findAllDetail(@RequestBody BookForm form) {
		Pageable pageable = PageRequest.of(form.getPage() - 1, form.getLimit(),
				Sort.by(Sort.Direction.ASC, "title"));
		List<BookDetailDto> bookPage = bookRepository.findBookDetails(pageable);
		return ResponseEntity.ok(bookPage);
	}
}
