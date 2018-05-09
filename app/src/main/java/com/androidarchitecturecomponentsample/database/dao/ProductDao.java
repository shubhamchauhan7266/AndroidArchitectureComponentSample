package com.androidarchitecturecomponentsample.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.androidarchitecturecomponentsample.database.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Product> products);

    @Delete()
    void delete(Product product);

    @Query("UPDATE product_table SET itemName = :productName WHERE itemCode = :productCode")
    void updateProductName(String productCode, String productName);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Product product);

    @Delete
    void deleteAll(List<Product> products);

    @Query("SELECT productId,itemCode,itemName,dealerPrice FROM product_table")
    DataSource.Factory<Integer,Product> getAllProducts();
}
