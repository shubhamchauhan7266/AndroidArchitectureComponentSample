package com.androidarchitecturecomponentsample.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Shubham Gupta
 */

public class ProductListResponseModel implements Serializable {
    private Boolean IsStatus;

    private Response Response;

    private Integer ResponseCode;

    private String ResponseMessage;

    public Boolean getIsStatus() {
        return IsStatus;
    }

    public void setIsStatus(Boolean IsStatus) {
        this.IsStatus = IsStatus;
    }

    public Response getResponse() {
        return Response;
    }

    public void setResponse(Response Response) {
        this.Response = Response;
    }

    public Integer getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(Integer ResponseCode) {
        this.ResponseCode = ResponseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String ResponseMessage) {
        this.ResponseMessage = ResponseMessage;
    }
    /**
     * this method is used to  parse the Json Object into model
     *
     * @param jsonObject : it contains the JSOn object.
     */
    public ProductListResponseModel(JSONObject jsonObject) {
        try {
            this.IsStatus = jsonObject.getBoolean("IsStatus");
            this.ResponseCode = jsonObject.getInt("ResponseCode");
            JSONObject jsonObjectResponse = (JSONObject) jsonObject.get("Response");
            JSONArray jsonElements = (JSONArray) jsonObjectResponse.get("indentDetails");
            List<IndentDetails> indentDetailsList = new ArrayList<>();
            for (int count = 0; count < jsonElements.length(); count++) {
                IndentDetails indentDetails = new IndentDetails();
                JSONObject jsonObject1 = (JSONObject) jsonElements.get(count);
                indentDetails.setItemCode(jsonObject1.getString("itemCode"));
                indentDetails.setItemName(jsonObject1.getString("itemName"));
                double price = jsonObject1.getDouble("dealerPrice");
                indentDetails.setDealerPrice(new Double(price));
                indentDetailsList.add(indentDetails);
            }
            Response = new Response();
            Response.setIndentDetails(indentDetailsList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
