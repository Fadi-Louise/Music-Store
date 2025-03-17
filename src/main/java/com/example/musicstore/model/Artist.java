package com.example.musicstore.model;

public class Artist {
    private int artistID;      // Unique identifier for the artist
    private String username;    // Username of the artist
    private String email;       // Email of the artist
    private String password;    // Password for the artist's account

    // Constructors
    public Artist() {
    }

    public Artist(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString method (for debugging/logging purposes)
    @Override
    public String toString() {
        return "Artist{" +
                "artistID=" + artistID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
