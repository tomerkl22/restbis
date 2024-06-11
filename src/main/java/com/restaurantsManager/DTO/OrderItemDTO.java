package com.restaurantsManager.DTO;

public class OrderItemDTO {
    private int dishId;
    private int amount;

    public OrderItemDTO(int dishId, int amount){
        this.dishId = dishId;
        this.amount = amount;
    }

    public int getDishId(){
        return dishId;
    }
    public int getAmount(){
        return amount;
    }
    public void setDishId(int dishId){
        this.dishId = dishId;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
}
