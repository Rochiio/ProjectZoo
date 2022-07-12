package com.example.zoojava.DI.components;

import com.example.zoojava.utils.csv.ImportAnimalCsvImpl;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component
public interface AnimalCsvComponent {
    ImportAnimalCsvImpl build();
}
