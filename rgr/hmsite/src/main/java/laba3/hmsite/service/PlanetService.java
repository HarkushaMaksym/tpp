package laba3.hmsite.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import laba3.hmsite.models.Planet;
import laba3.hmsite.repository.PlanetRepository;

@Service
public class PlanetService {

    private static final Logger logger = LoggerFactory.getLogger(PlanetService.class);

    @Autowired
    private PlanetRepository planetRepository;

    

    public List<Planet> getAllPlanets() {
        return planetRepository.findAll(Sort.by("id"));
    }

    public Optional<Planet> findPlanetById(Integer id) {
        return planetRepository.findById(id);
    }

    public void savePlanet(Planet planet) {
        planetRepository.save(planet);
        logger.info("Saved planet: {}", planet.getName());
    }

    public void updatePlanet(Planet updatedPlanet) {
        Planet existingPlanet = planetRepository.findById(updatedPlanet.getId())
                .orElseThrow(() -> new IllegalArgumentException("Planet not found"));

        existingPlanet.setName(updatedPlanet.getName());
        existingPlanet.setDescription(updatedPlanet.getDescription());
        planetRepository.save(existingPlanet);
        logger.info("Updated planet id={} name={}", existingPlanet.getId(), existingPlanet.getName());
    }

    public void deletePlanetById(Integer id) {
        planetRepository.deleteById(id);
        logger.info("Deleted planet id={}", id);
    }
    
}
