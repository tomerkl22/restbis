package com.restaurantsManager.service;

import com.restaurantsManager.model.Dish;
import com.restaurantsManager.model.Restaurant;
import com.restaurantsManager.repository.DishRepository;
import com.restaurantsManager.repository.RestaurantRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DishesService {
    private static final Logger log = LogManager.getLogger(DishesService.class);

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public ResponseEntity<String> createDish(String name, String description, int price, int restaurantId) {
        log.info("Creating a dish");
        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
            if (restaurant.isEmpty()) {
                String errorMessage = String.format("No restaurant %s found", restaurantId);
                log.error(errorMessage);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }
            Dish dish = new Dish(name, description, price, restaurantId);
            dishRepository.save(dish);
            log.info(String.format("Dish %s created successfully", name));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    public ResponseEntity<String> updateDish(int restaurantId, int dishId, String name, String description, Integer price) {
        Optional<Dish> dish = dishRepository.findById(dishId);
        if (dish.isEmpty()){
            String errorMessage = String.format("No dish %d found", dishId);
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        if (dish.get().getRestaurantId() != restaurantId){
            String errorMessage = String.format("No dish %d found for restaurant %d", dishId, restaurantId);
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        log.info(String.format("Updating dish %d", dishId));
        try{
            if (name != null){
                dish.get().setName(name);
            }
            if (description != null){
                dish.get().setDescription(description);
            }
            if (price != null){
                dish.get().setPrice(price);
            }
            dishRepository.save(dish.get());
            String message = String.format("Dish %d updated", dishId);
            log.info(message);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<String> deleteDish(int restaurantId, int dishId) {
        log.info(String.format("Deleting dish %d", dishId));
        Optional<Dish> dish = dishRepository.findById(dishId);
        if (dish.isEmpty()){
            String errorMessage = String.format("No dish %d found", dishId);
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        if (dish.get().getRestaurantId() != restaurantId){
            String errorMessage = String.format("Dish %d not in %d restaurant", dishId, restaurantId);
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        dishRepository.deleteById(restaurantId);
        String message = String.format("Dish %d deleted successfully", dishId);
        log.info(message);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }

    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish> dishes = dishRepository.findAll();
        log.info(String.format("Found %d dishes", dishes.size()));
        return ResponseEntity.status(HttpStatus.OK).body(dishes);
    }
}
