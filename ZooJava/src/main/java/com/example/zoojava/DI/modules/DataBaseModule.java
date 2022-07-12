package com.example.zoojava.DI.modules;

import com.example.zoojava.managers.DataBaseManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;


@Module
public class DataBaseModule {

    @Provides
    @Singleton
    DataBaseManager provideDataBaseManager() {
        return new DataBaseManager();
    }
}
