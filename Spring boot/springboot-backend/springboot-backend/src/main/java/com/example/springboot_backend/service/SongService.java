package com.example.springboot_backend.service;

import com.example.springboot_backend.model.Song;
import com.example.springboot_backend.model.Artist;
import com.example.springboot_backend.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    // Get all songs
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    // Get a song by its ID
    public Optional<Song> getSongById(int id) {
        return songRepository.findById(id);
    }

    // Get a song by its title
    public Optional<Song> getSongByTitle(String title) {
        return songRepository.findByTitle(title);
    }

    // Get songs by genre
    public List<Song> getSongsByGenre(String genre) {
        return songRepository.findByGenre(genre);
    }

    // Get songs by price
    public List<Song> getSongsByPrice(BigDecimal price) {
        return songRepository.findByPrice(price);
    }

    // Get songs by release date
    public List<Song> getSongsByReleaseDate(LocalDate releaseDate) {
        return songRepository.findByReleaseDate(releaseDate);
    }

    // Get songs by artist
    public List<Song> getSongsByArtist(Artist artist) {
        return songRepository.findByArtist(artist);
    }

    // Save a new song
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    // Update an existing song
    public void updateSong(int id, Song updatedSong) {
        Optional<Song> existingSong = songRepository.findById(id);
        if (existingSong.isPresent()) {
            Song song = existingSong.get();
            song.setTitle(updatedSong.getTitle());
            song.setDescription(updatedSong.getDescription());
            song.setGenre(updatedSong.getGenre());
            song.setPrice(updatedSong.getPrice());
            song.setReleaseDate(updatedSong.getReleaseDate());
            song.setArtist(updatedSong.getArtist());
            songRepository.save(song);
        } else {
            throw new RuntimeException("Song not found for ID: " + id);
        }
    }

    // Delete a song
    public void deleteSong(int id) {
        songRepository.deleteById(id);
    }

    // Search songs by query (basic example, you can extend it)
    public List<Song> searchSongs(String query) {
        return songRepository.findAll().stream()
                .filter(song -> song.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        song.getGenre().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }
}
