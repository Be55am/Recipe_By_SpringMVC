package com.wissal.recipe.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(of="id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;


}
