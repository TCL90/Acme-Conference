
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CameraReadyPaperService;
import domain.CameraReadyPaper;

@Controller
@RequestMapping("/camerareadypaper/administrator")
public class CameraReadyPaperAdministratorController extends AbstractController {

	@Autowired
	private CameraReadyPaperService	cameraReadyPaperService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int conferenceId) {
		ModelAndView result;

		Collection<CameraReadyPaper> cameras = null;

		try {

			cameras = this.cameraReadyPaperService.findAllByConferenceId(conferenceId);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("camerareadypaper/list");
		result.addObject("cameras", cameras);
		result.addObject("requestURI", "/camerareadypaper/administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int paperId) {
		final ModelAndView result;
		CameraReadyPaper c = null;

		try {
			c = this.cameraReadyPaperService.findOne(paperId);

		} catch (final Throwable oops) {
			result = new ModelAndView("welcome/index");
			return result;
		}

		result = new ModelAndView("camerareadypaper/show");
		result.addObject("camera", c);

		return result;
	}
}
