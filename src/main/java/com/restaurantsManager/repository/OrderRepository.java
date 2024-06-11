package com.restaurantsManager.repository;

import com.restaurantsManager.model.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<RestaurantOrder, Integer> {
}
