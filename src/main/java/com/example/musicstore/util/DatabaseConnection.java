package com.example.musicstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // JDBC URL for connecting to the Song_store database
    private static final String DB_URL = "jdbc:mysql://localhost:3307/Songs_store";
    // Database username
    private static final String DB_USER = "root";
    // Database password
    private static final String DB_PASSWORD = "root";
    // Connection object for managing database connections
    private static Connection connection;

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Method to retrieve a connection
    public static Connection getConnection() {
        try {
            // Check if connection is null or closed
            if (connection == null || connection.isClosed()) {
                // Establish a new connection
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Connection established!!");
            }
        } catch (SQLException e) {
            System.out.println("Connection not established!!");
            e.printStackTrace();
        }
        return connection;
    }

    // Method to close the connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Connection is not closed!!");
            e.printStackTrace();
        }
    }
}
