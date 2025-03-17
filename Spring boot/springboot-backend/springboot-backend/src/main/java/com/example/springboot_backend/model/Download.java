package com.example.springboot_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "downloads")
public class Download {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DownloadID")
    private Integer downloadId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "SongID", referencedColumnName = "SongID", nullable = false)
    private Song song; // Maintains consistent naming as in Song, Artist, and User

    // Constructors
    public Download() {
    }

    public Download(String name, String description, Song song) {
        this.name = name;
        this.description = description;
        this.song = song;
    }

    // Getters and Setters
    public Integer getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
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

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @Override
    public String toString() {
        return "Download{" +
                "downloadId=" + downloadId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", song=" + (song != null ? song.toString() : "null") +
                '}';
    }
}
