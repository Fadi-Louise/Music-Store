package com.example.springboot_backend.service;

import com.example.springboot_backend.model.Artist;
import com.example.springboot_backend.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Optional<Artist> getArtistById(int id) {
        return artistRepository.findById(id);
    }

    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist updateArtist(int id, Artist artist) {
        if (artistRepository.existsById(id)) {
            artist.setArtistID(id);
            return artistRepository.save(artist);
        } else {
            throw new IllegalArgumentException("Artist with ID " + id + " not found");
        }
    }

    public void deleteArtist(int id) {
        artistRepository.deleteById(id);
    }

    public Optional<Artist> authenticateArtist(String username, String password) {
        return artistRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<Artist> getArtistByEmail(String email) {
        return artistRepository.findByEmail(email);
    }

    public Optional<Artist> getArtistByUsername(String username) {
        return artistRepository.findByUsername(username);
    }
}
