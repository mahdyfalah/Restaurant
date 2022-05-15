package com.example.restaurant.model;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Table(name = "Dish")
public @Data class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ElementCollection // 1
    @CollectionTable(name = "ingredient_list", joinColumns = @JoinColumn(name = "id")) // 2
    @Column(name = "ingredients") // 3
    private List<String> ingredients;

    @Column(name = "price")
    private float price;
}
