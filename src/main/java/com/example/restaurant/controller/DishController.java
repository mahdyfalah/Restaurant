package com.example.restaurant.controller;

import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.Dish;
import com.example.restaurant.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")
public class DishController {
    @Autowired
    private DishRepository dishRepository;

    @GetMapping("/dishes")
    public List<Dish> getAllDishes(){
        return dishRepository.findAll();
    }

    @PostMapping("/dishes")
    public Dish createDish(@RequestBody Dish dish){
        return dishRepository.save(dish);
    }

    @GetMapping("/dishes/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable long id){
        Dish dish = dishRepository.findById(id).orElseThrow(()-> new NotFoundException("Dish does not exist"));
        return ResponseEntity.ok(dish);
    }

    @PutMapping("/dishes/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable long id, @RequestBody Dish dishDetails){
        Dish dish = dishRepository.findById(id).orElseThrow(()-> new NotFoundException("Dish does not exist"));

        dish.setName(dishDetails.getName());
        dish.setIngredients(dishDetails.getIngredients());
        dish.setPrice(dishDetails.getPrice());

        Dish updatedDish = dishRepository.save(dish);
        return ResponseEntity.ok(updatedDish);
    }

    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDish(@PathVariable long id){
        Dish dish = dishRepository.findById(id).orElseThrow(()-> new NotFoundException("Dish does not exist"));

        dishRepository.delete(dish);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
