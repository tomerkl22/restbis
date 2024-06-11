package com.restaurantsManager.service;

import com.restaurantsManager.DTO.OrderItemDTO;
import com.restaurantsManager.model.Dish;
import com.restaurantsManager.model.RestaurantOrder;
import com.restaurantsManager.model.Restaurant;
import com.restaurantsManager.repository.DishRepository;
import com.restaurantsManager.repository.OrderRepository;
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
public class OrderService {

    private static final Logger log = LogManager.getLogger(RestaurantsService.class);

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DishRepository dishRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    public ResponseEntity<String> order(int restaurantId, List<OrderItemDTO> orderItems) {
        log.info("Ordering");
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isEmpty()){
            String errorMessage = String.format("No restaurant %d found", restaurantId);
            log.error(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        for (int i=0; i<orderItems.size(); i++){
            int dishId = orderItems.get(i).getDishId();
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
        }
        RestaurantOrder order = new RestaurantOrder(restaurantId, orderItems);
        orderRepository.save(order);
        String message = String.format("{orderId:%s}",order.getOrderId());
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
