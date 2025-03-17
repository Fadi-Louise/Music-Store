package com.example.musicstore;

import com.example.musicstore.ArtistHomeController;
import com.example.musicstore.util.Authentication;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

    @FXML
    private Button loginBtn;

    @FXML
    private CheckBox artistCB; // Checkbox to indicate if the user is an artist

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Label statusLabel;

    // Action handler for the sign-up button
    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {
        String username = usernameTF.getText().trim();
        String password = passwordPF.getText().trim();
        String email = emailTF.getText().trim();

        // Check for empty fields
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            statusLabel.setVisible(true);
            return; // Exit the method to prevent further execution
        }

        try {
            if (artistCB.isSelected()) {
                // Perform registration for artist
                if (!Authentication.registerArtist(username, email, password)) {
                    statusLabel.setText("Registration failed. Please try again.");
                    statusLabel.setVisible(true);
                    return; // Exit the method if registration fails
                }

                // Load the ArtistHome.fxml and pass the username to set up the page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ArtistHome.fxml"));
                Parent root = loader.load();

                ArtistHomeController artistHomeController = loader.getController();
                artistHomeController.useUsernameToSetUpPage(username);
                switchToScene(event, root);

            } else {
                // Perform registration for a regular user
                if (!Authentication.registerUser(username, email, password)) {
                    statusLabel.setText("Registration failed. Please try again.");
                    statusLabel.setVisible(true);
                    return; // Exit the method if registration fails
                }

                // Load the UserHome.fxml and pass the username to set up the page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserHome.fxml"));
                Parent root = loader.load();

                com.example.musicstore.UserHomeController userHomeController = loader.getController();
                userHomeController.useUsernameToSetUpPage(username);
                switchToScene(event, root);
            }
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Error loading the next scene.");
            statusLabel.setVisible(true);
        }
    }

    // Action handler for the login button
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            switchToScene(event, root);
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Error loading the login scene.");
            statusLabel.setVisible(true);
        }
    }

    // Helper method to switch scenes
    private void switchToScene(ActionEvent event, Parent root) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic if needed
    }
}
