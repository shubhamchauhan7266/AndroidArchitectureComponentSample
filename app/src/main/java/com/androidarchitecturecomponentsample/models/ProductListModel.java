package com.androidarchitecturecomponentsample.models;

import com.androidarchitecturecomponentsample.database.entity.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Shubham Gupta
 */

public class ProductListModel{

    @SerializedName("indentDetails")
    @Expose
    public List<Product> indentDetails;
}
