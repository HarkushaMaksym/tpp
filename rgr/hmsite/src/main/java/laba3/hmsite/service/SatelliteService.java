package laba3.hmsite.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import laba3.hmsite.models.Satellite;
import laba3.hmsite.repository.SatelliteRepository;

@Service
public class SatelliteService {

    private static final Logger logger = LoggerFactory.getLogger(SatelliteService.class);

    @Autowired
    private SatelliteRepository satelliteRepository;

    

    public List<Satellite> getAllSatellites() {
        return satelliteRepository.findAll(Sort.by("id"));
    }

    public Optional<Satellite> findSatelliteById(Integer id) {
        return satelliteRepository.findById(id);
    }

    public void saveSatellite(Satellite satellite) {
        satelliteRepository.save(satellite);
        logger.info("Saved satellite: {}", satellite.getName());
    }

    public void updateSatellite(Satellite updatedSatellite) {
        Satellite existingSatellite = satelliteRepository.findById(updatedSatellite.getId())
                .orElseThrow(() -> new IllegalArgumentException("Satellite not found"));

        existingSatellite.setName(updatedSatellite.getName());
        existingSatellite.setDescription(updatedSatellite.getDescription());
        existingSatellite.setPlanet(updatedSatellite.getPlanet());
        satelliteRepository.save(existingSatellite);
        logger.info("Updated satellite id={} name={}", existingSatellite.getId(), existingSatellite.getName());
    }

    public void deleteSatelliteById(Integer id) {
        satelliteRepository.deleteById(id);
        logger.info("Deleted satellite id={}", id);
    }
    
}
