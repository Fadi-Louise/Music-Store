package com.example.musicstore.service;

import com.example.musicstore.model.Artist;
import com.example.musicstore.model.Downloads;
import com.example.musicstore.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DownloadService {
    // Establishing connection
    private static final Connection connection = DatabaseConnection.getConnection();

    // Method to add a new download to the database
    public static void addDownload(Downloads download) {
        String query = "INSERT INTO Downloads (SongID, Name, Description) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, download.getSongID());
            statement.setString(2, download.getName());
            statement.setString(3, download.getDescription());
            statement.executeUpdate();
            System.out.println("Download added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding download.");
            e.printStackTrace();
        }
    }

    // Method to retrieve all downloads for a specific song
    public static List<Downloads> getDownloadsBySongID(int songID) {
        List<Downloads> downloads = new ArrayList<>();
        String query = "SELECT * FROM Downloads WHERE SongID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, songID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int downloadID = resultSet.getInt("DownloadID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                Downloads download = new Downloads(songID, name, description);
                download.setDownloadID(downloadID);
                downloads.add(download);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving downloads.");
            e.printStackTrace();
        }
        return downloads;
    }

    // Method to delete a download by its ID
    public static void deleteDownload(int downloadID) {
        String query = "DELETE FROM Downloads WHERE DownloadID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, downloadID);
            statement.executeUpdate();
            System.out.println("Download deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting download.");
            e.printStackTrace();
        }
    }
}
