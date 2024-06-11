package com.restaurantsManager.DTO;

import java.util.List;

public class OrderDTO {
    private int restaurantId;
    private List<OrderItemDTO> orderItems;

    public int getRestaurantId(){
        return restaurantId;
    }
    public List<OrderItemDTO> getOrderItems(){ return orderItems; }
}
