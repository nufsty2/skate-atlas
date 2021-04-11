package com.nufsty2.skateatlas.skatepark;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SkateparkController {

	private final SkateparkRepository skateparks;

	public SkateparkController(SkateparkRepository skateparks) {
		this.skateparks = skateparks;
	}

	@GetMapping("/skateparks")
	public String getAllSkateparks(Skatepark skatepark, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /skateparks to return all records
		if (skatepark.getCountry() == null) {
			skatepark.setCountry(""); // empty strihng signifies broadest possible search
		}

		// find skateparks by country
		Collection<Skatepark> results = this.skateparks.findByCountry(skatepark.getCountry());
		model.put("selections", results);
		return "skatepark/skateparksList";
	}

	@PostMapping("/skateparks/add")
	public @ResponseBody String addSkatepark(@RequestParam String name, @RequestParam Boolean lights,
			@RequestParam Boolean free, @RequestParam Boolean inside, @RequestParam String surface,
			@RequestParam String address, @RequestParam Integer postalCode, @RequestParam String city,
			@RequestParam String country, @RequestParam String state, @RequestParam String website) {

		Skatepark newPark = new Skatepark(name, lights, free, inside, surface, address, postalCode, city, country,
				state, website);
		skateparks.save(newPark);

		return "Saved";
	}

}