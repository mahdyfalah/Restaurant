package com.example.restaurant.repository;

import com.example.restaurant.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {

}
