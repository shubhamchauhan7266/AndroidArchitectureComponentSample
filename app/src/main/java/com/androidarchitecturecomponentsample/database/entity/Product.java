package com.androidarchitecturecomponentsample.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 *
 *
 * @author Shubham Chauhan
 */
@Entity(tableName = "product_table")
public class Product {

    @PrimaryKey()
    @NonNull
    @SerializedName("itemCode")
    @ColumnInfo(name = "itemCode")
    private String productId;

    @SerializedName("itemName")
    @ColumnInfo(name = "itemName")
    private String productName;

    @SerializedName("dealerPrice")
    @ColumnInfo(name = "dealerPrice")
    private int productPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
