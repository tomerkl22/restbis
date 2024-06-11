package com.restaurantsManager.model;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float averageRating;
    private boolean isKosher;
    @ElementCollection
    private List<String> cuisines;
    private final static int NO_RATING = -1;
    public Restaurant(String name, boolean isKosher, List<String> cuisines) throws IllegalArgumentException {
        String errorMsg = validateParameters(name, isKosher, cuisines);
        if (!errorMsg.isEmpty()){
            throw new IllegalArgumentException(errorMsg);
        }

        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
        this.averageRating = NO_RATING;
    }

    private String validateParameters(String name, boolean isKosher, List<String> cuisines) {
        StringBuilder errorMsg = new StringBuilder();
        if(name == null || name.isEmpty()){
            errorMsg.append("Restaurant name cannot be empty");
        }
        if(cuisines == null || cuisines.isEmpty()){
            errorMsg.append("Restaurant cuisines cannot be empty");
        }
        return errorMsg.toString();
    }

    public Restaurant() {
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean getIsKosher(){
        return isKosher;
    }
    public float getAverageRating(){
        return averageRating;
    }
    public List<String> getCuisines(){
        return cuisines;
    }

    public void setName(String name) throws IllegalArgumentException{
        if (name.isEmpty()){
            throw new IllegalArgumentException("Restaurant name cannot be empty");
        }
        this.name = name;
    }

    public void setIsKosher(Boolean isKosher){
        this.isKosher = isKosher;
    }

    public void setCuisines(List<String> cuisines) throws IllegalArgumentException{
        // Assuming Restaurant must have at least 1 cuisine
        if(cuisines.isEmpty()){
            throw new IllegalArgumentException("Restaurant cuisines cannot be empty");
        }
        this.cuisines = cuisines;
    }

    public void setAverageRating(float averageRating){
        this.averageRating = averageRating;
    }



}
