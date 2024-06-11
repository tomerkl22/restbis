package com.restaurantsManager.service;

import com.restaurantsManager.model.Rating;
import com.restaurantsManager.model.Restaurant;
import com.restaurantsManager.repository.RatingRepository;
import com.restaurantsManager.repository.RestaurantRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {
    private static final Logger log = LogManager.getLogger(RestaurantsService.class);

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public ResponseEntity<String> rateRestaurant(int restaurantId, float rating) {
        log.info(String.format("Rating restaurant id-%d", restaurantId));
        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
            if (restaurant.isEmpty()) {
                String errorMessage = String.format("No restaurant %s found", restaurantId);
                log.error(errorMessage);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
            Rating rate = new Rating(restaurantId, rating);
            ratingRepository.save(rate);
            float averageRating = ratingRepository.getAvgRatingById(restaurantId);
            restaurant.get().setAverageRating(averageRating);
            restaurantRepository.save(restaurant.get());
            String message = String.format("Rating restaurant id-%d successfully", restaurantId);
            log.info(message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }

}
