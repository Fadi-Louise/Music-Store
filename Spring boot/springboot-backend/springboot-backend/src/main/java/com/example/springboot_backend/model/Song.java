package com.example.springboot_backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SongID") // Match the primary key column name in the database
    private int songID;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "genre")
    private String genre;

    @Column(name = "price", precision = 38, scale = 2)
    private BigDecimal price;

    @Column(name = "ReleaseDate") // Match the column name exactly as in the database
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "ArtistID") // Match the foreign key column name
    private Artist artist;

    // Constructors
    public Song() {}

    public Song(String title, String description, String genre, BigDecimal price, LocalDate releaseDate, Artist artist) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.price = price;
        this.releaseDate = releaseDate;
        this.artist = artist;
    }

    // Getters and Setters
    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songID=" + songID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", artist=" + (artist != null ? artist.getUsername() : "null") +
                '}';
    }
}
