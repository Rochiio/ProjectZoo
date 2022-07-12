package com.example.zoojava.DI.modules;

import com.example.zoojava.utils.csv.ImportAnimalCsv;
import com.example.zoojava.utils.csv.ImportAnimalCsvImpl;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public interface AnimalCsvModule {
    @Singleton
    @Binds
    ImportAnimalCsv bindsAnimalCsv(ImportAnimalCsvImpl animalCsv);
}
