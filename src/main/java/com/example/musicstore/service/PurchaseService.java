package com.example.musicstore.service;

import com.example.musicstore.model.Purchase;
import com.example.musicstore.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PurchaseService {

    // Establishing connection
    private static final Connection connection = DatabaseConnection.getConnection();

    // Method to add a new purchase to the database
    public static void addPurchase(Purchase purchase) {
        String query = "INSERT INTO Purchases (UserID, SongID, PricePaid, PurchaseDate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, purchase.getUserID());
            statement.setInt(2, purchase.getSongID());
            statement.setBigDecimal(3, purchase.getPricePaid());
            statement.setTimestamp(4, purchase.getPurchaseDate());
            statement.executeUpdate();
            System.out.println("Purchase added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding purchase.");
            e.printStackTrace();
        }
    }

    // Method to retrieve all purchases by user ID
    public static List<Purchase> getPurchasesByUserID(int userID) {
        List<Purchase> purchases = new ArrayList<>();
        String query = "SELECT * FROM Purchases WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int purchaseID = resultSet.getInt("PurchaseID");
                int songID = resultSet.getInt("SongID");
                Timestamp purchaseDate = resultSet.getTimestamp("PurchaseDate");
                BigDecimal pricePaid = resultSet.getBigDecimal("PricePaid");
                Purchase purchase = new Purchase(userID, songID, pricePaid);
                purchase.setPurchaseID(purchaseID);
                purchase.setPurchaseDate(purchaseDate);
                purchases.add(purchase);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving purchases.");
            e.printStackTrace();
        }
        return purchases;
    }

    // Method to delete a purchase by its ID
    public static void deletePurchase(int purchaseID) {
        String query = "DELETE FROM Purchases WHERE PurchaseID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, purchaseID);
            statement.executeUpdate();
            System.out.println("Purchase deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting purchase.");
            e.printStackTrace();
        }
    }
}
