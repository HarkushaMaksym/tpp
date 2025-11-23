package laba3.hmsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import laba3.hmsite.models.Planet;
import laba3.hmsite.service.PlanetService;
import laba3.hmsite.service.SatelliteService;

@Controller
@RequestMapping("/planets")
public class PlanetController {
    @Autowired
    private PlanetService planetService;

    @Autowired
    private SatelliteService satelliteService;

    @GetMapping
    public String listPlanets(Model model) {
        model.addAttribute("planets", planetService.getAllPlanets());
        return "planets";
    }

    @GetMapping("/add")
    public String addPlanetForm(Model model ) {
        
        model.addAttribute("planet", new Planet());
        
        return "add-planet";
    }

    @PostMapping("/add")
    public String addPlanet(@Valid @ModelAttribute("planet") Planet planet, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            
            return "add-planet";
        }
        planetService.savePlanet(planet);
        return "redirect:/planets";
    }

    @PostMapping("/update/{id}")
    public String updatePlanet(@PathVariable("id") Integer id, @Valid @ModelAttribute("planet") Planet planet,
            BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            
            return "edit-planet";
        }
        planet.setId(id);
        planetService.updatePlanet(planet);
        return "redirect:/planets";
    }
    
    @GetMapping("/edit/{id}")
    public String editPlanetForm(@PathVariable("id") Integer id, Model model) {
        Planet planet = planetService.findPlanetById(id).orElse(null);
        if (planet != null) {
            model.addAttribute("planet", planet);
            return "edit-planet";
        } else {
            return "redirect:/planets";
        }
    }

    @GetMapping("/delete/{id}")
    public String deletePlanet(@PathVariable("id") Integer id ) {
        
        planetService.deletePlanetById(id);
        return "redirect:/planets";
    }
}
