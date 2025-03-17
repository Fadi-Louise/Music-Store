package com.example.springboot_backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PurchaseID") // Column name in database
    private int purchaseID;

    @ManyToOne
    @JoinColumn(name = "UserID") // Foreign key to User
    private User user;

    @ManyToOne
    @JoinColumn(name = "SongID") // Foreign key to Song
    private Song song;

    @Column(name = "PricePaid") // Ensure this matches the column name in the DB exactly
    private BigDecimal pricePaid;

    @Column(name = "PurchaseDate")
    private java.time.LocalDateTime purchaseDate;

    // Getters and Setters
    public int getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public BigDecimal getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(BigDecimal pricePaid) {
        this.pricePaid = pricePaid;
    }

    public java.time.LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(java.time.LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseID=" + purchaseID +
                ", user=" + user +
                ", song=" + song +
                ", pricePaid=" + pricePaid +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
