package com.restaurantsManager.service;

import static org.junit.jupiter.api.Assertions.*;
import com.restaurantsManager.model.Dish;
import com.restaurantsManager.model.Restaurant;
import com.restaurantsManager.repository.DishRepository;
import com.restaurantsManager.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


class DishesServiceTest {
    @Mock
    private DishRepository dishRepository;
    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private DishesService dishesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateDishWithNonexistentRestaurant() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = dishesService.createDish("Pizza", "Cheese pizza", 15, 1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No restaurant 1 found", response.getBody());
    }

    @Test
    public void testCreateDishSuccessful() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(new Restaurant()));
        when(dishRepository.save(any(Dish.class))).thenReturn(new Dish());

        ResponseEntity<String> response = dishesService.createDish("Burger", "Good burger", 10, 1);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testUpdateDishNotFound() {
        when(dishRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = dishesService.updateDish(1, 1, "Updated Name", null, null);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No dish 1 found", response.getBody());
    }

    @Test
    public void testDeleteDishSuccessful() {
        Dish dish = new Dish("Salad", "Green salad", 5, 2);
        when(dishRepository.findById(2)).thenReturn(Optional.of(dish));

        ResponseEntity<String> response = dishesService.deleteDish(2, 2);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(dishRepository).deleteById(2);
    }

    @Test
    public void testGetAllDishes() {
        when(dishRepository.findAll()).thenReturn(java.util.Collections.emptyList());

        ResponseEntity<List<Dish>> response = dishesService.getAllDishes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    public void testCreateDishInvalidPrice() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(new Restaurant()));
        ResponseEntity<String> response = dishesService.createDish("Pizza", "Cheese pizza", -10, 1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Dish price cannot be negative", response.getBody());
    }

    @Test
    public void testUpdateDishWithNegativePrice() {
        Dish dish = new Dish("Burger", "Good burger", 10, 1);
        when(dishRepository.findById(1)).thenReturn(Optional.of(dish));
        ResponseEntity<String> response = dishesService.updateDish(1, 1, null, null, -1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals( "Price cannot be negative", response.getBody());
    }

    @Test
    public void testUpdateDishMismatchedRestaurant() {
        Dish dish = new Dish("Burger", "Good burger", 10, 2);
        when(dishRepository.findById(1)).thenReturn(Optional.of(dish));
        ResponseEntity<String> response = dishesService.updateDish(1, 1, "Updated Name", null, null);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals( "No dish 1 found for restaurant 1", response.getBody());
    }

}