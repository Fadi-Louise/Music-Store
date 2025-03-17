package com.example.musicstore.service;

import com.example.musicstore.model.UserDownloads;
import com.example.musicstore.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDownloadsService {
    // Establishing connection
    private static final Connection connection = DatabaseConnection.getConnection();

    // Method to add a new user download to the database
    public static void addUserDownload(UserDownloads userDownload) {
        String query = "INSERT INTO UserDownloads (UserID, DownloadID, DownloadDate) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userDownload.getUserID());
            statement.setInt(2, userDownload.getDownloadID());
            statement.setTimestamp(3, userDownload.getDownloadDate());
            statement.executeUpdate();
            System.out.println("User download added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding user download: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to retrieve all downloads by user ID
    public static List<UserDownloads> getUserDownloadsByUserID(int userID) {
        List<UserDownloads> userDownloadsList = new ArrayList<>();
        String query = "SELECT * FROM UserDownloads WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userDownloadID = resultSet.getInt("UserDownloadID");
                int downloadID = resultSet.getInt("DownloadID");
                Timestamp downloadDate = resultSet.getTimestamp("DownloadDate");

                UserDownloads userDownload = new UserDownloads(userID, downloadID);
                userDownload.setUserDownloadID(userDownloadID);
                userDownload.setDownloadDate(downloadDate);
                userDownloadsList.add(userDownload);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user downloads: " + e.getMessage());
            e.printStackTrace();
        }
        return userDownloadsList;
    }

    // Method to delete a user download by its ID
    public static void deleteUserDownload(int userDownloadID) {
        String query = "DELETE FROM UserDownloads WHERE UserDownloadID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userDownloadID);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User download deleted successfully.");
            } else {
                System.out.println("No user download found with ID: " + userDownloadID);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting user download: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
