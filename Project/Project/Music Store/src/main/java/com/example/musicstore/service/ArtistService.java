package com.example.musicstore.service;

import com.example.musicstore.model.Artist;
import com.example.musicstore.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistService {

    // Establishing connection
    private static final Connection connection = DatabaseConnection.getConnection();

    // Method to retrieve an artist by their email
    public static Artist getArtistByEmail(String email) {
        Artist artist = null;
        String query = "SELECT * FROM Artist WHERE Email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int artistID = resultSet.getInt("ArtistID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                artist = new Artist(username, email, password);
                artist.setArtistID(artistID);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving artist by email.");
            e.printStackTrace();
        }
        return artist;
    }

    // Method to retrieve an artist by their username
    public static Artist getArtistByUsername(String username) {
        Artist artist = null;
        String query = "SELECT * FROM Artist WHERE Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int artistID = resultSet.getInt("ArtistID");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                artist = new Artist(username, email, password);
                artist.setArtistID(artistID);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving artist by username.");
            e.printStackTrace();
        }
        return artist;
    }

    // Method to add a new artist to the database
    public static void addArtist(Artist artist) {
        String query = "INSERT INTO Artist (Username, Email, Password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, artist.getUsername());
            statement.setString(2, artist.getEmail());
            statement.setString(3, artist.getPassword());
            statement.executeUpdate();
            System.out.println("Artist added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding artist.");
            e.printStackTrace();
        }
    }

    // Method to update an artist's information by their email
    public static void updateArtistByEmail(String email, Artist updatedArtist) {
        String query = "UPDATE Artist SET Username = ?, Password = ? WHERE Email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, updatedArtist.getUsername());
            statement.setString(2, updatedArtist.getPassword());
            statement.setString(3, email);
            statement.executeUpdate();
            System.out.println("Artist updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating artist.");
            e.printStackTrace();
        }
    }

    // Method to delete an artist from the database
    public static void deleteArtist(int artistID) {
        String query = "DELETE FROM Artist WHERE ArtistID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, artistID);
            statement.executeUpdate();
            System.out.println("Artist deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting artist.");
            e.printStackTrace();
        }
    }
}
