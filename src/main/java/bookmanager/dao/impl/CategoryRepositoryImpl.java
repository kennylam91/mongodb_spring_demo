package bookmanager.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import bookmanager.dao.CategoryRepository;
import bookmanager.model.Category;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Category saveOrUpdate(Category category) {
		return mongoTemplate.save(category, "category");
	}

	@Override
	public List<Category> findAll() {
		return mongoTemplate.findAll(Category.class);
	}
	
	
	
	

	
}
