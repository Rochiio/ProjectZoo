package com.example.zoojava.di.modules;

import com.example.zoojava.managers.DataBaseManager;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    DataBaseManager providesDataBaseManager() {
        return new DataBaseManager();
    }
}
