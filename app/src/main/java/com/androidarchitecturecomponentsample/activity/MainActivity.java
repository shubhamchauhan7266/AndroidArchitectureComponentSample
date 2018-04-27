package com.androidarchitecturecomponentsample.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidarchitecturecomponentsample.R;
import com.androidarchitecturecomponentsample.adapter.ProductRecyclerViewAdapter;
import com.androidarchitecturecomponentsample.database.controller.ProductDatabaseController;
import com.androidarchitecturecomponentsample.database.entity.Product;
import com.androidarchitecturecomponentsample.interfaces.AppConstant;
import com.androidarchitecturecomponentsample.interfaces.IDatabaseListener;
import com.androidarchitecturecomponentsample.interfaces.OnItemClickListener;
import com.androidarchitecturecomponentsample.models.ProductListModel;
import com.androidarchitecturecomponentsample.utils.OtherUtil;
import com.androidarchitecturecomponentsample.utils.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shubham Gupta
 */
public class MainActivity extends AppCompatActivity implements OnItemClickListener, IDatabaseListener {
    private List<Product> mIndentDetailsList;
    private RecyclerView mRecyclerView;
    private ProductDatabaseController mProductDatabaseController;
    private View mRootView;
    private ProductRecyclerViewAdapter mProductRecyclerViewAdapter;
    private View mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        mProgressbar.setVisibility(View.VISIBLE);

        if (!OtherUtil.isNetworkEnabled(MainActivity.this)) {
            Snackbar.make(mRootView, "Internet not connected", Snackbar.LENGTH_SHORT).show();
            mProductDatabaseController.getAllProducts();
        } else {
            onJsonRequest();
        }
    }

    /**
     * this  method is used to initialize the layout
     */
    private void initLayout() {
        mRecyclerView = findViewById(R.id.recyclerViewId);
        mRootView = findViewById(R.id.rootView);
        mProgressbar = findViewById(R.id.fl_progressbar);
        mIndentDetailsList = new ArrayList<>();
        mProductDatabaseController = new ProductDatabaseController(getApplication(), this);
        setRecyclerView();
    }

    /**
     * Method is used to getProduct list from server.
     */
    public void onJsonRequest() {
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, AppConstant.PRODUCT_DETAILS_URL, getJsonPayload(), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.e("Dupont", response.toString());
                ProductListModel productListModel = null;
                try {
                    Gson gson = new Gson();
                    productListModel = gson.fromJson(String.valueOf(response.get("Response")), ProductListModel.class);
                    mIndentDetailsList = productListModel.indentDetails;
                    mProductDatabaseController.insertAll(mIndentDetailsList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mProgressbar.setVisibility(View.GONE);
                }
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Inside REDCLUBAPP", "Error: " + error.getMessage());
                error.printStackTrace();
                mProgressbar.setVisibility(View.GONE);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHeader();
            }
        };

        jsonRequest.setShouldCache(false);
        VolleySingleton.getInstance(this).add(jsonRequest);
    }

    /**
     * this method is used to set the recycler View
     */
    private void setRecyclerView() {
        mProductRecyclerViewAdapter = new ProductRecyclerViewAdapter(MainActivity.this, mIndentDetailsList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mProductRecyclerViewAdapter);
    }

    /**
     * Method is used to get Json Payload for Product Api.
     *
     * @return jsonPayload
     */
    public JSONObject getJsonPayload() {
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

    /**
     * Method is used to get header for Product Api.
     *
     * @return header
     */
    public HashMap<String, String> getHeader() {
        HashMap<String, String> headerHashMap = new HashMap<>();
        headerHashMap.put("AuthToken", "lW_LActKsl9_VSB1LbDYPG");
        return headerHashMap;
    }

    @Override
    public void onItemClick(int postion) {
        Toast.makeText(MainActivity.this, "itemClick", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemLongPressedListener(int position) {
        Toast.makeText(MainActivity.this, "item Long pressed ", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onSucess(int requestCode, Object response) {
        switch (requestCode) {
            case AppConstant.INSERT_ALL:
                mProductDatabaseController.deleteAllProducts(mIndentDetailsList);
                break;

            case AppConstant.RETRIEVE_ALL:
                mIndentDetailsList = (List<Product>) response;
                mProductRecyclerViewAdapter.setIndentDetails(mIndentDetailsList);
                mProductRecyclerViewAdapter.notifyDataSetChanged();
                mProgressbar.setVisibility(View.GONE);
                break;

            case AppConstant.DELETE_ALL:
                mProductDatabaseController.getAllProducts();
                break;

            default:
                break;
        }
    }

    @Override
    public void onError(int requestCode, String error) {
        switch (requestCode) {
            case AppConstant.INSERT_ALL:
                break;

            case AppConstant.RETRIEVE_ALL:
                break;

            default:
                break;
        }
        mProgressbar.setVisibility(View.GONE);
    }
}
