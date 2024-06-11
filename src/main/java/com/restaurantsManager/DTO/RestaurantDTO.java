package com.restaurantsManager.DTO;

import java.util.List;

public class RestaurantDTO {
    private String name;
    private Boolean isKosher;
    private List<String> cuisines;

    public String getName(){
        return this.name;
    }
    public Boolean getIsKosher(){
        return this.isKosher;
    }
    public List<String> getCuisines(){
        return this.cuisines;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setKosher(Boolean isKosher){
        this.isKosher = isKosher;
    }
    public void setCuisines(List<String> cuisines){
        this.cuisines = cuisines;
    }

}
