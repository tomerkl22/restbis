package com.restaurantsManager.controller;

import com.restaurantsManager.DTO.DishDTO;
import com.restaurantsManager.constants.UrlMappingConst;
import com.restaurantsManager.model.Dish;
import com.restaurantsManager.service.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(UrlMappingConst.RESTAURANTS + "/{id}" + UrlMappingConst.DISHES)
public class DishesController {

    @Autowired
    DishesService dishesService;

    @PostMapping()
    @ResponseBody
    public ResponseEntity<String> createDish(@PathVariable int id, @RequestBody DishDTO dishDTO){
        return dishesService.createDish(dishDTO.getName(), dishDTO.getDescription(), dishDTO.getPrice(), id);
    }

    @PutMapping("/{dishId}")
    @ResponseBody
    public ResponseEntity<String> updateDish(@PathVariable int id, @PathVariable int dishId, @RequestBody DishDTO dishDTO){
        return dishesService.updateDish(id, dishId, dishDTO.getName(), dishDTO.getDescription(), dishDTO.getPrice());
    }

    @DeleteMapping("/{dishId}")
    @ResponseBody
    public ResponseEntity<String> deleteDish(@PathVariable int id, @PathVariable int dishId){
        return dishesService.deleteDish(id, dishId);
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<Dish>> getAllDishes(@PathVariable int id){
        return dishesService.getAllDishes();
    }

}
