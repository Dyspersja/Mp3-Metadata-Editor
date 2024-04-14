package com.dyspersja.mp3metadataeditor.view.editor;

import com.dyspersja.mp3metadataeditor.view.errorscreen.ErrorScreenProvider;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class EditorInitializer {

    private static final String ID3v2_FXML = "ID3v2ContentPane.fxml";
    private static final String ID3v1_FXML = "ID3v1ContentPane.fxml";

    private final TitledPane ID3v2editorTitledPane;
    private final TitledPane ID3v1editorTitledPane;

    protected ID3v2ContentController initializeID3v2Controller() {
        return initializeController(ID3v2_FXML, ID3v2editorTitledPane);
    }

    protected ID3v1ContentController initializeID3v1Controller() {
        return initializeController(ID3v1_FXML, ID3v1editorTitledPane);
    }

    private <T> T initializeController(String fxmlFileName, TitledPane titledPane) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFileName));
            AnchorPane pane = fxmlLoader.load();

            T controller = fxmlLoader.getController();

            titledPane.setContent(pane);
            return controller;
        } catch (IllegalStateException | IOException e) {
            ErrorScreenProvider.displayFXMLErrorWindow(e);
            return null;
        }
    }
}
