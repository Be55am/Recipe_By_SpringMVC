package com.wissal.recipe.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;


    @OneToOne(fetch = FetchType.EAGER) //its eager by default but any way it loads this object when it load the ingrediant
    private UnitOfMeasure uom;

    @ManyToOne
    private  Recipe recipe;


    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }


    public Ingredient() {

    }
}
