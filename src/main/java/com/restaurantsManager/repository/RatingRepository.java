package com.restaurantsManager.repository;

import com.restaurantsManager.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.restaurantId = ?1")
    float getAvgRatingById(int id);
}