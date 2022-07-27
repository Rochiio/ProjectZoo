package com.example.zoojava.di.components;

import com.example.zoojava.di.modules.DatabaseModule;
import com.example.zoojava.repositories.animals.AnimalsRepositoryImpl;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = DatabaseModule.class)
public interface AnimalRepositoryComponent {
    AnimalsRepositoryImpl build();
}
