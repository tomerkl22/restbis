package com.restaurantsManager.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    @Test
    public void createNewRestaurantSuccessfully(){
        List<String> sampleCuisines = new ArrayList<>();
        sampleCuisines.add("Asian");
        Restaurant restaurant = new Restaurant("Nam", false, sampleCuisines);
        assertEquals(restaurant.getName(), "Nam");
    }

    @Test
    public void createNewRestaurantNoCuisines(){
        List<String> sampleCuisines = new ArrayList<>();
        try{
            Restaurant restaurant = new Restaurant("Nam", false, sampleCuisines);
            fail("Expected exception");
        }
        catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), "Restaurant cuisines cannot be empty");
        }
    }

    @Test()
    public void testEmptyNameThrowsException() {
        try {
            new Restaurant("", true, Arrays.asList("Italian"));
        }
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Restaurant name cannot be empty");

        }
    }

    @Test()
    public void testNullNameThrowsException() {
        try {
            new Restaurant(null, false, Arrays.asList("Mexican"));
        }
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Restaurant name cannot be empty");
        }
    }

    @Test()
    public void testEmptyCuisinesThrowsException() {
        try {
            new Restaurant("Taizu", false, Collections.emptyList());
        }
        catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), "Restaurant cuisines cannot be empty");
        }
    }

    @Test()
    public void testNullCuisinesThrowsException() {
        try {
            new Restaurant("Taizu", false, null);
        }
        catch (IllegalArgumentException e){
            assertEquals(e.getMessage(), "Restaurant cuisines cannot be empty");
        }
    }

    @Test
    public void testSetName() {
        Restaurant restaurant = new Restaurant("Vitrina", false, Arrays.asList("American"));
        restaurant.setName("New Name");
        assertEquals("New Name", restaurant.getName());
    }

    @Test
    public void setEmptyNameShouldThrowException() {
        Restaurant restaurant = new Restaurant("Vitrina", false, Arrays.asList("American"));
        try {
            restaurant.setName("");
            fail("Expected exception for setting empty name");
        } catch (IllegalArgumentException e) {
            assertEquals("Restaurant name cannot be empty", e.getMessage());
        }
    }


    @Test
    public void testSetCuisines() {
        Restaurant restaurant = new Restaurant("Bistro", false, Arrays.asList("French"));
        restaurant.setCuisines(Arrays.asList("Italian", "Desserts"));
        assertEquals(Arrays.asList("Italian", "Desserts"), restaurant.getCuisines());
    }

    @Test
    public void setEmptyCuisinesShouldThrowException() {
        Restaurant restaurant = new Restaurant("Bistro", false, Arrays.asList("French"));
        try {
            restaurant.setCuisines(Collections.emptyList());
            fail("Expected exception for setting empty cuisines");
        } catch (IllegalArgumentException e) {
            assertEquals("Restaurant cuisines cannot be empty", e.getMessage());
        }
    }


    @Test
    public void testSetAverageRating() {
        Restaurant restaurant = new Restaurant("BBB", true, Arrays.asList("Gourmet"));
        restaurant.setAverageRating(4.5f);
        assertEquals(4.5f, restaurant.getAverageRating());
    }

}