package com.wissal.recipe.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Be55am
 */
@Data
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private  Recipe recipe;
    @Lob // to create a lob object in the database field so we can add more the 255 char
    private String recipeNotes;


}
