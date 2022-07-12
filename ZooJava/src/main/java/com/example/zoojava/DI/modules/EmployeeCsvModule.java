package com.example.zoojava.DI.modules;

import com.example.zoojava.utils.csv.ImportEmployeeCsv;
import com.example.zoojava.utils.csv.ImportEmployeeCsvImpl;
import dagger.Binds;
import dagger.Module;

import javax.inject.Singleton;

@Module
public interface EmployeeCsvModule {
    @Singleton
    @Binds
    ImportEmployeeCsv bindsEmployeesCsv(ImportEmployeeCsvImpl employeeCsv);
}
