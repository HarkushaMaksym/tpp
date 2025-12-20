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
import laba3.hmsite.models.Satellite;
import laba3.hmsite.service.PlanetService;
import laba3.hmsite.service.SatelliteService;
@Controller
@RequestMapping("/satellites")
public class SatelliteController {

    @Autowired
    private SatelliteService satelliteService;

    @Autowired
    private PlanetService planetService;

    @GetMapping
    public String listSatellites(Model model) {
        model.addAttribute("satellites", satelliteService.getAllSatellites());
        return "satellites";
    }

    @GetMapping("/add")
    public String addSatelliteForm(Model model) {
        model.addAttribute("satellite", new Satellite());
        model.addAttribute("planets", planetService.getAllPlanets());
        return "add-satellite";
    }

    @PostMapping("/add")
    public String addSatellite(@Valid @ModelAttribute("satellite") Satellite satellite,
                            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("planets", planetService.getAllPlanets());
            return "add-satellite";
        }

        satelliteService.saveSatellite(satellite);
        return "redirect:/satellites";
    }

    @GetMapping("/edit/{id}")
    public String editSatelliteForm(@PathVariable("id") Integer id, Model model) {
        Satellite satellite = satelliteService.findSatelliteById(id).orElse(null);

        if (satellite == null)
            return "redirect:/satellites";

        model.addAttribute("satellite", satellite);
        model.addAttribute("planets", planetService.getAllPlanets());
        return "edit-satellite";
    }

    @PostMapping("/update/{id}")
    public String updateSatellite(@PathVariable("id") Integer id, @Valid @ModelAttribute("satellite") Satellite satellite,
                BindingResult result,Model model) {

        if (result.hasErrors()) {
            model.addAttribute("planets", planetService.getAllPlanets());
            return "edit-satellite";
        }

        Satellite existingSatellite = satelliteService.findSatelliteById(id)
            .orElseThrow(() -> new IllegalArgumentException("Satellite not found with ID: " + id));

        existingSatellite.setName(satellite.getName());
        existingSatellite.setDescription(satellite.getDescription());
        existingSatellite.setPlanet(satellite.getPlanet());

        satelliteService.updateSatellite(existingSatellite);

        return "redirect:/satellites";
    }

    @GetMapping("/delete/{id}")
    public String deleteSatellite(@PathVariable("id") Integer id) {
        satelliteService.deleteSatelliteById(id);
        return "redirect:/satellites";
    }
}