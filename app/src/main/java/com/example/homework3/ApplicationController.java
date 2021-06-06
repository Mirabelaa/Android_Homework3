package com.example.homework3;

import android.app.Application;

import androidx.room.Room;

import com.example.homework3.data.BookDataBase;

public class ApplicationController extends Application {

    private static BookDataBase dataBase;
    private static ApplicationController instance;
    private final String name ="Book";

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        setUpDataBase();
    }

    void setUpDataBase(){
        dataBase = Room.databaseBuilder(getApplicationContext(), BookDataBase.class, name)
                .fallbackToDestructiveMigration().build();
    }

    public static BookDataBase getDataBase() {
        return dataBase;
    }

}
