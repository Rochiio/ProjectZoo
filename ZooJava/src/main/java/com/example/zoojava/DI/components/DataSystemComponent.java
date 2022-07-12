package com.example.zoojava.DI.components;

import com.example.zoojava.DI.modules.AnimalCsvModule;
import com.example.zoojava.DI.modules.AnimalRepositoryModule;
import com.example.zoojava.DI.modules.EmployeeCsvModule;
import com.example.zoojava.DI.modules.EmployeeRepositoryModule;
import com.example.zoojava.utils.DataSystem;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AnimalCsvModule.class,
        EmployeeCsvModule.class,
        AnimalRepositoryModule.class,
        EmployeeRepositoryModule.class
})
public interface DataSystemComponent {
    DataSystem build();
}
