package com.androidarchitecturecomponentsample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductListResponse{

    @SerializedName("IsStatus")
    @Expose
    public boolean IsStatus;

    @SerializedName("Response")
    @Expose
    public ProductListModel Response;
}
