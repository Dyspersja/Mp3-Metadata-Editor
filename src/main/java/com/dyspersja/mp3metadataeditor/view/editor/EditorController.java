package com.dyspersja.mp3metadataeditor.view.editor;

import com.dyspersja.mp3metadataeditor.mp3.Mp3File;
import com.dyspersja.mp3metadataeditor.view.errorscreen.ErrorScreenProvider;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.stage.Modality;
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

    private Mp3File mp3File;

    @FXML
    public void initialize() {
        var initializer = new EditorInitializer(ID3v2editorTitledPane, ID3v1editorTitledPane);

        id3v1Controller = initializer.initializeID3v1Controller();
        id3v2Controller = initializer.initializeID3v2Controller();
        id3v2Controller.setWindow(window);
    }

    @FXML
    void createID3v1(ActionEvent event) {
        mp3File.createID3v1();
        activateID3v1TitledPane();
    }

    @FXML
    void createID3v2(ActionEvent event) {
        mp3File.createID3v2();
        activateID3v2TitledPane();
    }

    private void activateID3v1TitledPane(){
        ID3v1editorTitledPane.setCollapsible(true);
        ID3v1editorTitledPane.setExpanded(true);
        createID3v1Button.setDisable(true);
    }

    private void activateID3v2TitledPane(){
        ID3v2editorTitledPane.setCollapsible(true);
        ID3v2editorTitledPane.setExpanded(true);
        createID3v2Button.setDisable(true);
    }

    public void setFile(File file) {
        mp3File = new Mp3File(file);

        if (mp3File.isID3v2Present()) {
            id3v2Controller.setInitialValues(mp3File.getId3v2());
            activateID3v2TitledPane();
        }

        if (mp3File.isID3v1Present()) {
            id3v1Controller.setInitialValues(mp3File.getId3v1());
            activateID3v1TitledPane();
        }
    }

    @FXML
    void saveFile(ActionEvent event) {
        try {
            if (mp3File.isID3v2Present()) mp3File.setId3v2(id3v2Controller.getMetadata());
            if (mp3File.isID3v1Present()) mp3File.setId3v1(id3v1Controller.getMetadata());
        } catch (Exception e) {
            displayIncorrectInputWindow();
            return;
        }

        mp3File.saveFile();
        Platform.exit();
    }

    private void displayIncorrectInputWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/IncorrectInputScreen.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Incorrect Input");
            stage.setResizable(false);

            stage.setScene(scene);
            stage.showAndWait();
        } catch (IllegalStateException | IOException e) {
            ErrorScreenProvider.displayFXMLErrorWindow(e);
        }
    }
}
