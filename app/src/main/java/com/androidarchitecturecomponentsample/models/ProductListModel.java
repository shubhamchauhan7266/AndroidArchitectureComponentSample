package com.androidarchitecturecomponentsample.models;

import com.androidarchitecturecomponentsample.database.entity.Product;

import java.io.Serializable;
import java.util.List;

/**
 * @author Shubham Gupta
 */

public class ProductListModel implements Serializable {
    public List<Product> indentDetails;
}
