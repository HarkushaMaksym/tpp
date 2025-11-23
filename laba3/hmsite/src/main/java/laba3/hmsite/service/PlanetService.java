package laba3.hmsite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laba3.hmsite.models.Planet;
import laba3.hmsite.repository.PlanetRepository;

@Service
public class PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    public Optional<Planet> findPlanetById(Integer id) {
        return planetRepository.findById(id);
    }

    public void savePlanet(Planet planet) {
        planetRepository.save(planet);
    }

    public void updatePlanet(Planet updatedPlanet) {
        Planet existingPlanet = planetRepository.findById(updatedPlanet.getId())
                .orElseThrow(() -> new IllegalArgumentException("Planet not found"));

        existingPlanet.setName(updatedPlanet.getName());
        existingPlanet.setDescription(updatedPlanet.getDescription());
        planetRepository.save(existingPlanet);
    }

    public void deletePlanetById(Integer id) {
        planetRepository.deleteById(id);
    }
}
