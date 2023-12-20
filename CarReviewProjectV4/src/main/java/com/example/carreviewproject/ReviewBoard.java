package com.example.carreviewproject;

import java.util.ArrayList;
import java.util.List;

public class ReviewBoard {

    private List<CarReview> carReviewList = new ArrayList<>();
    private List<MotorcycleReview> motorcycleReviewList = new ArrayList<>();


    public List<CarReview> getCarReviewList() {
        return carReviewList;
    }

    public List<MotorcycleReview> getMotorcycleReviewList() {
        return motorcycleReviewList;
    }


    public void addCarReview(CarReview carReview) {
        carReviewList.add(carReview);
    }

    public void addMotorcycleReview(MotorcycleReview motorcycleReview) {
        motorcycleReviewList.add(motorcycleReview);
    }
}