package laba3.hmsite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import laba3.hmsite.models.Satellite;
import laba3.hmsite.repository.SatelliteRepository;

@Service
public class SatelliteService {

    @Autowired
    private SatelliteRepository satelliteRepository;

    public List<Satellite> getAllSatellites() {
        return satelliteRepository.findAll();
    }

    public Optional<Satellite> findSatelliteById(Integer id) {
        return satelliteRepository.findById(id);
    }

    public void saveSatellite(Satellite satellite) {
        satelliteRepository.save(satellite);
    }

    public void updateSatellite(Satellite updatedSatellite) {
        Satellite existingSatellite = satelliteRepository.findById(updatedSatellite.getId())
                .orElseThrow(() -> new IllegalArgumentException("Satellite not found"));

        existingSatellite.setName(updatedSatellite.getName());
        existingSatellite.setDescription(updatedSatellite.getDescription());
        existingSatellite.setPlanet(updatedSatellite.getPlanet());
        satelliteRepository.save(existingSatellite);
    }

    public void deleteSatelliteById(Integer id) {
        satelliteRepository.deleteById(id);
    }
}
