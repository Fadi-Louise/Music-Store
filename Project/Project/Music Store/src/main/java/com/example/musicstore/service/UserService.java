package com.example.musicstore.service;

import com.example.musicstore.model.User;
import com.example.musicstore.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // Establishing connection
    private static final Connection connection = DatabaseConnection.getConnection();

    // Method to retrieve a user by their email
    public static User getUserByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM User WHERE Email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userID = resultSet.getInt("UserID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                user = new User(username, email, password);
                user.setUserID(userID);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user by email.");
            e.printStackTrace();
        }
        return user;
    }

    // Method to retrieve a user by their username
    public static User getUserByUsername(String username) {
        User user = null;
        String query = "SELECT * FROM User WHERE Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userID = resultSet.getInt("UserID");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                user = new User(username, email, password);
                user.setUserID(userID);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user by username.");
            e.printStackTrace();
        }
        return user;
    }
    public static int getUserIdByUsername(String username) {
        // Query database to get user ID based on username
        String query = "SELECT UserID FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("UserID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // return -1 or handle if user ID is not found
    }

    // Method to add a new user to the database
    public static void addUser(User user) {
        String query = "INSERT INTO User (Username, Email, Password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding user.");
            e.printStackTrace();
        }
    }

    // Method to update a user's information by their email
    public static void updateUserByEmail(String email, User updatedUser) {
        String query = "UPDATE User SET Username = ?, Password = ? WHERE Email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, updatedUser.getUsername());
            statement.setString(2, updatedUser.getPassword());
            statement.setString(3, email);
            statement.executeUpdate();
            System.out.println("User updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating user.");
            e.printStackTrace();
        }
    }

    // Method to delete a user from the database
    public static void deleteUser(int userID) {
        String query = "DELETE FROM User WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            statement.executeUpdate();
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting user.");
            e.printStackTrace();
        }
    }
}
