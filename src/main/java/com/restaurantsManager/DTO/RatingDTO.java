package com.restaurantsManager.DTO;

public class RatingDTO {

    private int restaurantId;
    private float rating;

    public int getRestaurantId(){
        return restaurantId;
    }
    public float getRating(){
        return rating;
    }

    public void setRating(float rating){
        this.rating = rating;
    }
    public void setRestaurantId(int restaurantId){
        this.restaurantId = restaurantId;
    }
}
