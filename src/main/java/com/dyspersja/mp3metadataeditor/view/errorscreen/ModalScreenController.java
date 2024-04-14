package com.dyspersja.mp3metadataeditor.view.errorscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Setter;

public class ModalScreenController {

    @FXML // fx:id="okButton"
    private Button okButton;

    @FXML
    @Setter
    Stage window;

    @FXML
    void closeWindow(ActionEvent event) {
        window.close();
    }
}
