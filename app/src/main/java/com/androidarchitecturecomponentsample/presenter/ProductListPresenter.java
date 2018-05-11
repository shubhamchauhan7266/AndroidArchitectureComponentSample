package com.androidarchitecturecomponentsample.presenter;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;
import android.support.annotation.NonNull;

import com.androidarchitecturecomponentsample.application.MyApplication;
import com.androidarchitecturecomponentsample.viewmodel.ProductViewModel;
import com.androidarchitecturecomponentsample.database.entity.Product;
import com.androidarchitecturecomponentsample.interfaces.ApiClient;
import com.androidarchitecturecomponentsample.models.ProductListResponse;
import com.androidarchitecturecomponentsample.utils.OtherUtil;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListPresenter {
    private final ApiClient mNetworkApi;
    private final ProductListView mView;
    private Context mContext;
    private final ProductViewModel mProductViewModel;

    public ProductListPresenter(Context context, ProductListView view, Application application) {
        this.mNetworkApi = MyApplication.getClient();
        mProductViewModel = new ProductViewModel(application);
        mView = view;
        mContext = context;
    }

    /**
     * Method is used to get ProductList from Database.
     *
     * @return PagedList<Product>
     */
    public LiveData<PagedList<Product>> getProductListFromDatabase() {
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder()).setEnablePlaceholders(true)
                        .setPrefetchDistance(10)
                        .setPageSize(20).build();

        return (LiveData<PagedList<Product>>) (new LivePagedListBuilder(mProductViewModel.getAllProducts(), pagedListConfig)).build();
    }

    /**
     * Method is used to get ProductList from Api.
     */
    public void getProductList() {
        mView.showProgressBar();
        if (OtherUtil.isNetworkEnabled(mContext)) {
            Call<ProductListResponse> responseCall = mNetworkApi.getProductList(getHeader(), getJsonPayload());
            responseCall.enqueue(new Callback<ProductListResponse>() {
                @Override
                public void onResponse(@NonNull Call<ProductListResponse> call, @NonNull Response<ProductListResponse> response) {
                    mView.hideProgressBar();
                    if (response != null && response.body() != null) {
                        ProductListResponse productListResponse = response.body();
                        if (productListResponse != null && productListResponse.IsStatus) {
                            mProductViewModel.insertAll(productListResponse.Response.indentDetails);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ProductListResponse> call, @NonNull Throwable t) {
                    mView.hideProgressBar();
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

    public interface ProductListView {

        void showProgressBar();

        void hideProgressBar();
    }
}
