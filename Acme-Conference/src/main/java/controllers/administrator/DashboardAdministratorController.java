
package controllers.administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.DashboardService;
import controllers.AbstractController;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	@Autowired
	DashboardService	dashboardService;


	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		final ModelAndView res;
		res = new ModelAndView("dashboard/dashboard");

		final Double avgSubmissions = this.dashboardService.avgSubmissions();
		final Integer minSubmissions = this.dashboardService.minSubmissions();
		final Integer maxSubmissions = this.dashboardService.maxSubmissions();
		final Double stddevSubmissions = this.dashboardService.stddevSubmissions();

		final Double avgRegistrations = this.dashboardService.avgRegistrations();
		final Integer minRegistrations = this.dashboardService.minRegistrations();
		final Integer maxRegistrations = this.dashboardService.maxRegistrations();
		final Double stddevRegistrations = this.dashboardService.stddevRegistrations();

		final Double avgFee = this.dashboardService.avgFee();
		final Integer minFee = this.dashboardService.minFee();
		final Integer maxFee = this.dashboardService.maxFee();
		final Double stddevFee = this.dashboardService.stddevFee();

		final List<Double> days = this.dashboardService.days();
		final Double avgDays = this.dashboardService.avgDays(days);
		final Double minDays = this.dashboardService.minDays(days);
		final Double maxDays = this.dashboardService.maxDays(days);
		final Double stddevDays = this.dashboardService.stddevDays(days);

		final Double avgCategory = this.dashboardService.avgCategory();
		final Integer minCategory = this.dashboardService.minCategory();
		final Integer maxCategory = this.dashboardService.maxCategory();
		final Double stddevCategory = this.dashboardService.stddevCategory();

		final Double avgCommentsConf = this.dashboardService.avgCommentsConf();
		final Integer minCommentsConf = this.dashboardService.minCommentsConf();
		final Integer maxCommentsConf = this.dashboardService.maxCommentsConf();
		final Double stddevCommentsConf = this.dashboardService.stddevCommentsConf();

		final Double avgCommentsAct = this.dashboardService.avgCommentsAct();
		final Integer minCommentsAct = this.dashboardService.minCommentsAct();
		final Integer maxCommentsAct = this.dashboardService.maxCommentsAct();
		final Double stddevCommentsAct = this.dashboardService.stddevCommentsAct();

		res.addObject("avgSubmissions", avgSubmissions);
		res.addObject("minSubmissions", minSubmissions);
		res.addObject("maxSubmissions", maxSubmissions);
		res.addObject("stddevSubmissions", stddevSubmissions);
		res.addObject("avgRegistrations", avgRegistrations);
		res.addObject("minRegistrations", minRegistrations);
		res.addObject("maxRegistrations", maxRegistrations);
		res.addObject("stddevRegistrations", stddevRegistrations);
		res.addObject("avgFee", avgFee);
		res.addObject("minFee", minFee);
		res.addObject("maxFee", maxFee);
		res.addObject("stddevFee", stddevFee);
		res.addObject("avgDays", avgDays);
		res.addObject("minDays", minDays);
		res.addObject("maxDays", maxDays);
		res.addObject("stddevDays", stddevDays);
		res.addObject("avgCategory", avgCategory);
		res.addObject("minCategory", minCategory);
		res.addObject("maxCategory", maxCategory);
		res.addObject("stddevCategory", stddevCategory);
		res.addObject("avgCommentsConf", avgCommentsConf);
		res.addObject("minCommentsConf", minCommentsConf);
		res.addObject("maxCommentsConf", maxCommentsConf);
		res.addObject("stddevCommentsConf", stddevCommentsConf);
		res.addObject("avgCommentsAct", avgCommentsAct);
		res.addObject("minCommentsAct", minCommentsAct);
		res.addObject("maxCommentsAct", maxCommentsAct);
		res.addObject("stddevCommentsAct", stddevCommentsAct);

		return res;
	}
}
