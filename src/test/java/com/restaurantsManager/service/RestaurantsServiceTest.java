package com.restaurantsManager.service;

import com.restaurantsManager.model.Restaurant;
import com.restaurantsManager.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantsServiceTest {
    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantsService restaurantsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRestaurantSuccessful() {
        Restaurant restaurant = new Restaurant("Sushi", true, Arrays.asList("Japanese"));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);

        ResponseEntity<String> response = restaurantsService.createRestaurant("Sushi", true, Arrays.asList("Japanese"));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testCreateRestaurantFailure() {
        when(restaurantRepository.save(any(Restaurant.class))).thenThrow(new IllegalArgumentException("Invalid data"));

        ResponseEntity<String> response = restaurantsService.createRestaurant("", true, Arrays.asList("Japanese"));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testGetAllRestaurants() {
        List<Restaurant> restaurants = Arrays.asList(new Restaurant("Sushi", true, Arrays.asList("Japanese")));
        when(restaurantRepository.findAll()).thenReturn(restaurants);

        ResponseEntity<List<Restaurant>> response = restaurantsService.getAllRestaurants();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetRestaurantByIdNotFound() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<Restaurant> response = restaurantsService.getRestaurantById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetRestaurantByIdFound() {
        Restaurant restaurant = new Restaurant("Sushi", true, Arrays.asList("Japanese"));
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));

        ResponseEntity<Restaurant> response = restaurantsService.getRestaurantById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdateRestaurantNotFound() {
        when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = restaurantsService.updateRestaurant(1, "Sushi", true, Arrays.asList("Japanese"));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdateRestaurantSuccessful() {
        Restaurant restaurant = new Restaurant("Amore Mio", false, Arrays.asList("Italian"));
        when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        ResponseEntity<String> response = restaurantsService.updateRestaurant(1, "A", true, Arrays.asList("Italian", "Sushi"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Restaurant 1 updated", response.getBody());
    }

    @Test
    public void testDeleteRestaurantNotFound() {
        when(restaurantRepository.existsById(1)).thenReturn(false);

        ResponseEntity<String> response = restaurantsService.deleteRestaurant(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteRestaurantSuccessful() {
        when(restaurantRepository.existsById(1)).thenReturn(true);
        doNothing().when(restaurantRepository).deleteById(1);

        ResponseEntity<String> response = restaurantsService.deleteRestaurant(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


}
