package com.androidarchitecturecomponentsample.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.androidarchitecturecomponentsample.database.dao.ProductDao;
import com.androidarchitecturecomponentsample.database.entity.Product;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao getProductDao();

    private static ProductDatabase mProductDatabase;

    public  static ProductDatabase getInstance(final Context context) {
        if (mProductDatabase == null) {
            synchronized (ProductDatabase.class) {
                if (mProductDatabase == null) {
                    mProductDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            ProductDatabase.class, "product_database")
                            .build();

                }
            }
        }
        return mProductDatabase;
    }
}
