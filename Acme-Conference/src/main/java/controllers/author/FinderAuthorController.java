
package controllers.author;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.FinderService;
import controllers.AbstractController;
import domain.Category;
import domain.Finder;

@Controller
@RequestMapping("/finder/author")
public class FinderAuthorController extends AbstractController {

	@Autowired
	FinderService	finderService;

	@Autowired
	CategoryService	categoryService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		final ModelAndView res;
		Finder finder;
		finder = this.finderService.getFinder();

		res = new ModelAndView("finder/show");

		res.addObject("finder", finder);
		res.addObject("requestURI", "/finder/author/show.do");

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		Finder finder;

		finder = this.finderService.getFinder();
		Assert.notNull(finder);
		res = this.createEditModelAndView(finder);
		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Finder finder, final BindingResult binding) {
		ModelAndView res;
		try {
			finder = this.finderService.save(finder);
			res = new ModelAndView("redirect:show.do");
		} catch (final ValidationException oops) {
			res = this.createEditModelAndView(finder);
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(finder, "finder.error");
		}
		return res;
	}

	protected ModelAndView createEditModelAndView(final Finder finder) {
		final ModelAndView res;

		res = this.createEditModelAndView(finder, null);
		return res;
	}
	protected ModelAndView createEditModelAndView(final Finder finder, final String message) {
		final ModelAndView res;
		res = new ModelAndView("finder/edit");
		final List<Category> categories = this.categoryService.findAll();
		res.addObject("finder", finder);
		res.addObject("message", message);
		res.addObject("categories", categories);
		return res;
	}

}
