
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CategoryRepository;
import domain.Category;
import domain.Comment;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository	categoryRepository;


	public List<Category> findAll() {
		return this.categoryRepository.findAll();
	}
	
	public Category save(final Category c) {

		return this.categoryRepository.save(c);
	}

	//	public Collection<Category> findAll() {
	//		return this.categoryRepository.findAll();
	//	}
}
