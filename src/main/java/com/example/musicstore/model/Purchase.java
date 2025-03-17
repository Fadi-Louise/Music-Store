package com.example.musicstore.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Purchase {
    private int purchaseID;      // Unique identifier for the purchase
    private int userID;          // Foreign key referencing the user
    private int songID;          // Foreign key referencing the song
    private Timestamp purchaseDate; // Date and time of the purchase
    private BigDecimal pricePaid; // Price paid for the purchase

    // Constructors
    public Purchase() {
    }

    public Purchase(int userID, int songID, BigDecimal pricePaid) {
        this.userID = userID;
        this.songID = songID;
        this.pricePaid = pricePaid;
        this.purchaseDate = new Timestamp(System.currentTimeMillis()); // Sets current timestamp by default
    }

    // Getters and Setters
    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(BigDecimal pricePaid) {
        this.pricePaid = pricePaid;
    }

    // toString method (for debugging/logging purposes)
    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseID=" + purchaseID +
                ", userID=" + userID +
                ", songID=" + songID +
                ", purchaseDate=" + purchaseDate +
                ", pricePaid=" + pricePaid +
                '}';
    }
}