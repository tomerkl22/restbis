package com.restaurantsManager.controller;

import com.restaurantsManager.DTO.RestaurantDTO;
import com.restaurantsManager.constants.UrlMappingConst;
import com.restaurantsManager.model.Restaurant;
import com.restaurantsManager.service.RestaurantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(UrlMappingConst.RESTAURANTS)

public class RestaurantsController {

    @Autowired
    RestaurantsService restaurantsService;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return restaurantsService.getAllRestaurants();
    }

    @GetMapping(params = {"cuisine"})
    @ResponseBody
    public ResponseEntity<List<Restaurant>> getRestaurantByCuisine(@RequestParam("cuisine") String cuisine){
        return restaurantsService.getRestaurantsByCuisine(cuisine);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id ){
        return restaurantsService.getRestaurantById(id);
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<String> createRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        return restaurantsService.createRestaurant(restaurantDTO.getName(), restaurantDTO.getIsKosher(), restaurantDTO.getCuisines());
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> updateRestaurant(@PathVariable int id, @RequestBody RestaurantDTO restaurantDTO){
        return restaurantsService.updateRestaurant(id, restaurantDTO.getName(), restaurantDTO.getIsKosher(), restaurantDTO.getCuisines());
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteRestaurant(@PathVariable int id){
        return restaurantsService.deleteRestaurant(id);
    }


}
