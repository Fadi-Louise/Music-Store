package com.example.musicstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Song {
    private int songID;          // Unique identifier for the song
    private String title;        // Title of the song
    private String description;  // Description of the song
    private String genre;        // Genre of the song
    private BigDecimal price;    // Price of the song
    private LocalDate releaseDate; // Release date of the song
    private int artistID;        // Foreign key referencing the artist

    // Constructors
    public Song() {
    }

    public Song(int songID, String title, String description, String genre, BigDecimal price, LocalDate releaseDate, int artistID) {
        this.songID = songID;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.price = price;
        this.releaseDate = releaseDate;
        this.artistID = artistID;
    }
    public Song( String title, String description, String genre, BigDecimal price, LocalDate releaseDate, int artistID) {

        this.title = title;
        this.description = description;
        this.genre = genre;
        this.price = price;
        this.releaseDate = releaseDate;
        this.artistID = artistID;
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

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    // toString method (for debugging/logging purposes)
    @Override
    public String toString() {
        return "Song{" +
                "songID=" + songID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", artistID=" + artistID +
                '}';
    }
}
