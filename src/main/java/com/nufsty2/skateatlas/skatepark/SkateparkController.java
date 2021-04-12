package com.nufsty2.skateatlas.skatepark;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SkateparkController {

	private static final String VIEWS_SKATEPARK_CREATE_OR_UPDATE_FORM = "skatepark/createOrUpdateSkateparkForm";

	private final SkateparkRepository skateparks;

	public SkateparkController(SkateparkRepository skateparks) {
		this.skateparks = skateparks;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/skateparks")
	public String processFindForm(Skatepark skatepark, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /skateparks to return all records
		if (skatepark.getCountry() == null) {
			skatepark.setCountry(""); // empty strihng signifies broadest possible search
		}

		// find skateparks by country
		Collection<Skatepark> results = this.skateparks.findByCountry(skatepark.getCountry());
		model.put("selections", results);
		return "skatepark/skateparksList";
	}

	@GetMapping("/skateparks/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("skatepark", new Skatepark());
		return "skatepark/findSkateparks";
	}

	@GetMapping("/skateparks/new")
	public String initCreationForm(Map<String, Object> model) {
		Skatepark skatepark = new Skatepark();
		model.put("skatepark", skatepark);
		return VIEWS_SKATEPARK_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/skateparks/new")
	public String processCreationForm(@Valid Skatepark skatepark, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_SKATEPARK_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.skateparks.save(skatepark);
			return "redirect:/skateparks/" + skatepark.getId();
		}
	}

	/**
	 * Custom handler for displaying an skatepark.
	 * @param id the ID of the skatepark to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/skateparks/{id}")
	public ModelAndView showSkatepark(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("skatepark/skateparkdetails");
		Skatepark skatepark = this.skateparks.findById(id);
		mav.addObject(skatepark);
		return mav;
	}

}