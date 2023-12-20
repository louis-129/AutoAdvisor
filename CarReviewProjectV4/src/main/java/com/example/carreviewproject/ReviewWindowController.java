package com.example.carreviewproject;

import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


import java.util.List;


public class ReviewWindowController {

    @FXML
    private ComboBox<String> reviewTypeComboBox;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label makeLabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label yearLabel;

    @FXML
    private Label reviewLabel;
    @FXML
    private Label mpgLabel;

    @FXML
    private Label milesPerKWHLabel;

    @FXML
    private Label estimatedFuelCostLabel;


    private ReviewBoard reviewBoard;
    private List<CarReview> carReviews;
    private List<MotorcycleReview> motorcycleReviews;
    private int currentCarReviewIndex = 0;
    private int currentMotorcycleReviewIndex = 0;


    public void setReviewBoard(ReviewBoard reviewBoard) {
        this.reviewBoard = reviewBoard;
        carReviews = reviewBoard.getCarReviewList();
        motorcycleReviews = reviewBoard.getMotorcycleReviewList();
        updateUIWithCurrentReview();
    }


    private void updateUIWithCurrentReview() {
        String selectedReviewType = reviewTypeComboBox.getValue();
        if ("Car".equals(selectedReviewType)) {
            updateCarReviewUI();
        } else if ("Motorcycle".equals(selectedReviewType)) {
            updateMotorcycleReviewUI();
        }
    }


    private void updateCarReviewUI() {
        if (carReviews != null && !carReviews.isEmpty()) {
            CarReview currentReview = carReviews.get(currentCarReviewIndex);
            updateUIWithReview(currentReview);
            currentCarReviewIndex = (currentCarReviewIndex + 1) % carReviews.size();
        }
    }


    private void updateMotorcycleReviewUI() {
        if (motorcycleReviews != null && !motorcycleReviews.isEmpty()) {
            MotorcycleReview currentReview = motorcycleReviews.get(currentMotorcycleReviewIndex);
            updateUIWithReview(currentReview);
            currentMotorcycleReviewIndex = (currentMotorcycleReviewIndex + 1) % motorcycleReviews.size();
        }
    }


    @FXML
    private void updateUIWithReview(VehicleReview review) {

        if (review instanceof CarReview) {
            CarReview carReview = (CarReview) review;
            usernameLabel.setText(" " + review.getUsername());
            scoreLabel.setText(" " + String.valueOf(review.getReviewScore()));
            makeLabel.setText(" " + carReview.getCarMake());
            modelLabel.setText(" " + carReview.getCarModel());
            yearLabel.setText(" " + String.valueOf(carReview.getYear()));
            reviewLabel.setText(" " + carReview.getReview());

            // Display fuelType information
            if (carReview.getCarFuelType() == CarReview.CarFuelType.GASOLINE ||
                    carReview.getCarFuelType() == CarReview.CarFuelType.DIESEL ||
                    carReview.getCarFuelType() == CarReview.CarFuelType.ETHANOL) {
                showMPG(carReview.getMPG());
                hideMilesPerKWH();
                showEstimatedAnnualFuelCost(carReview.calculateAnnualFuelCost());
            } else if (carReview.getCarFuelType() == CarReview.CarFuelType.ELECTRICITY) {
                hideMPG();
                showMilesPerKWH(carReview.getMilesPerKWH());
                showEstimatedAnnualFuelCost(carReview.calculateAnnualFuelCost());
            }
        } else if (review instanceof MotorcycleReview) {
            MotorcycleReview motorcycleReview = (MotorcycleReview) review;
            usernameLabel.setText(" " + review.getUsername());
            scoreLabel.setText(" " + String.valueOf(review.getReviewScore()));
            makeLabel.setText(" " + motorcycleReview.getMotorcycleMake());
            modelLabel.setText(" " + motorcycleReview.getMotorcycleModel());
            yearLabel.setText(" " + String.valueOf(motorcycleReview.getYear()));
            reviewLabel.setText(" " + motorcycleReview.getReview());
            if (motorcycleReview.getMotorcycleFuelType() == MotorcycleReview.MotorcycleFuelType.GASOLINE) {
                showMPG(motorcycleReview.getMPG());
                hideMilesPerKWH();
                showEstimatedAnnualFuelCost(motorcycleReview.calculateAnnualFuelCost());
            } else if (motorcycleReview.getMotorcycleFuelType() == MotorcycleReview.MotorcycleFuelType.ELECTRICITY) {
                hideMPG();
                showMilesPerKWH(motorcycleReview.getMilesPerKWH());
                showEstimatedAnnualFuelCost(motorcycleReview.calculateAnnualFuelCost());
            }
        }
    }

    // Updates the list of reviews based on the review type
    @FXML
    private void onReviewTypeSelected() {
        currentCarReviewIndex = 0;
        currentMotorcycleReviewIndex = 0;
        updateUIWithCurrentReview();
    }


    @FXML
    private void onNextReviewButtonClick() {
        String selectedReviewType = reviewTypeComboBox.getValue();
        if ("Car".equals(selectedReviewType)) {
            updateCarReviewUI();
        } else if ("Motorcycle".equals(selectedReviewType)) {
            updateMotorcycleReviewUI();
        }
    }

    //Fuel Type Methods
    private void showMPG(double mpg) {
        mpgLabel.setText(" MPG: " + String.valueOf(mpg));
        mpgLabel.setVisible(true);
    }

    private void hideMPG() {
        mpgLabel.setVisible(false);
    }

    private void showMilesPerKWH(double milesPerKWH) {
        milesPerKWHLabel.setText(" Miles Per KWH: " + String.valueOf(milesPerKWH));
        milesPerKWHLabel.setVisible(true);
    }

    private void hideMilesPerKWH() {
        milesPerKWHLabel.setVisible(false);
    }

    private void showEstimatedAnnualFuelCost(double annualFuelCost) {
        String formattedFuelCost = String.format("%.2f", annualFuelCost);
        estimatedFuelCostLabel.setText(" Estimated Annual Fuel Cost: $" + formattedFuelCost);
    }
}
