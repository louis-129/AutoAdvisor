package com.example.carreviewproject;


import java.util.Date;
import java.time.Year;
import java.util.Objects;
public abstract class VehicleReview {
    //DEFAULT VARIABLES
    public static final String DEFAULT_USERNAME = "Generic User";
    public static final double DEFAULT_REVIEW_SCORE = 5.0;
    public static final String DEFAULT_REVIEW = "I have neutral feelings for this vehicle";
    public static final int DEFAULT_YEAR = 2000;
    public static final double DEFAULT_MPG = 15.0;
    public static final double DEFAULT_MILES_PER_KWH = 100.0;

    public static final int AVERAGE_MILES_DRIVEN = 14000; //based on Nationwide Average
    public static final double AVERAGE_PRICE_PER_GALLON_OF_GASLOLINE = 4.75; // dollars per gallon (based on California average)
    public static final double AVERAGE_PRICE_PER_KWH = 0.30; // dollars per KWH (based on California average)

    //INSTANCE VARIABLES
    private String username;
    private double reviewScore;
    private String review;
    private int year;
    //added these two as both motorcycle and car class will have eletric and liquid fuel options
    private double mpg; //(miles per gallon)
    private double milesPerKWH; //(miles per gallon equvilent)
    //In the final version the user will only be able to input their mpg or mpge based on the fuel type they selected. The other will be set to 0.0

    //Default Constructor
    public VehicleReview() {
        this(DEFAULT_USERNAME, DEFAULT_REVIEW_SCORE, DEFAULT_REVIEW, DEFAULT_YEAR, DEFAULT_MPG, DEFAULT_MILES_PER_KWH);
    }

    //Full Constructor
    public VehicleReview(String username, double reviewScore, String review, int year, double mpg, double milesPerKWH) {
        if(!this.setAll(username, reviewScore, review, year, mpg, milesPerKWH))
        {
            System.out.println("ERROR: Bad data given to the full constructor in VehicleReview class");
            System.exit(0);
        }
    }

    //Copy Constructor
    public VehicleReview(VehicleReview original){
        if(original!=null)
        {
            this.setAll(original.username, original.reviewScore, original.review, original.year,original.mpg,original.milesPerKWH);
        }
        else
        {
            System.out.println("ERROR: Null was passed in through the copy constructor in VehicleReview class");
            System.exit(0);
        }
    }

    public abstract double calculateAnnualFuelCost(); //implementation will be different based off the fuel types avalible for each type of vehicle
    //For example cars will get Eletric, Gasoline, Diesel and Ethanol fuel types. While motorcycles will just get Gasoline and Eletric.
    //Will take be calculated based off the average annual miles driven by an American and yearly average fuel prices in California.
    //MPG will be manually added by the user and will be used to calculate the annual fuel cost


    //GETTERS
    public String getUsername() {
        return username;
    }

    public double getReviewScore() {
        return reviewScore;
    }

    public String getReview() {
        return review;
    }

    public int getYear(){
        return year;
    }

    public double getMPG(){
        return mpg;
    }

    public double getMilesPerKWH(){
        return milesPerKWH;
    }


    //SETTERS
    public boolean setUsername(String username) {
        if (username != null && !username.isEmpty() && username.length() > 0) {
            this.username = username;
            return true;
        } else {
            return false;
        }
    }

    public boolean setReviewScore(double reviewScore) {
        if (reviewScore >= 0.0 && reviewScore <= 10.0) {
            this.reviewScore = reviewScore;
            return true;
        } else {
            return false;
        }
    }

    public boolean setReview(String review) {
        if (review != null && !review.isEmpty() && review.length()>0) {
            this.review = review;
            return true;
        } else {
            return false;
        }
    }

    public boolean setYear(int year) {
        if (year >= 1900 && year <= Year.now().getValue()) {
            this.year = year;
            return true;
        } else {
            return false;
        }
    }

    public boolean setMPG(double mpg){
        if(mpg>=0.0 && mpg <=80 ){ //based off current records and realistic standards
            this.mpg = mpg;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean setMilesPerKWH(double milesPerKWH){
        if(milesPerKWH>=0.0 && milesPerKWH <=15.0 ){ //based off current records and realistic standards
            this.milesPerKWH = milesPerKWH;
            return true;
        }
        else{
            return false;
        }
    }

    //SET_ALL METHOD
    public boolean setAll(String username, double reviewScore, String review, int year, double mpg, double milesPerKWH)
    {
        return (this.setUsername(username) && this.setReviewScore(reviewScore) && this.setReview(review) && this.setYear(year) && this.setMPG(mpg) && this.setMilesPerKWH(milesPerKWH));
    }

    //EQUALS METHOD
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VehicleReview that = (VehicleReview) o;
        return year == that.year &&
                Double.compare(that.reviewScore, reviewScore) == 0 &&
                Objects.equals(username, that.username) &&
                Objects.equals(review, that.review) &&
                Double.compare(that.mpg, mpg) == 0 &&
                Double.compare(that.milesPerKWH, milesPerKWH) == 0;
    }

    //TO STRING METHOD
    @Override
    public String toString() {
        return "VehicleReview {" + "username = '" + username + '\'' +  ", reviewScore = " + reviewScore + ", review = '" + review + '\'' +
                ", year = " + year + ", mpg = " + mpg + ", miles per KWH = " + milesPerKWH + '}';
    }


}