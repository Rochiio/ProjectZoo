package com.example.zoojava.DI.components;

import com.example.zoojava.DI.modules.DataBaseModule;
import com.example.zoojava.repositories.employees.EmployeesRepositoryImpl;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = DataBaseModule.class)
public interface EmployeeRepositoryComponent {
    @Singleton
    EmployeesRepositoryImpl build();
}
