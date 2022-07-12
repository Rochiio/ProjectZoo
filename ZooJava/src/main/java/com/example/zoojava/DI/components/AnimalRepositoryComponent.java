package com.example.zoojava.DI.components;

import com.example.zoojava.DI.modules.DataBaseModule;
import com.example.zoojava.repositories.animals.AnimalsRepositoryImpl;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = DataBaseModule.class)
public interface AnimalRepositoryComponent {
    AnimalsRepositoryImpl build();
}
