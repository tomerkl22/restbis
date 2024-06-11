package com.restaurantsManager.service;

import com.restaurantsManager.model.Restaurant;
import com.restaurantsManager.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class RestaurantsService {

    private static final Logger log = LogManager.getLogger(RestaurantsService.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    public ResponseEntity<String> createRestaurant(String name, boolean isKosher, List<String> cuisines){
        log.info("Creating restaurant");
        try{
            Restaurant restaurant = new Restaurant(name, isKosher, cuisines);
            restaurantRepository.save(restaurant);
            log.info(String.format("Restaurant %s created successfully", name));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
            List<Restaurant> restaurants = restaurantRepository.findAll();
            log.info(String.format("Found %d restaurants", restaurants.size()));
            return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    public ResponseEntity<Restaurant> getRestaurantById(int id) {
            Optional<Restaurant> restaurant = restaurantRepository.findById(id);
            if (restaurant.isEmpty()){
                String message = String.format("No restaurant %s found", id);
                log.error(message);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            else{
                log.info("Restaurant found");
                return ResponseEntity.status(HttpStatus.OK).body(restaurant.get());
            }
    }

    public ResponseEntity<List<Restaurant>> getRestaurantsByCuisine(String cuisine) {
        List<Restaurant> restaurants = restaurantRepository.findAllByCuisines(cuisine);
        log.info(String.format("Found %d restaurant that serve %s cuisine", restaurants.size(), cuisine));
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }


    public ResponseEntity<String> updateRestaurant(int id, String name, Boolean isKosher, List<String> cuisines) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()){
            String errorMessage = String.format("No restaurant %d found", id);
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        try {
            if (name != null) {
                restaurant.get().setName(name);
            }
            if (isKosher != null) {
                restaurant.get().setIsKosher(isKosher);
            }
            if (cuisines != null) {
                restaurant.get().setCuisines(cuisines);
            }
            restaurantRepository.save(restaurant.get());
            String message = String.format("Restaurant %d updated", id);
            log.info(message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<String> deleteRestaurant(int id) {
        log.info(String.format("Deleting restaurant %d", id));
        if (!restaurantRepository.existsById(id)){
            String errorMessage = String.format("No restaurant %d found", id);
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        restaurantRepository.deleteById(id);
        String message = String.format("Restaurant %d deleted successfully", id);
        log.info(message);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }
}
