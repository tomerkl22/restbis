package com.restaurantsManager.service;

import com.restaurantsManager.model.Dish;
import com.restaurantsManager.model.Restaurant;
import com.restaurantsManager.model.RestaurantOrder;
import com.restaurantsManager.repository.DishRepository;
import com.restaurantsManager.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import com.restaurantsManager.DTO.OrderItemDTO;
import com.restaurantsManager.repository.OrderRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private DishRepository dishRepository;
    @Mock
    private RestaurantRepository restaurantRepository;
    @InjectMocks
    private OrderService orderService;
    private String uuidPattern = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOrderSuccessful() {
        int restaurantId = 1;
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        Dish dish = new Dish("Pizza", "Cheese", 20, restaurantId);
        when(dishRepository.findById(1)).thenReturn(Optional.of(dish));
        List<OrderItemDTO> orderItems = Arrays.asList(new OrderItemDTO(1, 2));
        RestaurantOrder mockOrder = new RestaurantOrder();
        when(orderRepository.save(any(RestaurantOrder.class))).thenReturn(mockOrder);

        ResponseEntity<String> response = orderService.order(restaurantId, orderItems);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{orderId:}",response.getBody().replaceAll(uuidPattern, ""));
    }
    @Test
    public void testOrderWithNonexistentRestaurant() {
        int restaurantId = 1;
        List<OrderItemDTO> orderItems = Arrays.asList(new OrderItemDTO(1, 2));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = orderService.order(restaurantId, orderItems);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No restaurant 1 found", response.getBody());
    }

    @Test
    public void testOrderWithNonexistentDish() {
        int restaurantId = 1;
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        List<OrderItemDTO> orderItems = Arrays.asList(new OrderItemDTO(1, 2));
        when(dishRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = orderService.order(restaurantId, orderItems);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No dish 1 found", response.getBody());
    }

    @Test
    public void testOrderWithDishNotInRestaurant() {
        int restaurantId = 1;
        Restaurant restaurant = new Restaurant();
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        Dish dish = new Dish("Pizza", "Cheesy", 20, 2); // Dish belongs to a different restaurant
        when(dishRepository.findById(1)).thenReturn(Optional.of(dish));
        List<OrderItemDTO> orderItems = Arrays.asList(new OrderItemDTO(1, 2));

        ResponseEntity<String> response = orderService.order(restaurantId, orderItems);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Dish 1 not in 1 restaurant", response.getBody());
    }



}