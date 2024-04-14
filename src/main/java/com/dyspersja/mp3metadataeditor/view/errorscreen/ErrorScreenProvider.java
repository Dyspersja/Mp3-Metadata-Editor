package com.dyspersja.mp3metadataeditor.view.errorscreen;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ErrorScreenProvider {

    public static void displayFXMLErrorWindow(Exception e) {
        String headerText = "Error Loading FXML";
        String contentText = "An error occurred while loading the FXML file: " + e.getMessage();

        displayErrorWindow(headerText, contentText);
    }

    public static void displayFileReadErrorWindow(Exception e) {
        String headerText = "Error Reading File";
        String contentText = "An error occurred while reading the file: " + e.getMessage();

        displayErrorWindow(headerText, contentText);
    }

    public static void displayFileWriteErrorWindow(Exception e) {
        String headerText = "Error Writing to a File";
        String contentText = "An error occurred while writing to the file: " + e.getMessage();

        displayErrorWindow(headerText, contentText);
    }

    private static void displayErrorWindow(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
        Platform.exit();
    }
}
