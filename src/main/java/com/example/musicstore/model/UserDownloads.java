package com.example.musicstore.model;

import java.sql.Timestamp;

public class UserDownloads {
    private int userDownloadID; // Unique identifier for the user download
    private int userID;         // Foreign key referencing the user
    private int downloadID;     // Foreign key referencing the download
    private Timestamp downloadDate; // Date and time of the download

    // Constructors
    public UserDownloads() {
        // Default constructor
    }

    public UserDownloads(int userID, int downloadID) {
        this.userID = userID;
        this.downloadID = downloadID;
        this.downloadDate = new Timestamp(System.currentTimeMillis()); // Sets current timestamp by default
    }

    // Getters and Setters
    public int getUserDownloadID() {
        return userDownloadID;
    }

    public void setUserDownloadID(int userDownloadID) {
        this.userDownloadID = userDownloadID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getDownloadID() {
        return downloadID;
    }

    public void setDownloadID(int downloadID) {
        this.downloadID = downloadID;
    }

    public Timestamp getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(Timestamp downloadDate) {
        this.downloadDate = downloadDate;
    }

    // toString method (for debugging/logging purposes)
    @Override
    public String toString() {
        return "UserDownloads{" +
                "userDownloadID=" + userDownloadID +
                ", userID=" + userID +
                ", downloadID=" + downloadID +
                ", downloadDate=" + downloadDate +
                '}';
    }
}
