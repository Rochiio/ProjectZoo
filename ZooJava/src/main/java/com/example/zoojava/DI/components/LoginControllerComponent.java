package com.example.zoojava.DI.components;

import com.example.zoojava.DI.modules.DataBaseModule;
import com.example.zoojava.DI.modules.EmployeeRepositoryModule;
import com.example.zoojava.controllers.LoginController;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        DataBaseModule.class,
        EmployeeRepositoryModule.class
})
public interface LoginControllerComponent {
    LoginController build();
}
