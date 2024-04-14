package com.dyspersja.mp3metadataeditor.view.editor;

import com.dyspersja.mp3metadataeditor.mp3.metadata.ID3v1Metadata;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ID3v1ContentController {

    @FXML // fx:id="ID3v1AlbumTextField"
    private TextField ID3v1AlbumTextField;
    @FXML // fx:id="ID3v1ArtistTextField"
    private TextField ID3v1ArtistTextField;
    @FXML // fx:id="ID3v1CommentTextField"
    private TextField ID3v1CommentTextField;
    @FXML // fx:id="ID3v1TitleTextField"
    private TextField ID3v1TitleTextField;
    @FXML // fx:id="ID3v1TrackTextField"
    private TextField ID3v1TrackTextField;
    @FXML // fx:id="ID3v1YearTextField"
    private TextField ID3v1YearTextField;

    public void setInitialValues(ID3v1Metadata metadata) {
        ID3v1AlbumTextField.setText(metadata.getAlbum());
        ID3v1ArtistTextField.setText(metadata.getArtist());
        ID3v1CommentTextField.setText(metadata.getComment());
        ID3v1TitleTextField.setText(metadata.getTitle());
        ID3v1TrackTextField.setText(String.valueOf(metadata.getTrack()));
        ID3v1YearTextField.setText(metadata.getYear());
    }
}
