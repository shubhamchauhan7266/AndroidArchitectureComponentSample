package com.androidarchitecturecomponentsample.application;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.androidarchitecturecomponentsample.database.ProductDatabase;

public class MyApplication  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
