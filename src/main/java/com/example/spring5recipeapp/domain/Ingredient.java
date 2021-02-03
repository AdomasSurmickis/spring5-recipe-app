package com.example.spring5recipeapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Ingredient {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient() {
    }


    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
        this.uom = uom;
    }
}
