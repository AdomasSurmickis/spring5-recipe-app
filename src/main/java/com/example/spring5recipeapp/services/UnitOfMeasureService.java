package com.example.spring5recipeapp.services;

import com.example.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.example.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.spring5recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();




}
