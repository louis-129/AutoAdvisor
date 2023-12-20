package com.example.carreviewproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ErrorController {

    @FXML
    private Label errorMessageLabel;

    @FXML
    public void displayError(String errorMessage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Error.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller instance
            ErrorController errorController = fxmlLoader.getController();

            // Set the error message
            errorController.setErrorMessage(errorMessage);

            Stage errorStage = new Stage();
            errorStage.setTitle("Error");
            errorStage.setScene(new Scene(root, 550, 100));
            errorStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setErrorMessage(String errorMessage) {
        errorMessageLabel.setText(errorMessage);
    }


}
