package com.dyspersja.mp3metadataeditor.filechooser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.File;

public class FileChooserController {

    @FXML // fx:id="choseFileButton"
    private Button choseFileButton;

    @FXML
    @Setter
    private Stage window;

    public void choseFile(ActionEvent event) {
        File chosenFile = openFileChooser();
        if (chosenFile != null) {
            openEditorWindow(chosenFile);
        }
    }

    private File openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        ExtensionFilter extensionFilter = new ExtensionFilter("MP3 files (*.mp3)", "*.mp3");
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser.showOpenDialog(window);
    }

    private void openEditorWindow(File chosenFile) {

    }
}
