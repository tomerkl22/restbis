package com.restaurantsManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dishId;
    private String name;
    private String description;
    private int price;
    @JsonIgnore
    private int restaurantId;

    public Dish(String name, String description, int price, int restaurantId) throws IllegalArgumentException{
        String errorMsg = validateParameters(name, description, price);
        if (!errorMsg.isEmpty()){
            throw new IllegalArgumentException(errorMsg);
        }
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public String validateParameters(String name, String description, int price){
        StringBuilder errorMsg = new StringBuilder();
        if (name == null || name.isEmpty()){
            errorMsg.append("Dish name cannot be empty");
        }
        if (description == null || description.isEmpty()){
            errorMsg.append("Dish description cannot be empty");
        }
        if (price < 0){
            errorMsg.append("Dish price cannot be negative");
        }
        return errorMsg.toString();
    }

    public Dish() {}
    public int getId() {
        return dishId;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public int getRestaurantId(){
        return restaurantId;
    }
    public int getPrice(){
        return price;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setPrice(int price) throws IllegalArgumentException {
        if (price < 0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }
}
