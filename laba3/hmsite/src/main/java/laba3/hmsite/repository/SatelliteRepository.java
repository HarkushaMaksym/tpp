package laba3.hmsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laba3.hmsite.models.Satellite;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, Integer> {
}
