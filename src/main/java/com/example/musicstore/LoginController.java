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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private Button loginBtn;

    @FXML
    private CheckBox artistCB; // Checkbox to indicate if the user is an artist

    @FXML
    private Button signUpBtn;

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordPF;

    @FXML
    private Label statusLabel;

    // Action handler for the login button
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameTF.getText().trim();
        String password = passwordPF.getText().trim();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty()) {
            showStatusMessage("Please enter both username and password.");
            return;
        }

        try {
            // Perform authentication
            boolean isAuthenticated;
            if (artistCB.isSelected()) {
                isAuthenticated = Authentication.authenticateArtist(username, password);
            } else {
                isAuthenticated = Authentication.authenticateUser(username, password);
            }

            if (!isAuthenticated) {
                showStatusMessage("Login failed. Incorrect username or password! Try Again.");
                return;
            }

            // Load the appropriate home scene based on the checkbox
            FXMLLoader loader;
            Parent root;
            if (artistCB.isSelected()) {
                loader = new FXMLLoader(getClass().getResource("ArtistHome.fxml")); // Load ArtistHome.fxml
                root = loader.load();

                ArtistHomeController artistHomeController = loader.getController();
                artistHomeController.useUsernameToSetUpPage(username);
            } else {
                loader = new FXMLLoader(getClass().getResource("UserHome.fxml")); // Load UserHome.fxml
                root = loader.load();

                com.example.musicstore.UserHomeController userHomeController = loader.getController();
                userHomeController.useUsernameToSetUpPage(username);
            }

            switchToScene(event, root);
        } catch (IOException e) {
            e.printStackTrace();
            showStatusMessage("Error loading the next scene.");
        }
    }

    // Method to show status messages
    private void showStatusMessage(String message) {
        statusLabel.setText(message);
        statusLabel.setVisible(true);
    }

    // Mouse enter event to change button color
    @FXML
    void handleMouseEnter() {
        setBackground("#FFFFFF", "#000000");
    }

    // Mouse exit event to reset button color
    @FXML
    void handleMouseExit() {
        setBackground("#00FF00", "#000000");
    }

    // Method to set the background and text color of the button
    public void setBackground(String bgColor, String textColor) {
        BackgroundFill bgFill = new BackgroundFill(Color.web(bgColor), null, null);
        loginBtn.setBackground(new Background(bgFill));
        loginBtn.setTextFill(Color.web(textColor));
    }

    // Action handler for the sign-up button
    @FXML
    void handleSignUpButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml")); // Load SignUp.fxml
            Parent root = loader.load();
            switchToScene(event, root);
        } catch (IOException e) {
            e.printStackTrace();
            showStatusMessage("Error loading the sign-up scene.");
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
        // Set initial state for UI elements, if needed
        statusLabel.setVisible(false); // Hide the status label on startup
    }

    public Button getSignUpBtn() {
        return signUpBtn;
    }

    public void setSignUpBtn(Button signUpBtn) {
        this.signUpBtn = signUpBtn;
    }
}
