package com.restaurantsManager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatingTest {

    @Test
    public void testValidRatingCreation() {
        Rating rating = new Rating(1, 4.5f);
        assertEquals(1, rating.getRestaurantId());
        assertEquals(4.5f, rating.getRating(), 0.01);
    }

    @Test
    public void testRatingBelowZeroThrowsException() {
        try {
            new Rating(1, -1.0f);
            fail("Expected IllegalArgumentException for rating below zero");
        } catch (IllegalArgumentException e) {
            assertEquals("Rating is not valid", e.getMessage());
        }
    }

    @Test
    public void testRatingAboveFiveThrowsException() {
        try {
            new Rating(1, 5.1f);
            fail("Expected IllegalArgumentException for rating above five");
        } catch (IllegalArgumentException e) {
            assertEquals("Rating is not valid", e.getMessage());
        }
    }


    @Test
    public void testRatingBoundary() {
        Rating ratingLow = new Rating(1, 0.1f);
        assertEquals(0.1f, ratingLow.getRating());

        Rating ratingHigh = new Rating(1, 5.0f);
        assertEquals(5.0f, ratingHigh.getRating());
    }

}