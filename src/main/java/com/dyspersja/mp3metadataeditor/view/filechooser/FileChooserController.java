package com.dyspersja.mp3metadataeditor.view.filechooser;

import com.dyspersja.mp3metadataeditor.view.editor.EditorController;
import com.dyspersja.mp3metadataeditor.view.errorscreen.ErrorScreenProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

public class FileChooserController {

    @FXML // fx:id="choseFileButton"
    private Button choseFileButton;

    @FXML
    @Setter
    private Stage window;

    @FXML
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/EditorScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            EditorController controller = fxmlLoader.getController();
            controller.setWindow(window);
            controller.setFile(chosenFile);

            window.setTitle("Mp3 Metadata Editor");
            window.setScene(scene);
        } catch (IllegalStateException | IOException e) {
            ErrorScreenProvider.displayFXMLErrorWindow(e);
        }
    }
}
