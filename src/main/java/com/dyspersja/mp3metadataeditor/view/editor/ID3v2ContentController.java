package com.dyspersja.mp3metadataeditor.view.editor;

import com.dyspersja.mp3metadataeditor.mp3.metadata.ID3v2Metadata;
import com.dyspersja.mp3metadataeditor.view.errorscreen.ErrorScreenProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;

public class ID3v2ContentController {

    @FXML // fx:id="ID3v2AlbumArtistTextField"
    private TextField ID3v2AlbumArtistTextField;
    @FXML // fx:id="ID3v2AlbumTextField"
    private TextField ID3v2AlbumTextField;
    @FXML // fx:id="ID3v2SongArtistTextField"
    private TextField ID3v2SongArtistTextField;
    @FXML // fx:id="ID3v2TitleTextField"
    private TextField ID3v2TitleTextField;
    @FXML // fx:id="ID3v2TrackTextField"
    private TextField ID3v2TrackTextField;
    @FXML // fx:id="ID3v2YearTextField"
    private TextField ID3v2YearTextField;

    @FXML // fx:id="ID3v2AlbumCoverImageView"
    private ImageView ID3v2AlbumCoverImageView;
    @FXML // fx:id="changeAlbumCoverButton"
    private Button changeAlbumCoverButton;

    @FXML
    @Setter
    private Stage window;

    private byte[] imageData;

    @FXML
    void changeAlbumCover(ActionEvent event) {
        File chosenFile = openFileChooser();
        if (chosenFile == null) return;

        try {
            this.imageData = Files.readAllBytes(chosenFile.toPath());
            Image newAlbumCover = getImageFromByteArray(this.imageData);
            ID3v2AlbumCoverImageView.setImage(newAlbumCover);
        } catch (IOException e) {
            ErrorScreenProvider.displayFileReadErrorWindow(e);
        }
    }

    private File openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("Image files (*.jpeg, *.jpg)", "*.jpeg", "*.jpg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser.showOpenDialog(window);
    }

    public void setInitialValues(ID3v2Metadata metadata) {
        ID3v2AlbumArtistTextField.setText(metadata.getAlbumArtist());
        ID3v2AlbumTextField.setText(metadata.getAlbum());
        ID3v2SongArtistTextField.setText(metadata.getSongArtist());
        ID3v2TitleTextField.setText(metadata.getTitle());
        ID3v2TrackTextField.setText(metadata.getTrack());
        ID3v2YearTextField.setText(metadata.getYear());

        this.imageData = metadata.getAlbumCover();
        ID3v2AlbumCoverImageView.setImage(imageData != null
                ? getImageFromByteArray(imageData)
                : loadMissingImage());
    }

    private Image getImageFromByteArray(byte[] imageData) {
        return new Image(new ByteArrayInputStream(imageData));
    }

    private Image loadMissingImage() {
        URL imageUrl = getClass().getResource("/images/MissingImage.png");

        return imageUrl != null
                ? new Image(imageUrl.toExternalForm())
                : null;
    }

    public ID3v2Metadata getMetadata() {
        return ID3v2Metadata.builder()
                .isPresent(true)
                .albumArtist(ID3v2AlbumArtistTextField.getText())
                .album(ID3v2AlbumTextField.getText())
                .songArtist(ID3v2SongArtistTextField.getText())
                .title(ID3v2TitleTextField.getText())
                .track(ID3v2TrackTextField.getText())
                .year(ID3v2YearTextField.getText())
                .albumCover(imageData)
                .build();
    }
}
