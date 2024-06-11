package com.restaurantsManager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue
    private int id;
    private Integer dishId;
    private int amount;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    protected RestaurantOrder order;
    public OrderItem(){
    }
    public OrderItem(int dishId, int amount) {
        this.dishId = dishId;
        this.amount = amount;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Integer getDishId() { return dishId; }
    public void setDishId(Integer dishId) { this.dishId = dishId; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount;}

    public void setOrder(RestaurantOrder order){ this.order = order;}
}