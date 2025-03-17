package com.example.musicstore.service;

import com.example.musicstore.model.Song;
import com.example.musicstore.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SongService {

    // Establishing connection
    private static final Connection connection = DatabaseConnection.getConnection();

    // Method to add a new song to the database
    public static void addSong(Song song) {
        String query = "INSERT INTO songs (Title, Description, Genre, Price, ReleaseDate, ArtistID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, song.getTitle());
            pstmt.setString(2, song.getDescription());
            pstmt.setString(3, song.getGenre());
            pstmt.setBigDecimal(4, song.getPrice());
            pstmt.setDate(5, java.sql.Date.valueOf(song.getReleaseDate())); // Assuming releaseDate is of type LocalDate
            pstmt.setInt(6, song.getArtistID()); // Ensure you have a getArtistID method in your Song class

            pstmt.executeUpdate();
            System.out.println("Song saved to database: " + song.getTitle());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to save song to database.");
        }
    }


    // Method to retrieve all songs from the database
    public static List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT * FROM Songs";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int songID = resultSet.getInt("SongID");
                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String genre = resultSet.getString("Genre");
                BigDecimal price = resultSet.getBigDecimal("Price");
                LocalDate releaseDate = resultSet.getDate("ReleaseDate").toLocalDate();
                int artistID = resultSet.getInt("ArtistID");
                Song song = new Song( title, description, genre, price, releaseDate, artistID);
                songs.add(song);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving songs.");
            e.printStackTrace();
        }
        return songs;
    }

    // Method to retrieve all songs by an artist's username
    public static List<Song> getSongsByArtistUsername(String username) {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT s.* FROM Songs s JOIN Artist a ON s.ArtistID = a.ArtistID WHERE a.Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int songID = resultSet.getInt("SongID");
                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String genre = resultSet.getString("Genre");
                BigDecimal price = resultSet.getBigDecimal("Price");
                LocalDate releaseDate = resultSet.getDate("ReleaseDate").toLocalDate();
                int artistID = resultSet.getInt("ArtistID");
                Song song = new Song( title, description, genre, price, releaseDate, artistID);
                songs.add(song);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving songs by artist username.");
            e.printStackTrace();
        }
        return songs;
    }

    // Method to retrieve songs by title
    public static List<Song> getSongsByTitle(String title) {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT * FROM Songs WHERE Title = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int songID = resultSet.getInt("SongID");
                String songTitle = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String genre = resultSet.getString("Genre");
                BigDecimal price = resultSet.getBigDecimal("Price");
                LocalDate releaseDate = resultSet.getDate("ReleaseDate").toLocalDate();
                int artistID = resultSet.getInt("ArtistID");
                Song song = new Song( songTitle, description, genre, price, releaseDate, artistID);
                songs.add(song);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving songs by title.");
            e.printStackTrace();
        }
        return songs;
    }

    // Method to delete a song by its ID
    public static void deleteSong(int songID) {
        String query = "DELETE FROM Songs WHERE SongID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, songID);
            statement.executeUpdate();
            System.out.println("Song deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting song.");
            e.printStackTrace();
        }
    }
}
