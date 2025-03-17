package com.example.musicstore.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {

    private static final Connection connection = DatabaseConnection.getConnection();

    // Method to authenticate a user
    public static boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE Username = ? AND Password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If a matching record is found, return true; otherwise, return false.
        } catch (SQLException e) {
            System.out.println("Error during user authentication:");
            e.printStackTrace();
            return false;
        }
    }

    // Method to get the user ID for a user
    public static int getUserID(String username) {
        String query = "SELECT UserID FROM users WHERE Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("UserID"); // Return the UserID
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user ID:");
            e.printStackTrace();
        }
        return -1; // Return -1 if not found or an error occurs
    }

    // Method to authenticate an artist
    public static boolean authenticateArtist(String username, String password) {
        String query = "SELECT * FROM artist WHERE Username = ? AND Password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If a matching record is found, return true; otherwise, return false.
        } catch (SQLException e) {
            System.out.println("Error during artist authentication:");
            e.printStackTrace();
            return false;
        }
    }

    // Method to get the user ID for an artist
    public static int getArtistID(String username) {
        String query = "SELECT ArtistID FROM artist WHERE Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ArtistID"); // Return the ArtistID
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving artist ID:");
            e.printStackTrace();
        }
        return -1; // Return -1 if not found or an error occurs
    }

    // Method to register a new user
    public static boolean registerUser(String username, String email, String password) {
        boolean isRegistered = false;
        String query = "INSERT INTO users (Username, Email, Password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.executeUpdate();
            System.out.println("User registered successfully.");
            isRegistered = true;
        } catch (SQLException e) {
            System.out.println("Error during user registration:");
            e.printStackTrace();
        }
        return isRegistered;
    }

    // Method to register a new artist
    public static boolean registerArtist(String username, String email, String password) {
        boolean isRegistered = false;
        String query = "INSERT INTO artist (Username, Email, Password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.executeUpdate();
            System.out.println("Artist registered successfully.");
            isRegistered = true;
        } catch (SQLException e) {
            System.out.println("Error during artist registration:");
            e.printStackTrace();
        }
        return isRegistered;
    }
}
