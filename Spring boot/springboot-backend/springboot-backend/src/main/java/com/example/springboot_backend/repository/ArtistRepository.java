package com.example.springboot_backend.repository;

import com.example.springboot_backend.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findByUsernameAndPassword(String username, String password);
    Optional<Artist> findByEmail(String email);
    Optional<Artist> findByUsername(String username);
}
