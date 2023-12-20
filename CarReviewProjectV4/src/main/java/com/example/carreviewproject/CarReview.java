package com.example.carreviewproject;

import java.util.Arrays;
import java.text.DecimalFormat;

public class CarReview extends VehicleReview {

    //CAR MAKES
    public enum CarMake {
        ALFA_ROMEO,
        ASTON_MARTIN,
        AUDI,
        BENTLEY,
        BMW,
        BUICK,
        CADILLAC,
        CHEVROLET,
        CHRYSLER,
        DODGE,
        FERRARI,
        FIAT,
        FORD,
        GMC,
        HONDA,
        HYUNDAI,
        INFINITI,
        JAGUAR,
        JEEP,
        KIA,
        LAMBORGHINI,
        LAND_ROVER,
        LEXUS,
        LINCOLN,
        LOTUS,
        MASERATI,
        MAZDA,
        MCLAREN,
        MERCEDES_BENZ,
        MINI,
        MITSUBISHI,
        NISSAN,
        PORSCHE,
        RAM,
        ROLLS_ROYCE,
        SUBARU,
        TESLA,
        TOYOTA,
        VOLKSWAGEN,
        VOLVO
    }

    //CAR FUEL TYPES
    public enum CarFuelType {
        GASOLINE,
        DIESEL,
        ELECTRICITY,
        ETHANOL
    }

    //DEFAULT VARIABLES
    public static final CarMake DEFAULT_CARMAKE = CarMake.TOYOTA;
    public static final String DEFAULT_CARMODEL = "Corolla";
    public static final CarFuelType DEFAULT_FUELTYPE = CarFuelType.GASOLINE;

    public static final double AVERAGE_PRICE_PER_GALLON_OF_DIESEL = 5.60;
    public static final double AVERAGE_PRICE_PER_GALLON_OF_ETHANOL = 3.20;

    //INSTNACE VARIABLES
    private CarMake carMake;
    private String carModel;
    private CarFuelType carFuelType;

    //DEFAULT CONSTRUCTOR
    public CarReview(){
        super();
        this.setCarMake(DEFAULT_CARMAKE);
        this.setCarModel(DEFAULT_CARMODEL);
        this.setCarFuelType(DEFAULT_FUELTYPE);
    }

    //FULL CONSTRUCTOR
    public CarReview(String username, double reviewScore, String review, CarMake carMake, String carModel, int year, double mpg,
                     double milesPerKWH, CarFuelType carFuelType) {
        super(username, reviewScore, review, year, mpg, milesPerKWH);
        if(!this.setCarMake(carMake) || !this.setCarModel(carModel) || !this.setCarFuelType(carFuelType)) {
            System.out.println("Error: Invalid data provided to the full constructor in Car Review class");
            System.exit(0);
        }
    }

    //Copy Conctructor
    public CarReview(CarReview original){
        if(original != null)
        {
            this.setAll(original.getUsername(), original.getReviewScore(), original.getReview(), original.getCarMake(),
                    original.getCarModel(), original.getYear(), original.getMPG(), original.getMilesPerKWH(), original.getCarFuelType());
        }
        else{
            System.out.println("Error: Null data provided to Copy Constructor in Car Review clas");
            System.exit(0);
        }
    }

