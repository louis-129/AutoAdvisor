package com.example.carreviewproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    private ReviewBoard reviewBoard;

    // Setter method to set to the shared ReviewBoard instance
    public void setReviewBoard(ReviewBoard reviewBoard) {
        this.reviewBoard = reviewBoard;
    }

    @FXML
    private void onOpenSecondWindowButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller and set the shared ReviewBoard instance
            HelloController helloController = fxmlLoader.getController();
            helloController.setReviewBoard(reviewBoard);

            // You can create a new stage for the second window
            Stage secondStage = new Stage();
            secondStage.setTitle("Create Vehicle Review");
            secondStage.setScene(new Scene(root, 520, 650));
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onOpenReviewsWindowButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReviewWindowController.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller and set the shared ReviewBoard instance
            ReviewWindowController reviewController = fxmlLoader.getController();
            reviewController.setReviewBoard(reviewBoard);

            // You can create a new stage for the review window
            Stage reviewStage = new Stage();
            reviewStage.setTitle("View Reviews");
            reviewStage.setScene(new Scene(root, 650, 600));
            reviewStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
