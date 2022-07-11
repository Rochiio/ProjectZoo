package com.example.zoojava.DI.modules;

import com.example.zoojava.repositories.animals.AnimalsRepository;
import com.example.zoojava.repositories.animals.AnimalsRepositoryImpl;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public interface AnimalRepositoryModule {
    @Binds
    @Singleton
    AnimalsRepository bindsAnimalsRepository(AnimalsRepositoryImpl repository);
}
