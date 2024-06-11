package com.restaurantsManager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import com.restaurantsManager.model.Restaurant;
import com.restaurantsManager.repository.RatingRepository;
import com.restaurantsManager.repository.RestaurantRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

class RatingServiceTest {
    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RatingService ratingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRateRestaurantWithNonexistentRestaurant() {
        int restaurantId = 1;
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = ratingService.rateRestaurant(restaurantId, 4.5f);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No restaurant 1 found", response.getBody());
    }

    @Test
    public void testRateRestaurantSuccessfully() {
        int restaurantId = 1;
        float newRating = 4.5f;
        Restaurant restaurant = new Restaurant("Test Restaurant", true, Arrays.asList("American"));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(ratingRepository.getAvgRatingById(restaurantId)).thenReturn(newRating);

        ResponseEntity<String> response = ratingService.rateRestaurant(restaurantId, newRating);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rating restaurant id-1 successfully", response.getBody());
    }

    @Test
    public void testRateRestaurantInvalidRating() {
        int restaurantId = 1;
        float invalidRating = 6.0f; // Assume valid ratings are between 0 and 5
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(new Restaurant()));

        ResponseEntity<String> response = ratingService.rateRestaurant(restaurantId, invalidRating);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Rating is not valid", response.getBody());
    }

    @Test
    public void testAverageRatingCalculationError() {
        int restaurantId = 1;
        float newRating = 4.0f;
        Restaurant restaurant = new Restaurant("Test Restaurant", true, Arrays.asList("American"));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(ratingRepository.getAvgRatingById(restaurantId)).thenThrow(new RuntimeException("Database error"));

        try {
            ratingService.rateRestaurant(restaurantId, newRating);
        } catch (Exception e) {
            assertEquals("Database error", e.getMessage());
        }
    }

}