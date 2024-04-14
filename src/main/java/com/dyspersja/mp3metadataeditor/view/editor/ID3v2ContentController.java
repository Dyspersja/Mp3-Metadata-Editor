package com.dyspersja.mp3metadataeditor.view.editor;

import com.dyspersja.mp3metadataeditor.mp3.metadata.ID3v2Metadata;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.ByteArrayInputStream;
import java.net.URL;

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

    @FXML
    void changeAlbumCover(ActionEvent event) {

    }

    public void setInitialValues(ID3v2Metadata metadata) {
        ID3v2AlbumArtistTextField.setText(metadata.getAlbumArtist());
        ID3v2AlbumTextField.setText(metadata.getAlbum());
        ID3v2SongArtistTextField.setText(metadata.getSongArtist());
        ID3v2TitleTextField.setText(metadata.getTitle());
        ID3v2TrackTextField.setText(metadata.getTrack());
        ID3v2YearTextField.setText(metadata.getYear());

        byte[] imageData = metadata.getAlbumCover();
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
}
