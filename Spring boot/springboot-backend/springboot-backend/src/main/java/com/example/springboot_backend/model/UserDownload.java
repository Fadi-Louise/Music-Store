package com.example.springboot_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user-downloads")  // Updated to match the table name
public class UserDownload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userDownloadID;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "DownloadID")
    private Download download;

    @Column(name = "DownloadDate")
    private String downloadDate;

    // Constructors
    public UserDownload() {}

    public UserDownload(User user, Download download, String downloadDate) {
        this.user = user;
        this.download = download;
        this.downloadDate = downloadDate;
    }

    // Getters and Setters
    public Integer getUserDownloadID() {
        return userDownloadID;
    }

    public void setUserDownloadID(Integer userDownloadID) {
        this.userDownloadID = userDownloadID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Download getDownload() {
        return download;
    }

    public void setDownload(Download download) {
        this.download = download;
    }

    public String getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(String downloadDate) {
        this.downloadDate = downloadDate;
    }

    @Override
    public String toString() {
        return "UserDownload{" +
                "userDownloadID=" + userDownloadID +
                ", user=" + user +
                ", download=" + download +
                ", downloadDate='" + downloadDate + '\'' +
                '}';
    }
}
