package com.example.zoojava.DI.modules;

import com.example.zoojava.repositories.employees.EmployeesRepository;
import com.example.zoojava.repositories.employees.EmployeesRepositoryImpl;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public interface EmployeeRepositoryModule {
    @Binds
    @Singleton
    EmployeesRepository bindsEmployeesRepository(EmployeesRepositoryImpl repository);
}
