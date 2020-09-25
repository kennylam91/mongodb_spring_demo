package bookmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookmanager.dao.CategoryRepository;
import bookmanager.model.Category;
import bookmanager.service.SequenceGeneratorService;

@RestController
@RequestMapping("categories")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@PostMapping
	public ResponseEntity<Category> saveOrUpdate(@RequestBody Category category){
		try {
			if(category.getCategoryId() == null) {
				category.setCategoryId(sequenceGeneratorService.generateSequence(Category.SEQUENCE_NAME));
			}
			Category savedCategory = categoryRepository.saveOrUpdate(category);
			return ResponseEntity.ok(savedCategory);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> fetchAll(){
		List<Category> list = categoryRepository.findAll();
		return ResponseEntity.ok(list);
	}
}
