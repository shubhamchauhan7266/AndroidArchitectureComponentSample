
package com.androidarchitecturecomponentsample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("userID")
    @Expose
    private Object userID;
    @SerializedName("lastLoggedIn")
    @Expose
    private Object lastLoggedIn;
    @SerializedName("mappedTerritoryHeadCode")
    @Expose
    private String mappedTerritoryHeadCode;
    @SerializedName("mappedTerritoryHeadname")
    @Expose
    private String mappedTerritoryHeadname;
    @SerializedName("mappedTerritoryHeadphone")
    @Expose
    private String mappedTerritoryHeadphone;
    @SerializedName("mappedRegionalCode")
    @Expose
    private String mappedRegionalCode;
    @SerializedName("mappedRegionalHeadname")
    @Expose
    private String mappedRegionalHeadname;
    @SerializedName("mappedRegionalHeadPhone")
    @Expose
    private String mappedRegionalHeadPhone;

    public Object getUserID() {
        return userID;
    }

    public void setUserID(Object userID) {
        this.userID = userID;
    }

    public Object getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Object lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public String getMappedTerritoryHeadCode() {
        return mappedTerritoryHeadCode;
    }

    public void setMappedTerritoryHeadCode(String mappedTerritoryHeadCode) {
        this.mappedTerritoryHeadCode = mappedTerritoryHeadCode;
    }

    public String getMappedTerritoryHeadname() {
        return mappedTerritoryHeadname;
    }

    public void setMappedTerritoryHeadname(String mappedTerritoryHeadname) {
        this.mappedTerritoryHeadname = mappedTerritoryHeadname;
    }

    public String getMappedTerritoryHeadphone() {
        return mappedTerritoryHeadphone;
    }

    public void setMappedTerritoryHeadphone(String mappedTerritoryHeadphone) {
        this.mappedTerritoryHeadphone = mappedTerritoryHeadphone;
    }

    public String getMappedRegionalCode() {
        return mappedRegionalCode;
    }

    public void setMappedRegionalCode(String mappedRegionalCode) {
        this.mappedRegionalCode = mappedRegionalCode;
    }

    public String getMappedRegionalHeadname() {
        return mappedRegionalHeadname;
    }

    public void setMappedRegionalHeadname(String mappedRegionalHeadname) {
        this.mappedRegionalHeadname = mappedRegionalHeadname;
    }

    public String getMappedRegionalHeadPhone() {
        return mappedRegionalHeadPhone;
    }

    public void setMappedRegionalHeadPhone(String mappedRegionalHeadPhone) {
        this.mappedRegionalHeadPhone = mappedRegionalHeadPhone;
    }

}
