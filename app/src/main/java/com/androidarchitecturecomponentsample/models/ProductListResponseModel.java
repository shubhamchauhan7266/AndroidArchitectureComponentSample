package com.androidarchitecturecomponentsample.models;

public class ProductListResponseModel {
    private String IsStatus;

    private Response Response;

    private String ResponseCode;

    private String ResponseMessage;

    public String getIsStatus ()
    {
        return IsStatus;
    }

    public void setIsStatus (String IsStatus)
    {
        this.IsStatus = IsStatus;
    }

    public Response getResponse ()
    {
        return Response;
    }

    public void setResponse (Response Response)
    {
        this.Response = Response;
    }

    public String getResponseCode ()
    {
        return ResponseCode;
    }

    public void setResponseCode (String ResponseCode)
    {
        this.ResponseCode = ResponseCode;
    }

    public String getResponseMessage ()
    {
        return ResponseMessage;
    }

    public void setResponseMessage (String ResponseMessage)
    {
        this.ResponseMessage = ResponseMessage;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IsStatus = "+IsStatus+", Response = "+Response+", ResponseCode = "+ResponseCode+", ResponseMessage = "+ResponseMessage+"]";
    }
}
