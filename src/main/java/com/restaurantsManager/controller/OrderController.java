package com.restaurantsManager.controller;

import com.restaurantsManager.DTO.OrderDTO;
import com.restaurantsManager.constants.UrlMappingConst;
import com.restaurantsManager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(UrlMappingConst.ORDER)
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping()
    @ResponseBody
    public ResponseEntity<String> order(@RequestBody OrderDTO orderDTO){
        return orderService.order(orderDTO.getRestaurantId(), orderDTO.getOrderItems());
    }

}
