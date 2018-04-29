package com.androidarchitecturecomponentsample.ui.presenter;

import android.content.Context;

import com.androidarchitecturecomponentsample.application.MyApplication;
import com.androidarchitecturecomponentsample.interfaces.ApiClient;
import com.androidarchitecturecomponentsample.models.ProductListResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListPresenter {
    private final ApiClient mNetworkApi;
    private final ProductListView mView;

    public ProductListPresenter(Context context, ProductListView view) {
        this.mNetworkApi = MyApplication.getClient();
        mView = view;
    }

    /**
     * Method is used to get ProductList from Api.
     */
    public void getProductList(){
        Call<ProductListResponse> responseCall = mNetworkApi.getProductList(getHeader(),getJsonPayload());
        responseCall.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {
                mView.hideProgressBar();
                if (response != null && response.body() != null) {
                     ProductListResponse productListResponse = response.body();
                     if(productListResponse.IsStatus){
                         mView.onProductListResponse(productListResponse);
                     }
                } else {
                    mView.onResponseFailer(new Exception("Something went wrong"));
                }
            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {
                mView.hideProgressBar();
                mView.onResponseFailer(t);
            }
        });
    }

    /**
     * Method is used to get header for Product Api.
     *
     * @return header
     */
    private HashMap<String, String> getHeader() {
        HashMap<String, String> headerHashMap = new HashMap<>();
        headerHashMap.put("AuthToken", "lW_LActKsl9_VSB1LbDYPG");
        return headerHashMap;
    }

    /**
     * Method is used to get Json Payload for Product Api.
     *
     * @return jsonPayload
     */
    private JSONObject getJsonPayload() {
        JSONObject params = new JSONObject();
        try {
            params.put("searchSelectedBrand", "");
            params.put("searchsSlectedCrop", "");
            params.put("searchSelectedSize", "");
            params.put("newLaunches", "");
            params.put("productOnOffer", "");
            params.put("outOfStcokCheck", "");
            params.put("likeSearch", "");
            params.put("pageIndex", 1);
            params.put("pageSize", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }

    public interface ProductListView{
        void onProductListResponse(ProductListResponse productListResponse);
        void onResponseFailer(Throwable t);
        void showProgressBar();
        void hideProgressBar();
    }
}
