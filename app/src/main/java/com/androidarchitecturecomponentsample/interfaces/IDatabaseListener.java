package com.androidarchitecturecomponentsample.interfaces;

/**
 * @author :Shubham Gupta
 * this interface is used to get the callback when we want to operation the CURD operation in the Database
 */
public interface IDatabaseListener {

    void onSucess(int requestCode,Object response);

    void onError(int requestCode,String error);
}
