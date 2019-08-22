
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CategoryRepository;
import domain.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired
	CategoryRepository	categoryRepository;


	public List<Category> findAll() {
		return this.categoryRepository.findAll();
	}
}