    //GETTERS
    public CarMake getCarMake() {
        return carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public CarFuelType getCarFuelType() {
        return carFuelType;
    }

    //SETTERS
    public boolean setCarMake(CarMake carMake) {
        if (carMake != null && Arrays.asList(CarMake.values()).contains(carMake)) { //converts the enums //into a list and iterates through list to see if the carMake exists
            this.carMake = carMake;
            return true;
        } else {
            System.out.println("Invalid car make: " + carMake);
            return false;
        }
    }

    public boolean setCarModel(String carModel) {
        if (carModel != null && !carModel.isEmpty()) {
            this.carModel = carModel;
            return true;
        } else {
            System.out.println("Car Model passed in as Null or Empty");
            return false;
        }
    }

    public boolean setCarFuelType(CarFuelType carFuelType) {
        if (carFuelType != null && Arrays.asList(CarFuelType.values()).contains(carFuelType)) { //converts the enums into a list and iterates through list to see if the carFuelType exists
            this.carFuelType = carFuelType;
            return true;
        } else {
            System.out.println("Invalid car fuel type: " + carFuelType);
            return false;
        }
    }

    //SET ALL METHOD
    public boolean setAll(String username,double reviewScore, String review, CarMake carMake, String carModel, int year, double mpg, double milesPerKWH, CarFuelType
            carFuelType) //used the super set all in the Vehicle Review class
    {
        return (super.setAll(username,reviewScore,review,year,mpg,milesPerKWH)
                && this.setCarMake(carMake)
                && this.setCarModel(carModel)
                && this.setCarFuelType(carFuelType));
    }

    //EQUALS METHOD
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof CarReview)) {
            return false;
        }
        CarReview otherCar = (CarReview) other;
        return (super.equals(other) &&
                this.carMake.equals(otherCar.carMake) &&
                this.carModel.equals(otherCar.carModel) &&
                this.carFuelType.equals(otherCar.carFuelType));
    }

    //ABSTRACT METHOD
    @Override
    public double calculateAnnualFuelCost() {
        double totalMiles = AVERAGE_MILES_DRIVEN;
        double fuelEfficiency;

        // Determine fuel efficiency based on the fuel type
        switch (carFuelType) {
            case GASOLINE:
            case DIESEL:
            case ETHANOL:
                fuelEfficiency = getMPG();
                break;
            case ELECTRICITY:
                fuelEfficiency = getMilesPerKWH();
                break;
            default:
                fuelEfficiency = 0.0; // Default to 0 if fuel type is unknown
                break;
        }

        if (fuelEfficiency == 0.0) {
            // Handle the case where fuel efficiency is zero to avoid division by zero
            return Double.POSITIVE_INFINITY; // Or any other suitable value or error handling
        }

        // Adjust costPerUnitOfEnergy based on fuel type
        double costPerUnitOfEnergy;
        switch (carFuelType) {
            case GASOLINE:
                costPerUnitOfEnergy = VehicleReview.AVERAGE_PRICE_PER_GALLON_OF_GASLOLINE;
                break;
            case ELECTRICITY:
                costPerUnitOfEnergy = VehicleReview.AVERAGE_PRICE_PER_KWH;
                break;
            case DIESEL:
                costPerUnitOfEnergy = AVERAGE_PRICE_PER_GALLON_OF_DIESEL;
                break;
            case ETHANOL:
                costPerUnitOfEnergy = AVERAGE_PRICE_PER_GALLON_OF_ETHANOL;
                break;
            default:
                costPerUnitOfEnergy = 0.0; // Default to 0 if fuel type is unknown
                break;
        }

        // Calculate and return the annual fuel cost
        return (totalMiles / fuelEfficiency) * costPerUnitOfEnergy;
    }

    //TO STRING METHOD
    @Override
    public String toString() {
        // Use DecimalFormat for formatting the annual fuel cost
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String formattedFuelCost = decimalFormat.format(calculateAnnualFuelCost());

        return "CarReview [" +
                "Username = '" + getUsername() + '\'' +
                ", ReviewScore = " + getReviewScore() +
                ", Review = '" + getReview() + '\'' +
                ", CarMake = " + getCarMake().name() +
                ", CarModel = '" + getCarModel() + '\'' +
                ", Year = " + getYear() +
                ", MPG = " + getMPG() +
                ", Miles Per KWH = " + getMilesPerKWH() +
                ", CarFuelType = " + getCarFuelType() +
                ", AnnualFuelCost = $" + formattedFuelCost + // Include formatted annual fuel cost
                " ]";
    }
}
