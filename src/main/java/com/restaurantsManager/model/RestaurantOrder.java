package com.restaurantsManager.model;

import com.restaurantsManager.DTO.OrderItemDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurant_order")
public class RestaurantOrder {
    // Order is a saved word in postgreSQL - I had a problem with it.
    @Id
    @Column(name = "order_id")
    private UUID orderId;
    private int restaurantId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    public RestaurantOrder(){
    }
    public RestaurantOrder(int restaurantId, List<OrderItemDTO> orderItems) {
        this.orderId = UUID.randomUUID();
        this.restaurantId = restaurantId;
        for (OrderItemDTO item : orderItems){
            this.orderItems.add(new OrderItem(item.getDishId(), item.getAmount()));
        }
        this.orderItems.forEach(item -> item.setOrder(this));
    }
    public UUID getOrderId() { return orderId; }
    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}