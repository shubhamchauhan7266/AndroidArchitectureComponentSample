package com.androidarchitecturecomponentsample.models;

import java.io.Serializable;

/**
 * @author Shubham Gupta
 */

public class IndentDetails  implements Serializable{

    private String itemName;
    private Double dealerPrice;
    private String itemCode;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getDealerPrice() {
        return dealerPrice;
    }

    public void setDealerPrice(Double dealerPrice) {
        this.dealerPrice = dealerPrice;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }


}
