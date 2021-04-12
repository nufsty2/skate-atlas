package com.nufsty2.skateatlas.skatepark;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@GetMapping("/skateparks/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("skatepark", new Skatepark());
		return "skatepark/findSkateparks";
	}

	@GetMapping("/skateparks")
	public String processFindForm(Skatepark skatepark, BindingResult result, Map<String, Object> model) {

		// find skateparks by state
		Collection<Skatepark> results = (skatepark.getState() == "") ? (Collection<Skatepark>) this.skateparks.findAll()
				: this.skateparks.findByState(skatepark.getState());

		if (results.isEmpty()) {
			// no skateparks found
			result.rejectValue("state", "notFound", "not found");
			return "skatepark/findSkateparks";
		}
		else if (results.size() == 1) {
			// 1 skatepark found
			skatepark = results.iterator().next();
			return "redirect:/skateparks/" + skatepark.getId();
		}
		else {
			model.put("selections", results);
			return "skatepark/skateparksList";
		}
	}

	@GetMapping("/skateparks/{id}/edit")
	public String initUpdateSkateparkForm(@PathVariable("id") int id, Model model) {
		Skatepark skatepark = this.skateparks.findById(id);
		model.addAttribute(skatepark);
		return VIEWS_SKATEPARK_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/skateparks/{id}/edit")
	public String processUpdateSkateparkForm(@Valid Skatepark skatepark, BindingResult result,
			@PathVariable("id") int id) {
		if (result.hasErrors()) {
			return VIEWS_SKATEPARK_CREATE_OR_UPDATE_FORM;
		}
		else {
			skatepark.setId(id);
			this.skateparks.save(skatepark);
			return "redirect:/skateparks/{id}";
		}
	}

	@GetMapping("/skateparks/{id}/delete")
	public String initDeleteSkateparkForm(@PathVariable("id") int id, Model model) {
		Skatepark skatepark = this.skateparks.findById(id);
		String state = skatepark.getState();
		this.skateparks.delete(skatepark);
		return "redirect:/skateparks/?state=" + state;
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