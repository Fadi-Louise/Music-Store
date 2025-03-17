package com.example.springboot_backend.repository;

import com.example.springboot_backend.model.Song;
import com.example.springboot_backend.model.Artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    // Find songs by title
    Optional<Song> findByTitle(String title);

    // Find songs by genre
    List<Song> findByGenre(String genre);

    // Find songs by price
    List<Song> findByPrice(BigDecimal price);

    // Find songs by release date
    List<Song> findByReleaseDate(LocalDate releaseDate);

    // Find songs by artist
    List<Song> findByArtist(Artist artist);

}
