package com.restaurantsManager.repository;

import com.restaurantsManager.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Integer>{
}
