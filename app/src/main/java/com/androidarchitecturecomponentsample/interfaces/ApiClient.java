package com.androidarchitecturecomponentsample.interfaces;

import com.androidarchitecturecomponentsample.models.ProductListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ApiClient {

    @POST("PlaceAnOrder/GetIndentPlaceOrderDetailsWithProductsComplete?userCode=CMH-0075")
    Call<ProductListResponse> getProductList(@HeaderMap Map<String, String> header, @Body Object object);
}
