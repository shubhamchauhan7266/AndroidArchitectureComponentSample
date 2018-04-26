package com.androidarchitecturecomponentsample.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.androidarchitecturecomponentsample.database.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Insert
    void insertAll(List<Product> products);

    @Delete()
    void delete(Product product);

    @Query("UPDATE product_table SET itemName = :productName WHERE itemCode = :productId")
    void updateProductName(String productId, String productName);

    @Update
    void update(Product product);

    @Delete
    void deleteAll(Product... products);

    @Query("SELECT itemCode,itemName,dealerPrice FROM product_table")
    LiveData<List<Product>> getAllProducts();
}
