package com.example.carreviewproject;

import java.util.Arrays;

public class MotorcycleReview extends VehicleReview {

    public enum MotorcycleMake {
        APRILIA,
        BENELLI,
        BIMOTA,
        BMW,
        DUCATI,
        HARLEY_DAVIDSON,
        HONDA,
        HUSQVARNA,
        INDIAN,
        KAWASAKI,
        KTM,
        MOTO_GUZZI,
        MV_AGUSTA,
        SUZUKI,
        TRIUMPH,
        VICTORY,
        YAMAHA,
        ZERO
    }

    //CAR FUEL TYPES
    public enum MotorcycleFuelType {
        GASOLINE,
        ELECTRICITY
    }

    //DEFAULT VARIABLES
    public static final MotorcycleReview.MotorcycleMake DEFAULT_MOTORCYCLEMAKE = MotorcycleReview.MotorcycleMake.YAMAHA;
    public static final String DEFAULT_MOTORCYCLEMODEL = "MT-09 Tracer ABS";
    public static final MotorcycleReview.MotorcycleFuelType DEFAULT_FUELTYPE = MotorcycleReview.MotorcycleFuelType.GASOLINE;

    private MotorcycleMake motorcycleMake;
    private String motorcycleModel;
    private MotorcycleFuelType motorcycleFuelType;

    public MotorcycleReview(){
        super();
        this.setMotorcycleMake(DEFAULT_MOTORCYCLEMAKE);
        this.setMotorcycleModel(DEFAULT_MOTORCYCLEMODEL);
        this.setMotorcycleFuelType(DEFAULT_FUELTYPE);
    }
    public MotorcycleReview(String username, double reviewScore, String review, MotorcycleMake motorcycleMake,
                            String motorcycleModel, int year, double mpg, double milesPerKWH,
                            MotorcycleFuelType motorcycleFuelType) {
        super(username, reviewScore, review, year, mpg, milesPerKWH);
        if (!this.setMotorcycleMake(motorcycleMake) || !this.setMotorcycleModel(motorcycleModel) ||
                !this.setMotorcycleFuelType(motorcycleFuelType)) {
            System.out.println("Error: Invalid data provided to the full constructor in Car Review class");
            System.exit(0);
        }
    }

    //Copy Conctructor
    public MotorcycleReview(MotorcycleReview original){
        if(original != null)
        {
            this.setAll(original.getUsername(), original.getReviewScore(), original.getReview(), original.getMotorcycleMake(),
                    original.getMotorcycleModel(), original.getYear(), original.getMPG(), original.getMilesPerKWH(), original.getMotorcycleFuelType());
        }
        else{
            System.out.println("Error: Null data provided to Copy Constructor in Motorcycle Review clas");
            System.exit(0);
        }
    }

    public MotorcycleMake getMotorcycleMake()
    {
        return motorcycleMake;
    }

    public String getMotorcycleModel()
    {
        return motorcycleModel;
    }

    public MotorcycleFuelType getMotorcycleFuelType(){
        return motorcycleFuelType;
    }

    @Override
    public double calculateAnnualFuelCost() {
        double totalMiles = AVERAGE_MILES_DRIVEN; // You might want to replace this with actual miles driven by the user
        double fuelEfficiency;

        // Determine fuel efficiency based on the fuel type
        switch (motorcycleFuelType) {
            case GASOLINE:
                fuelEfficiency = getMPG();
                break;
            case ELECTRICITY:
                fuelEfficiency = getMilesPerKWH();
                break;
            default:
                fuelEfficiency = 0.0; // Adjust this value based on other fuel types if needed
                break;
        }

        if (fuelEfficiency == 0.0) {
            // Handle the case where fuel efficiency is zero to avoid division by zero
            return Double.POSITIVE_INFINITY; // Or any other suitable value or error handling
        }

        // Adjust costPerUnitOfEnergy based on fuel type
        double costPerUnitOfEnergy;
        switch (motorcycleFuelType) {
            case GASOLINE:
                costPerUnitOfEnergy = AVERAGE_PRICE_PER_GALLON_OF_GASLOLINE;
                break;
            case ELECTRICITY:
                costPerUnitOfEnergy = AVERAGE_PRICE_PER_KWH;
                break;
            default:
                costPerUnitOfEnergy = 0.0; // Adjust this value based on other fuel types if needed
                break;
        }

        // Calculate and return the annual fuel cost
        return (totalMiles / fuelEfficiency) * costPerUnitOfEnergy;
    }


    public boolean setMotorcycleMake(MotorcycleReview.MotorcycleMake motorcycleMake) {
        if (motorcycleMake != null && Arrays.asList(MotorcycleReview.MotorcycleMake.values()).contains(motorcycleMake)) { //converts the enums //into a list and iterates through list to see if the carMake exists
            this.motorcycleMake = motorcycleMake;
            return true;
        } else {
            System.out.println("Invalid motorcycle make: " + motorcycleMake);
            return false;
        }
    }

    public boolean setMotorcycleModel(String motorcycleModel) {
        System.out.println("NAME:::::" + motorcycleModel);
        if (motorcycleModel != null && !motorcycleModel.isEmpty()) {
            this.motorcycleModel = motorcycleModel;
            return true;
        } else {
            System.out.println("Motorcycle Model Passed as Null or Empty");
            return false;
        }
    }

    public boolean setMotorcycleFuelType(MotorcycleReview.MotorcycleFuelType motorcycleFuelType) {
        if (motorcycleFuelType != null && Arrays.asList(MotorcycleReview.MotorcycleFuelType.values()).contains(motorcycleFuelType)) { //converts the enums into a list and iterates through list to see if the carFuelType exists
            this.motorcycleFuelType = motorcycleFuelType;
            return true;
        } else {
            System.out.println("Invalid car fuel type: " + motorcycleFuelType);
            return false;
        }
    }

    //SET ALL METHOD
    public boolean setAll(String username, double reviewScore, String review, MotorcycleReview.MotorcycleMake motorcycleMake,
                          String motorcycleModel, int year, double mpg, double milesPerKWH, MotorcycleReview.MotorcycleFuelType
            motorcycleFuelType) //used the super set all in the Vehicle Review class
    {
        return (super.setAll(username,reviewScore,review,year,mpg,milesPerKWH)
                && this.setMotorcycleMake(motorcycleMake)
                && this.setMotorcycleModel(motorcycleModel)
                && this.setMotorcycleFuelType(motorcycleFuelType));
    }


    //EQUALS METHOD
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof MotorcycleReview)) {
            return false;
        }
        MotorcycleReview otherMotorcycle = (MotorcycleReview) other;
        return (super.equals(other) &&
                this.motorcycleMake.equals(otherMotorcycle.motorcycleMake) &&
                this.motorcycleModel.equals(otherMotorcycle.motorcycleModel) &&
                this.motorcycleFuelType.equals(otherMotorcycle.motorcycleFuelType));
    }

    @Override
    public String toString() {
        return "Motorcycle Review [" +
                "Username = '" + getUsername() + '\'' +
                ", ReviewScore = " + getReviewScore() +
                ", Review = '" + getReview() + '\'' +
                ", Motorcycle Make = " + motorcycleMake +
                ", Motorcycle Model = '" + motorcycleModel + '\'' +
                ", Year = " + getYear() +
                ", MPG = " + getMPG() +
                ", Miles Per KWH = " + getMilesPerKWH() +
                ", AnnualFuelCost = $" + calculateAnnualFuelCost() +
                " ]";
    }
}
