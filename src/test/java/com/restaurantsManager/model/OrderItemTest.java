package com.restaurantsManager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {
    @Test
    public void testOrderItemCreation() {
        int dishId = 1;
        int amount = 5;
        OrderItem orderItem = new OrderItem(dishId, amount);

        assertEquals(dishId, orderItem.getDishId().intValue());
        assertEquals(amount, orderItem.getAmount());
    }

    @Test
    public void testSetDishId() {
        OrderItem orderItem = new OrderItem();
        orderItem.setDishId(2);
        assertEquals(Integer.valueOf(2), orderItem.getDishId());
    }

    @Test
    public void testSetAmount() {
        OrderItem orderItem = new OrderItem();
        int newAmount = 10;
        orderItem.setAmount(newAmount);
        assertEquals(newAmount, orderItem.getAmount());
    }

    @Test
    public void testSetOrder() {
        OrderItem orderItem = new OrderItem();
        RestaurantOrder order = new RestaurantOrder();
        orderItem.setOrder(order);
        assertEquals(order, orderItem.order);
    }

    @Test
    public void testSetId() {
        OrderItem orderItem = new OrderItem();
        int newId = 100;
        orderItem.setId(newId);
        assertEquals(newId, orderItem.getId());
    }

}