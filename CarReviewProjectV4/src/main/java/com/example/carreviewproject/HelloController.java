package com.example.carreviewproject;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

public class HelloController {

    @FXML
    private ComboBox<String> reviewTypeComboBox;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField reviewScoreField;
    @FXML
    private TextField reviewField;
    @FXML
    private ComboBox<String> makeDropdown;
    @FXML
    private TextField modelField;
    @FXML
    private TextField yearField;
    @FXML
    private ComboBox<String> fuelTypeDropdown;
    @FXML
    private Label mpgFieldLabel;

    @FXML
    private TextField mpgField;

    @FXML
    private Label milesPerKwhFieldLabel;

    @FXML
    private TextField milesPerKwhField;


    private ReviewBoard reviewBoard;



    // Setter method to set the shared ReviewBoard instance
    public void setReviewBoard(ReviewBoard reviewBoard) {
        this.reviewBoard = reviewBoard;
    }



    @FXML
    protected void onAddReviewButtonClick() {
        String reviewType = reviewTypeComboBox.getValue();
        if (reviewType != null) {
            switch (reviewType) {
                case "Car":
                    addCarReview();
                    break;
                case "Motorcycle":
                    addMotorcycleReview();
                    break;
            }
        }
    }

    @FXML
    private void initialize() {
        reviewTypeComboBox.setOnAction(event -> {
            updateMakeDropdown();
            updateFuelTypeFields();
            updateFuelTypeComboBox();
            });


        // Add a listener for the fuelTypeDropdown
        fuelTypeDropdown.setOnAction(event -> {
            updateFuelTypeFields();
        });
    }


    private void updateMakeDropdown() {
        String selectedType = reviewTypeComboBox.getValue();
        makeDropdown.getItems().clear(); // Clear existing items

        if ("Car".equals(selectedType)) {
            // Add car makes to the dropdown
            for (CarReview.CarMake carMake : CarReview.CarMake.values()) {
                makeDropdown.getItems().add(carMake.toString());
            }
        } else if ("Motorcycle".equals(selectedType)) {
            // Add motorcycle makes to the dropdown
            for (MotorcycleReview.MotorcycleMake motorcycleMake : MotorcycleReview.MotorcycleMake.values()) {
                makeDropdown.getItems().add(motorcycleMake.toString());
            }
        }
    }

    private void addCarReview() {
        try {
            String username = usernameField.getText();
            double reviewScore = Double.parseDouble(reviewScoreField.getText());
            String reviewText = reviewField.getText();
            String make = makeDropdown.getValue();
            String model = modelField.getText();
            int year = Integer.parseInt(yearField.getText());
            double mpgValue = 0.0;
            double milesPerKwhValue = 0.0; // had to set to 0.0 to prevent numberException errors
            String fuelType = fuelTypeDropdown.getValue();
            CarReview.CarFuelType carFuelType = CarReview.CarFuelType.valueOf(fuelType.toUpperCase());

            // Set values based on the selected fuel type
            if ("GASOLINE".equals(fuelType) || "DIESEL".equals(fuelType) || "ETHANOL".equals(fuelType)) {
                mpgValue = Double.parseDouble(mpgField.getText());
            } else if ("ELECTRICITY".equals(fuelType)) {
                milesPerKwhValue = Double.parseDouble(milesPerKwhField.getText());
            }

            System.out.println("After adjustment - mpgValue: " + mpgValue + ", milesPerKwhValue: " + milesPerKwhValue);
            CarReview carReview = new CarReview(username, reviewScore, reviewText,
                    CarReview.CarMake.valueOf(make.toUpperCase()), model, year,mpgValue,milesPerKwhValue,carFuelType);

            if (isValidCarReview(carReview)) {
                reviewBoard.addCarReview(carReview);
                System.out.println("Car Review added: " + carReview);
                clearFields();
            } else {
                // Get the instance of ErrorController and call the method
                ErrorController errorController = new ErrorController();
                errorController.displayError("Invalid Car Review parameters. Please check your input fields.");
            }
        } catch (NumberFormatException e) {
            // Get the instance of ErrorController and call the method
            e.printStackTrace();
            ErrorController errorController = new ErrorController();
            errorController.displayError("Invalid input. Please enter valid numeric values.");
        }
    }



    private boolean isValidCarReview(CarReview carReview) {
        // Check if the make is valid
        if (carReview.getCarMake() == null) {
            return false;
        }

        // Check if the model is not empty
        if (carReview.getCarModel().isEmpty()) {
            return false;
        }


        int currentYear = Year.now().getValue();
        if (carReview.getYear() < 1900 || carReview.getYear() > currentYear) {
            return false;
        }


        if (carReview.getReviewScore() < 0 || carReview.getReviewScore() > 10) {
            return false;
        }


        if (carReview.getUsername().isEmpty()) {
            return false;
        }


        if (carReview.getReview().isEmpty()) {
            return false;
        }

        return true;
    }


