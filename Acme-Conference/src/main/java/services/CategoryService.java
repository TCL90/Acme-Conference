
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Category;
import domain.Conference;
import domain.Comment;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository	categoryRepository;

	@Autowired
	private ConferenceService	conferenceService;


	public boolean checkAdmin() {
		final Authority a = new Authority();
		a.setAuthority(Authority.ADMIN);
		final UserAccount user = LoginService.getPrincipal();
		return user.getAuthorities().contains(a);
	}

	public List<Category> findAll() {
		return this.categoryRepository.findAll();
	}
	
	public Category save(final Category c) {

		return this.categoryRepository.save(c);
	}

	//	public Collection<Category> findAll() {
	//		return this.categoryRepository.findAll();
	//	}

	public Category findOne(final int id) {
		return this.categoryRepository.findOne(id);
	}

	public Category create() {
		this.checkAdmin();
		return new Category();
	}

	public Category save(final Category category) {
		Assert.isTrue(this.checkAdmin());
		Assert.notNull(category);
		Assert.isTrue(!category.getTitleIng().contains("CONFERENCE"), "hackingTry");
		Assert.isTrue(!category.getTitleIng().contains(category.getParent().getTitleIng()), "hackingTry");
		return this.categoryRepository.save(category);
	}

	public void delete(final Category category) {
		Assert.isTrue(this.checkAdmin());
		Assert.notNull(category);
		Assert.isTrue(!category.getTitleIng().contains("CONFERENCE"));
		final List<Conference> conferences = this.conferenceService.conferencesByCategory(category);
		for (final Conference c : conferences)
			c.setCategory(category.getParent());
		//final Conference cSaved = this.conferenceService.save(c);
		final List<Category> childs = this.findChilds(category);
		for (final Category c : childs)
			c.setParent(category.getParent());
		//final Category catSaved = this.save(c);
		this.categoryRepository.delete(category);
	}
	public List<Category> findChilds(final Category parent) {
		return this.categoryRepository.findChilds(parent);
	}
}
