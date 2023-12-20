package com.example.carreviewproject;

public class SingletonReviewBoard {
    private static ReviewBoard instance = new ReviewBoard();

    private SingletonReviewBoard() {
        // Private constructor to prevent instantiation
    }

    public static synchronized ReviewBoard getInstance() {
        return instance;
    }


}
