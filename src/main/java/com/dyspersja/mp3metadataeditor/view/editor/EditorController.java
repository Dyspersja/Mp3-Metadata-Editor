package com.dyspersja.mp3metadataeditor.view.editor;

import com.dyspersja.mp3metadataeditor.view.errorscreen.ErrorScreenProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

public class EditorController {

    @FXML // fx:id="saveFileButton"
    private Button saveFileButton;

    @FXML // fx:id="ID3v2editorTitledPane"
    private TitledPane ID3v2editorTitledPane;
    @FXML // fx:id="createID3v2Button"
    private Button createID3v2Button;

    @FXML // fx:id="ID3v1editorTitledPane"
    private TitledPane ID3v1editorTitledPane;
    @FXML // fx:id="createID3v1Button"
    private Button createID3v1Button;

    @FXML
    @Setter
    private Stage window;

    private ID3v2ContentController id3v2Controller;
    private ID3v1ContentController id3v1Controller;

    @FXML
    public void initialize() {
        loadID3v2Controller();
        loadID3v1Controller();
    }

    private void loadID3v2Controller() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ID3v2ContentPane.fxml"));
            AnchorPane pane = fxmlLoader.load();

            id3v2Controller = fxmlLoader.getController();
            id3v2Controller.setWindow(window);

            ID3v2editorTitledPane.setContent(pane);
        } catch (IllegalStateException | IOException e) {
            ErrorScreenProvider.displayErrorWindow(e);
        }
    }

    private void loadID3v1Controller() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ID3v1ContentPane.fxml"));
            AnchorPane pane = fxmlLoader.load();

            id3v1Controller = fxmlLoader.getController();

            ID3v1editorTitledPane.setContent(pane);
        } catch (IllegalStateException | IOException e) {
            ErrorScreenProvider.displayErrorWindow(e);
        }
    }

    @FXML
    void createID3v1(ActionEvent event) {

    }

    @FXML
    void createID3v2(ActionEvent event) {

    }

    @FXML
    void saveFile(ActionEvent event) {

    }

    public void setFile(File file) {

    }
}
