
package controllers;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import repositories.ActorRepository;
import services.ReviewerService;
import domain.Reviewer;

@Controller
@RequestMapping("/reviewer")
public class ReviewerController extends AbstractController {

	@Autowired
	private ReviewerService	reviewerService;

	@Autowired
	private ActorRepository	actorRepository;


	// Constructors -----------------------------------------------------------

	public ReviewerController() {
		super();
	}

	// Register ---------------------------------------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;

		//		Reviewerity reviewerity;
		//		Collection<Reviewerity> reviewerities;
		//		UserAccount userAccount;
		//		userAccount = new UserAccount();
		//		reviewerities = userAccount.getReviewerities();
		//		reviewerity = new Reviewerity();
		//		reviewerity.setReviewerity(Reviewerity.AUTHOR);
		//		reviewerities.add(reviewerity);
		//		userAccount.setReviewerities(reviewerities);

		final Reviewer reviewer = this.reviewerService.create();
		//reviewer.setUserAccount(userAccount);
		res = this.createEditModelAndView(reviewer);

		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Reviewer reviewer, final BindingResult binding) {
		ModelAndView result;

		try {

			final String vacia = "";
			if (!reviewer.getEmail().isEmpty() || reviewer.getEmail() != vacia)
				Assert.isTrue(reviewer.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || reviewer.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			Assert.isTrue(this.actorRepository.findByUsername(reviewer.getUserAccount().getUsername()) == null, "username taken");

			this.reviewerService.save(reviewer);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(reviewer);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditModelAndView(reviewer, "reviewer.email.error");
			else if (oops.getMessage() == "username taken")
				result = this.createEditModelAndView(reviewer, "reviewer.username.error");
			else
				result = this.createEditModelAndView(reviewer, "reviewer.comit.error");
		}
		return result;

	}
	protected ModelAndView createEditModelAndView(final Reviewer reviewer) {
		ModelAndView result;

		result = this.createEditModelAndView(reviewer, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Reviewer reviewer, final String message) {
		ModelAndView result;

		result = new ModelAndView("reviewer/register");
		result.addObject("reviewer", reviewer);
		result.addObject("message", message);

		return result;
	}

}
