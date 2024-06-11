package com.restaurantsManager.model;

import com.restaurantsManager.DTO.OrderItemDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RestaurantOrderTest {
    @Test
    public void testOrderCreationWithValidData() {
        List<OrderItemDTO> itemDTOs = Arrays.asList(
                new OrderItemDTO(1, 3),
                new OrderItemDTO(2, 1)
        );
        RestaurantOrder order = new RestaurantOrder(123, itemDTOs);

        assertNotNull(order.getOrderId());
        assertEquals(123, order.getRestaurantId());
        assertNotNull(order.getOrderItems());
        assertEquals(2, order.getOrderItems().size());
        assertEquals(1, order.getOrderItems().get(0).getDishId());
        assertEquals(3, order.getOrderItems().get(0).getAmount());
        assertEquals(2, order.getOrderItems().get(1).getDishId());
        assertEquals(1, order.getOrderItems().get(1).getAmount());
    }

    @Test
    public void testSetRestaurantId() {
        RestaurantOrder order = new RestaurantOrder();
        order.setRestaurantId(999);
        assertEquals(999, order.getRestaurantId());
    }

    @Test
    public void testSetOrderItems() {
        RestaurantOrder order = new RestaurantOrder();
        List<OrderItem> items = Arrays.asList(
                new OrderItem(1, 5),
                new OrderItem(2, 2)
        );
        order.setOrderItems(items);
        assertEquals(2, order.getOrderItems().size());
        assertEquals(1, order.getOrderItems().get(0).getDishId());
        assertEquals(5, order.getOrderItems().get(0).getAmount());
        assertEquals(2, order.getOrderItems().get(1).getDishId());
        assertEquals(2, order.getOrderItems().get(1).getAmount());
    }

}