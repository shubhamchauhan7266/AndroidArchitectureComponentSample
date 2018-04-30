package com.androidarchitecturecomponentsample.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Shubham Chauhan
 */
@Entity(tableName = "product_table")
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int productId;

    @SerializedName("itemCode")
    @Expose
    @ColumnInfo(name = "itemCode")
    private String productCode;

    @SerializedName("itemName")
    @Expose
    @ColumnInfo(name = "itemName")
    private String productName;

    @SerializedName("dealerPrice")
    @Expose
    @ColumnInfo(name = "dealerPrice")
    private int productPrice;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
