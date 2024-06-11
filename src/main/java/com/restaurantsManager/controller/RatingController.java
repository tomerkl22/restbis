package com.restaurantsManager.controller;

import com.restaurantsManager.DTO.RatingDTO;
import com.restaurantsManager.constants.UrlMappingConst;
import com.restaurantsManager.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(UrlMappingConst.RATINGS)
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping()
    @ResponseBody
    public ResponseEntity<String> rateRestaurant(@RequestBody RatingDTO ratingDTO){
        return ratingService.rateRestaurant(ratingDTO.getRestaurantId(), ratingDTO.getRating());
    }



}
