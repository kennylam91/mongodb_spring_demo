package bookmanager.dao;


import java.util.List;

import bookmanager.model.Category;

public interface CategoryRepository{
	
	Category saveOrUpdate(Category category);
	
	List<Category> findAll();
}
