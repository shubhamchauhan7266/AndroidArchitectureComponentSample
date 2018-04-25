
package com.androidarchitecturecomponentsample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesTeamResponseModel {

    @SerializedName("IsStatus")
    @Expose
    private Boolean isStatus;
    @SerializedName("Response")
    @Expose
    private Response response;
    @SerializedName("ResponseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;

    public Boolean getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(Boolean isStatus) {
        this.isStatus = isStatus;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
