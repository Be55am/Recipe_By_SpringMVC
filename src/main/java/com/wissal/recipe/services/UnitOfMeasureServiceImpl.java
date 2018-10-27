package com.wissal.recipe.services;

import com.wissal.recipe.commands.UnitOfMeasureCommand;
import com.wissal.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.wissal.recipe.repositories.UniteOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UniteOfMeasureRepository uniteOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UniteOfMeasureRepository uniteOfMeasureRepository,UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.uniteOfMeasureRepository = uniteOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand=unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        return StreamSupport.stream(uniteOfMeasureRepository.findAll().spliterator(),false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert).collect(Collectors.toSet());
    }
}
