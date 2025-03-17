package com.example.springboot_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userID;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, message = "Username must have at least 3 characters")
    @Column(name = "username", unique = true)
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Please provide a valid email")
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must have at least 8 characters")
    @Column(name = "password")
    private String password;

    @Column(name = "Balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance = new BigDecimal("10.00"); // Default balance set to 10.00

    // Constructors
    public User() {
    }

    public User(String username, String email, String password, BigDecimal balance) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setBalance(balance);
    }

    public User(String username, String password, BigDecimal balance) {
        setUsername(username);
        setPassword(password);
        setBalance(balance);
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    // toString method (for debugging/logging purposes)
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
