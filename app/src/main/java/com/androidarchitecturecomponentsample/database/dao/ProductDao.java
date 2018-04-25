package com.androidarchitecturecomponentsample.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.androidarchitecturecomponentsample.database.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product word);

    @Query("DELETE FROM product_table WHERE id = :productId")
    void delete(String productId);

    @Query("UPDATE product_table SET name = :productName")
    void update(String productId,String productName);

    @Delete
    void deleteAll(Product... products);

    @Query("SELECT id,name,price,quantity FROM product_table")
    LiveData<List<Product>> getAllProducts();
}
