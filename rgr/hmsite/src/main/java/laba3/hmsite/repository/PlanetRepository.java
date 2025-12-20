package laba3.hmsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laba3.hmsite.models.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer> {
}
