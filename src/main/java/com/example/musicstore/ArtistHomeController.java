package com.example.musicstore;

import com.example.musicstore.model.Song;
import com.example.musicstore.service.ArtistService;
import com.example.musicstore.service.SongService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.musicstore.SceneController.switchToScene;

public class ArtistHomeController implements Initializable {

    @FXML
    public BorderPane bdPane;
    @FXML
    public Button libraryBtn;
    @FXML
    public Button createSongBtn;

    @FXML
    private Label welcomeLabel;
    @FXML
    private FlowPane songsFlowPane;
    @FXML
    private TextField searchField;

    private String userUsername;

    private VBox inputBox;

    // Set up the artist's homepage with the username
    public void useUsernameToSetUpPage(String username) {
        welcomeLabel.setText("Welcome Artist " + username);
        userUsername = username;
    }

    // Fill FlowPane with songs associated with the artist
    @FXML
    private void fillSongsFlowPane() {
        // Clear flow pane
        songsFlowPane.getChildren().clear();

        // Retrieve song data from the service
        List<Song> allSongs = SongService.getSongsByArtistUsername(userUsername);
        System.out.println("Number of songs found for user '" + userUsername + "': " + allSongs.size());

        // Create a cell for each song and add it to the FlowPane
        for (Song song : allSongs) {
            System.out.println("Processing song: " + song.getTitle() + " | Price: $" + song.getPrice() + " | Genre: " + song.getGenre());

            // Create labels for title and price
            Label titleLabel = createLabel(song.getTitle());
            Label priceLabel = createLabel("$" + song.getPrice());

            // Place title and price labels in an HBox
            HBox titlePriceBox = new HBox(titleLabel, priceLabel);
            titlePriceBox.setAlignment(Pos.CENTER);
            titlePriceBox.setSpacing(5);

            // Construct the file path for the image based on the song's title
            String imagePath = "/com/example/musicstore/assets/" + song.getTitle() + ".png";
            InputStream imageStream = getClass().getResourceAsStream(imagePath);

            // Load the image
            ImageView imageView = new ImageView();
            if (imageStream != null) {
                imageView.setImage(new Image(imageStream));
                imageView.setFitWidth(200);
                imageView.setFitHeight(250);
            } else {
                System.out.println("Image not found at path: " + imagePath + ", loading default image.");
                imagePath = "/com/example/musicstore/assets/default_image.png"; // Use default image path
                imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream != null) {
                    imageView.setImage(new Image(imageStream));
                } else {
                    System.out.println("Error: Default image not found at path: " + imagePath);
                    continue; // Skip this song if image loading fails
                }
            }

            // Create a VBox to hold the image and labels
            VBox vbox = new VBox(imageView, titlePriceBox);
            vbox.setStyle("-fx-background-color: black");
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(5);

            // Add the VBox to the FlowPane
            songsFlowPane.getChildren().add(vbox);
            System.out.println("Added song to display: " + song.getTitle());
        }

        configureFlowPane();
        System.out.println("Finished populating FlowPane with songs.");
    }

    private void configureFlowPane() {
        songsFlowPane.setHgap(60);
        songsFlowPane.setVgap(60);
        songsFlowPane.setPrefWrapLength(400);
        songsFlowPane.setPadding(new Insets(20));
        songsFlowPane.setStyle("-fx-background-color: #000000;");
    }

    // Logout handler
    @FXML
    private void handleLogoutActionButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        switchToScene(event, root);
    }

    // Search songs by title
    @FXML
    private void handleSearchActionButton(ActionEvent event) {
        // Placeholder: This should be adapted to filter based on search input
        fillSongsFlowPane();
    }

    @FXML
    private void handleCreateSongButton(ActionEvent event) {
        songsFlowPane.getChildren().clear();

        inputBox = new VBox(
                createLabel("Title:"), createTextField("Enter title"),
                createLabel("Price:"), createTextField("Enter price"),
                createLabel("Genre:"), createTextField("Enter genre"),
                createLabel("Description:"), createTextArea("Enter description")
        );
        inputBox.setAlignment(Pos.CENTER_LEFT);
        inputBox.setSpacing(10);

        Button submitButton = createSubmitButton();
        songsFlowPane.getChildren().addAll(inputBox, submitButton);
        configureFlowPane();
    }

    private Button createSubmitButton() {
        Button submitButton = new Button("Create");
        submitButton.setOnAction(e -> handleSubmitSong(
                ((TextField) inputBox.getChildren().get(1)).getText(),
                ((TextField) inputBox.getChildren().get(3)).getText(),
                ((TextField) inputBox.getChildren().get(5)).getText(),
                ((TextArea) inputBox.getChildren().get(7)).getText()
        ));
        submitButton.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white;");
        submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        return submitButton;
    }

    private void handleSubmitSong(String title, String price, String genre, String description) {
        LocalDate currentDate = LocalDate.now();
        Song song = new Song(title, description, genre, new BigDecimal(price), currentDate,
                ArtistService.getArtistByUsername(userUsername).getArtistID());
        SongService.addSong(song);
        fillSongsFlowPane();
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setTextFill(Color.WHITE);
        return label;
    }

    private TextField createTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        return textField;
    }

    private TextArea createTextArea(String promptText) {
        TextArea textArea = new TextArea();
        textArea.setPromptText(promptText);
        textArea.setWrapText(true);
        return textArea;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillSongsFlowPane();
    }
}
