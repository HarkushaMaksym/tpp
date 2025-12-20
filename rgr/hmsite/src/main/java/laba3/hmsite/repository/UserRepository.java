package laba3.hmsite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import laba3.hmsite.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
