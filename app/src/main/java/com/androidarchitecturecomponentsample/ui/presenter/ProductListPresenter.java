package com.androidarchitecturecomponentsample.ui.presenter;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;

import com.androidarchitecturecomponentsample.application.MyApplication;
import com.androidarchitecturecomponentsample.database.controller.ProductDatabaseController;
import com.androidarchitecturecomponentsample.database.entity.Product;
import com.androidarchitecturecomponentsample.interfaces.ApiClient;
import com.androidarchitecturecomponentsample.interfaces.AppConstant;
import com.androidarchitecturecomponentsample.interfaces.IDatabaseListener;
import com.androidarchitecturecomponentsample.models.ProductListResponse;
import com.androidarchitecturecomponentsample.utils.OtherUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListPresenter implements IDatabaseListener {
    private final ApiClient mNetworkApi;
    private final ProductListView mView;
    private Context mContext;
    private final ProductDatabaseController mProductDatabaseController;

    public ProductListPresenter(Context context, ProductListView view, Application application) {
        this.mNetworkApi = MyApplication.getClient();
        mProductDatabaseController = new ProductDatabaseController(application, this);
        mView = view;
        mContext = context;
    }

    /**
     * Method is used to get ProductList from Api.
     */
    public void getProductList() {
        mView.showProgressBar();
        if (!OtherUtil.isNetworkEnabled(mContext)) {
            mProductDatabaseController.getAllProducts();
        } else {

            Call<ProductListResponse> responseCall = mNetworkApi.getProductList(getHeader(), getJsonPayload());
            responseCall.enqueue(new Callback<ProductListResponse>() {
                @Override
                public void onResponse(@NonNull Call<ProductListResponse> call, @NonNull Response<ProductListResponse> response) {
                    mView.hideProgressBar();
                    if (response != null && response.body() != null) {
                        ProductListResponse productListResponse = response.body();
                        if (productListResponse != null && productListResponse.IsStatus) {
                            mProductDatabaseController.insertAll(productListResponse.Response.indentDetails);
                            mView.onProductListResponse(productListResponse.Response.indentDetails);
                        }
                    } else {
                        mView.onResponseFailer(new Exception("Something went wrong"));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ProductListResponse> call, @NonNull Throwable t) {
                    mView.hideProgressBar();
                    mView.onResponseFailer(t);
                }
            });
        }
    }

    /**
     * Method is used to get header for Product Api.
     *
     * @return header
     */
    private HashMap<String, String> getHeader() {
        HashMap<String, String> headerHashMap = new HashMap<>();
        headerHashMap.put("AuthToken", "lW_LActKsl9_VSB1LbDYPG");
        headerHashMap.put("Content-Type", "application/json");
        return headerHashMap;
    }

    /**
     * Method is used to get Json Payload for Product Api.
     *
     * @return jsonPayload
     */
    private Object getJsonPayload() {
        HashMap<String, String> params = new HashMap<>();
        try {
            params.put("searchSelectedBrand", "");
            params.put("searchsSlectedCrop", "");
            params.put("searchSelectedSize", "");
            params.put("newLaunches", "");
            params.put("productOnOffer", "");
            params.put("outOfStcokCheck", "");
            params.put("likeSearch", "");
            params.put("pageIndex", "1");
            params.put("pageSize", "10");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    @Override
    public void onDbOperationSucess(int requestCode, Object response) {
        mView.hideProgressBar();

        switch (requestCode) {

            case AppConstant.INSERT_ALL:
                break;

            case AppConstant.RETRIEVE_ALL:
                List<Product> products = (List<Product>) response;
                mView.onProductListResponse(products);
                break;
        }
    }

    @Override
    public void onDbOperationFailed(int requestCode, String error) {

    }

    public interface ProductListView {
        void onProductListResponse(List<Product> products);

        void onResponseFailer(Throwable t);

        void showProgressBar();

        void hideProgressBar();
    }
}
