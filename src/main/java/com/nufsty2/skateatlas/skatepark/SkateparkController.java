package com.nufsty2.skateatlas.skatepark;

import org.springframework.stereotype.Controller;
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

    @GetMapping("/skateparks/all")
    public Iterable<Skatepark> getAllSkateparks() {
        return skateparks.findAll();
    }

    @PostMapping("/skateparks/add")
    public @ResponseBody String addSkatepark(@RequestParam String name,
            @RequestParam Boolean lights, @RequestParam Boolean free,
            @RequestParam Boolean inside, @RequestParam String surface,
            @RequestParam String address, @RequestParam Integer postalCode,
            @RequestParam String city, @RequestParam String country,
            @RequestParam String state, @RequestParam String website) {
        
        Skatepark newPark = new Skatepark(
            name, lights, free, inside, surface, address, postalCode, city,
            country, state, website
        );
        skateparks.save(newPark);

        return "Saved";
    }
}