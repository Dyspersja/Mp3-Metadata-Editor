package com.dyspersja.mp3metadataeditor.filechooser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Setter;

public class FileChooserController {

    @FXML // fx:id="choseFileButton"
    private Button choseFileButton;

    @FXML
    @Setter
    private Stage window;

    public void choseFile(ActionEvent event) {

    }
}