    private void addMotorcycleReview() {
        try {
            String username = usernameField.getText();
            double reviewScore = Double.parseDouble(reviewScoreField.getText());
            String reviewText = reviewField.getText();
            String make = makeDropdown.getValue();
            String model = modelField.getText();
            int year = Integer.parseInt(yearField.getText());
            MotorcycleReview.MotorcycleMake motorcycleMake = MotorcycleReview.MotorcycleMake.valueOf(make.toUpperCase());
            double mpgValue = 0.0;
            double milesPerKwhValue = 0.0;
            // Get the selected fuel type from the dropdown menu
            String selectedFuelType = fuelTypeDropdown.getValue();
            MotorcycleReview.MotorcycleFuelType fuelType = MotorcycleReview.MotorcycleFuelType.valueOf(selectedFuelType.toUpperCase());

            if ("GASOLINE".equals(selectedFuelType)) {
                mpgValue = Double.parseDouble(mpgField.getText());
            } else if ("ELECTRICITY".equals(selectedFuelType)) {
                milesPerKwhValue = Double.parseDouble(milesPerKwhField.getText());
            }

            System.out.println("After adjustment - mpgValue: " + mpgValue + ", milesPerKwhValue: " + milesPerKwhValue);


            // Create a MotorcycleReview object with fuel type
            MotorcycleReview motorcycleReview = new MotorcycleReview(username, reviewScore, reviewText,
                    motorcycleMake, model, year, mpgValue, milesPerKwhValue, fuelType);

            if (isValidMotorcycleReview(motorcycleReview)) {
                reviewBoard.addMotorcycleReview(motorcycleReview);
                System.out.println("Motorcycle Review added: " + motorcycleReview);
                clearFields();
            } else {
                ErrorController errorController = new ErrorController();
                errorController.displayError("Invalid Motorcycle Review parameters. Please check your input fields.");
            }
        } catch (NumberFormatException e) {
            ErrorController errorController = new ErrorController();
            errorController.displayError("Invalid input for review score or year. Please enter valid numeric values.");
        }
    }


    private boolean isValidMotorcycleReview(MotorcycleReview motorcycleReview) {

        if (motorcycleReview.getMotorcycleMake() == null) {
            return false;
        }


        if (motorcycleReview.getMotorcycleModel().isEmpty()) {
            return false;
        }

        int currentYear = Year.now().getValue();
        if (motorcycleReview.getYear() < 1900 || motorcycleReview.getYear() > currentYear) {
            return false;
        }

        if (motorcycleReview.getReviewScore() < 0 || motorcycleReview.getReviewScore() > 10) {
            return false;
        }

        if (motorcycleReview.getUsername().isEmpty()) {
            return false;
        }

        if (motorcycleReview.getReview().isEmpty()) {
            return false;
        }

        return true;
    }

    private void updateFuelTypeFields() {
        String selectedFuelType = fuelTypeDropdown.getValue();

        // Set visibility based on the selected fuel type
        if ("GASOLINE".equals(selectedFuelType) || "ETHANOL".equals(selectedFuelType) || "DIESEL".equals(selectedFuelType)) {
            mpgFieldLabel.setVisible(true);
            mpgField.setVisible(true);
            milesPerKwhFieldLabel.setVisible(false);
            milesPerKwhField.setVisible(false);
        } else if ("ELECTRICITY".equals(selectedFuelType)) {
            mpgFieldLabel.setVisible(false);
            mpgField.setVisible(false);
            milesPerKwhFieldLabel.setVisible(true);
            milesPerKwhField.setVisible(true);
        }
    }

    @FXML
    private void updateFuelTypeComboBox() {
        String selectedType = reviewTypeComboBox.getValue();
        fuelTypeDropdown.getItems().clear(); // Clear existing items

        if ("Car".equals(selectedType)) {
            for (CarReview.CarFuelType carFuelType : CarReview.CarFuelType.values()) {
                fuelTypeDropdown.getItems().add(carFuelType.toString());
            }
        } else if ("Motorcycle".equals(selectedType)) {
            for (MotorcycleReview.MotorcycleFuelType motorcycleFuelType : MotorcycleReview.MotorcycleFuelType.values()) {
                fuelTypeDropdown.getItems().add(motorcycleFuelType.toString());
            }
        }
    }

    private void clearFields() {
        usernameField.clear();
        reviewScoreField.clear();
        reviewField.clear();
        makeDropdown.getSelectionModel().clearSelection();
        modelField.clear();
        yearField.clear();
        mpgField.clear();
        milesPerKwhField.clear();
    }

}
