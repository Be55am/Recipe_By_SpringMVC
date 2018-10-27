package com.wissal.recipe.services;

import com.wissal.recipe.commands.UnitOfMeasureCommand;
import com.wissal.recipe.model.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
