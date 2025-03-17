package com.example.springboot_backend.controller;

import com.example.springboot_backend.model.Song;
import com.example.springboot_backend.model.Artist;
import com.example.springboot_backend.service.SongService;
import com.example.springboot_backend.service.ArtistService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;
    private final ArtistService artistService;

    @Autowired
    public SongController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable int id) {
        Optional<Song> song = songService.getSongById(id);
        return song.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Song> getSongByTitle(@PathVariable String title) {
        Optional<Song> song = songService.getSongByTitle(title);
        return song.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Song>> getSongsByGenre(@PathVariable String genre) {
        List<Song> songs = songService.getSongsByGenre(genre);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<Song>> getSongsByPrice(@PathVariable BigDecimal price) {
        List<Song> songs = songService.getSongsByPrice(price);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/release-date/{releaseDate}")
    public ResponseEntity<List<Song>> getSongsByReleaseDate(@PathVariable LocalDate releaseDate) {
        List<Song> songs = songService.getSongsByReleaseDate(releaseDate);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<Song>> getSongsByArtistId(@PathVariable int artistId) {
        Artist artistObj = artistService.getArtistById(artistId).orElse(null);
        if (artistObj == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Song> songs = songService.getSongsByArtist(artistObj);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Song> saveSong(@Valid @RequestBody Song song) {
        Song savedSong = songService.saveSong(song);
        return new ResponseEntity<>(savedSong, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSong(@PathVariable int id, @Valid @RequestBody Song updatedSong) {
        try {
            songService.updateSong(id, updatedSong);
            return new ResponseEntity<>("Song updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable int id) {
        songService.deleteSong(id);
        return new ResponseEntity<>("Song deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Song> searchSongs(@RequestParam String query) {
        return songService.searchSongs(query);
    }
}
