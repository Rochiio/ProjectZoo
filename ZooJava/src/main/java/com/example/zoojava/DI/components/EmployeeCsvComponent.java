package com.example.zoojava.DI.components;

import com.example.zoojava.utils.csv.ImportEmployeeCsvImpl;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component
public interface EmployeeCsvComponent {
    ImportEmployeeCsvImpl build();
}
