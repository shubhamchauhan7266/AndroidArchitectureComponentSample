
package com.androidarchitecturecomponentsample.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductListResponseModel {

    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("groupId")
    @Expose
    private String groupId;
    @SerializedName("dealerPrice")
    @Expose
    private Integer dealerPrice;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("imageName")
    @Expose
    private String imageName;
    @SerializedName("crop")
    @Expose
    private Object crop;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("inventory")
    @Expose
    private Integer inventory;
    @SerializedName("numberInCase")
    @Expose
    private String numberInCase;
    @SerializedName("newLaunches")
    @Expose
    private Integer newLaunches;
    @SerializedName("technicalName")
    @Expose
    private String technicalName;
    @SerializedName("brandPortfolio")
    @Expose
    private String brandPortfolio;
    @SerializedName("portfolio")
    @Expose
    private String portfolio;
    @SerializedName("salesUnit")
    @Expose
    private String salesUnit;
    @SerializedName("warehouse")
    @Expose
    private Object warehouse;
    @SerializedName("pageCount")
    @Expose
    private Object pageCount;
    @SerializedName("isAddedCart")
    @Expose
    private Integer isAddedCart;
    @SerializedName("createdDate")
    @Expose
    private Object createdDate;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getDealerPrice() {
        return dealerPrice;
    }

    public void setDealerPrice(Integer dealerPrice) {
        this.dealerPrice = dealerPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Object getCrop() {
        return crop;
    }

    public void setCrop(Object crop) {
        this.crop = crop;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getNumberInCase() {
        return numberInCase;
    }

    public void setNumberInCase(String numberInCase) {
        this.numberInCase = numberInCase;
    }

    public Integer getNewLaunches() {
        return newLaunches;
    }

    public void setNewLaunches(Integer newLaunches) {
        this.newLaunches = newLaunches;
    }

    public String getTechnicalName() {
        return technicalName;
    }

    public void setTechnicalName(String technicalName) {
        this.technicalName = technicalName;
    }

    public String getBrandPortfolio() {
        return brandPortfolio;
    }

    public void setBrandPortfolio(String brandPortfolio) {
        this.brandPortfolio = brandPortfolio;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public Object getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Object warehouse) {
        this.warehouse = warehouse;
    }

    public Object getPageCount() {
        return pageCount;
    }

    public void setPageCount(Object pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getIsAddedCart() {
        return isAddedCart;
    }

    public void setIsAddedCart(Integer isAddedCart) {
        this.isAddedCart = isAddedCart;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

}
