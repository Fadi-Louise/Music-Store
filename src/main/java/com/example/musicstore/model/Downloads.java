package com.example.musicstore.model;

public class Downloads {
    private int downloadID; // Unique identifier for the download
    private int songID;     // Foreign key referencing the song
    private String name;    // Name of the download
    private String description; // Description of the download

    // Default constructor
    public Downloads() {
    }

    // Parameterized constructor
    public Downloads(int songID, String name, String description) {
        this.songID = songID;
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public int getDownloadID() {
        return downloadID;
    }

    public void setDownloadID(int downloadID) {
        this.downloadID = downloadID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method for debugging/logging purposes
    @Override
    public String toString() {
        return "Download{" +
                "downloadID=" + downloadID +
                ", songID=" + songID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
