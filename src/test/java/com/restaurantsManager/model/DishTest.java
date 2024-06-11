package com.restaurantsManager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishTest {
    @Test
    public void testDishCreationWithValidData() {
        Dish dish = new Dish("Pizza", "Tomato pizza", 20, 1);
        assertEquals("Pizza", dish.getName());
        assertEquals("Tomato pizza", dish.getDescription());
        assertEquals(20, dish.getPrice());
        assertEquals(1, dish.getRestaurantId());
    }

    @Test
    public void testDishCreationWithInvalidName() {
        try {
            new Dish("", "Tomato pizza", 20, 1);
            fail("Expected IllegalArgumentException for empty name");
        } catch (IllegalArgumentException e) {
            assertEquals("Dish name cannot be empty", e.getMessage());
        }
    }

    @Test
    public void testDishCreationWithInvalidDescription() {
        try {
            new Dish("Pizza", "", 20, 1);
            fail("Expected IllegalArgumentException for empty description");
        } catch (IllegalArgumentException e) {
            assertEquals("Dish description cannot be empty", e.getMessage());
        }
    }

    @Test
    public void testDishCreationWithNegativePrice() {
        try {
            new Dish("Pizza", "Tomato pizza", -1, 1);
            fail("Expected IllegalArgumentException for negative price");
        } catch (IllegalArgumentException e) {
            assertEquals("Dish price cannot be negative", e.getMessage());
        }
    }

    @Test
    public void testSetPrice() {
        Dish dish = new Dish("Burger", "Beef burger with sauces", 15, 2);
        dish.setPrice(18);
        assertEquals(18, dish.getPrice());
    }

    @Test
    public void testSetInvalidPrice() {
        Dish dish = new Dish("Burger", "Beef burger with sauces", 15, 2);
        try {
            dish.setPrice(-5);
            fail("Expected IllegalArgumentException for negative price");
        } catch (IllegalArgumentException e) {
            assertEquals("Price cannot be negative", e.getMessage());
        }
    }

    @Test
    public void testSetName() {
        Dish dish = new Dish("Burger", "Beef burger with sauces", 15, 2);
        dish.setName("Veggie Burger");
        assertEquals("Veggie Burger", dish.getName());
    }

    @Test
    public void testSetDescription() {
        Dish dish = new Dish("Burger", "Beef burger with sauces", 15, 2);
        dish.setDescription("Vegetarian burger with fresh vegetables and sauces");
        assertEquals("Vegetarian burger with fresh vegetables and sauces", dish.getDescription());
    }

}