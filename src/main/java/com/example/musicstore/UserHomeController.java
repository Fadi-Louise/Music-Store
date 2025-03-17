package com.example.musicstore;

import com.example.musicstore.model.Song;
import com.example.musicstore.service.SongService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.musicstore.SceneController.switchToScene;

public class UserHomeController implements Initializable {

    @FXML
    private Label welcomeLabel;
    @FXML
    private FlowPane songsFlowPane; // Holds song cells
    @FXML
    private TextField searchField;
    private String userUsername;
    // Removed userUserID as requested
    private List<Song> purchasedSongs = new ArrayList<>(); // Library

    // Method to set the username and update the label
    public void useUsernameToSetUpPage(String username) {
        welcomeLabel.setText("Welcome " + username);
        userUsername = username;
    }

    @FXML
    private void fillSongsFlowPane() {
        songsFlowPane.getChildren().clear();
        List<Song> allSongs = SongService.getAllSongs();
        for (Song song : allSongs) {
            songsFlowPane.getChildren().add(createSongCell(song));
        }
    }

    private VBox createSongCell(Song song) {
        Label titleLabel = new Label(song.getTitle());
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label priceLabel = new Label("$" + song.getPrice());
        priceLabel.setTextFill(Color.WHITE);
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        HBox titlePriceBox = new HBox(titleLabel, priceLabel);
        titlePriceBox.setAlignment(Pos.CENTER);
        titlePriceBox.setSpacing(5);

        String imagePath = "/com/example/musicstore/assets/" + song.getTitle() + ".png";
        if (getClass().getResource(imagePath) == null) {
            imagePath = "/com/example/musicstore/assets/default_image.png";
        }

        InputStream imageStream = getClass().getResourceAsStream(imagePath);
        ImageView imageView = new ImageView(new Image(imageStream));
        imageView.setFitWidth(200);
        imageView.setFitHeight(250);

        imageView.setOnMouseClicked(event -> showPurchaseDialog(song));

        VBox vbox = new VBox(imageView, titlePriceBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);
        vbox.setStyle("-fx-background-color: black");

        return vbox;
    }

    private void showPurchaseDialog(Song song) {
        Alert purchaseAlert = new Alert(Alert.AlertType.CONFIRMATION);
        purchaseAlert.setTitle("Purchase Confirmation");
        purchaseAlert.setHeaderText("Do you want to purchase " + song.getTitle() + "?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        purchaseAlert.getButtonTypes().setAll(yesButton, cancelButton);

        purchaseAlert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                addToLibrary(song);
            }
        });
    }

    private void showLibrary() {
        songsFlowPane.getChildren().clear();
        if (purchasedSongs.isEmpty()) {
            Label emptyLabel = new Label("Your library is empty.");
            emptyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            emptyLabel.setTextFill(Color.RED);
            songsFlowPane.getChildren().add(emptyLabel);
            return;
        }
        for (Song song : purchasedSongs) {
            songsFlowPane.getChildren().add(createSongCell(song));
        }
    }

    @FXML
    private void handleLibraryButtonAction() {
        showLibrary();
    }

    @FXML
    private void handleSearchActionButton(ActionEvent event) {
        songsFlowPane.getChildren().clear();
        List<Song> searchedSongs = SongService.getSongsByTitle(searchField.getText());
        for (Song song : searchedSongs) {
            songsFlowPane.getChildren().add(createSongCell(song));
        }
    }

    private void addToLibrary(Song song) {
        purchasedSongs.add(song);
        System.out.println("Added to library: " + song.getTitle());
        showLibrary();
    }

    @FXML
    private void handleLogoutActionButton(ActionEvent event) throws IOException {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("Login.fxml"));
        switchToScene(event, loginRoot);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillSongsFlowPane();
    }
}
