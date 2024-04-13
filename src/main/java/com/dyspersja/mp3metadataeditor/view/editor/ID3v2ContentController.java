package com.dyspersja.mp3metadataeditor.view.editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.Setter;

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
}
