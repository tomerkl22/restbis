package com.restaurantsManager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ratingId;
    private int restaurantId;
    private float rating;

    public Rating(int restaurantId, float rating) throws IllegalArgumentException {
        if (rating < 0 || rating > 5){
            throw new IllegalArgumentException("Rating is not valid");
        }
        this.restaurantId = restaurantId;
        this.rating = rating;
    }
    public Rating(){
    }

    public int getRatingId() {
        return ratingId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public float getRating() {
        return rating;
    }


}
