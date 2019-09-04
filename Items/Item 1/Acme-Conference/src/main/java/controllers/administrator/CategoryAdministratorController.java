
package controllers.administrator;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.CategoryService;
import controllers.AbstractController;
import domain.Category;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController extends AbstractController {

	@Autowired
	AdministratorService	adminService;

	@Autowired
	CategoryService			categoryService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;

		final List<Category> categories = this.categoryService.findAll();
		result = new ModelAndView("category/administrator/list");
		result.addObject("categories", categories);
		result.addObject("requestURI", "/category/administrator/list.do");
		return result;
	}

	protected ModelAndView createEditModelAndView(final Category category) {

		ModelAndView result;
		result = this.createEditModelAndView(category, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Category category, final String message) {
		ModelAndView result;
		result = new ModelAndView("category/administrator/edit");
		result.addObject("category", category);
		final List<Category> parents = this.categoryService.findAll();
		parents.remove(category);
		result.addObject("parents", parents);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		final Category category = this.categoryService.create();
		result = this.createEditModelAndView(category);
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryId) {
		final ModelAndView result;
		final Category category = this.categoryService.findOne(categoryId);
		Assert.isTrue(!category.getTitleIng().contains("CONFERENCE"));
		result = this.createEditModelAndView(category);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Category category, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(category);
		else
			try {
				final Category saved = this.categoryService.save(category);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				if (oops.getMessage() == "hackingTry")
					result = this.createEditModelAndView(category, "category.hacking");
				else
					result = this.createEditModelAndView(category, "category.error");
			}
		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Category category) {
		ModelAndView result;
		try {
			this.categoryService.delete(category);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(category, "category.error");
		}

		return result;
	}
}
