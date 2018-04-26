package com.androidarchitecturecomponentsample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidarchitecturecomponentsample.R;
import com.androidarchitecturecomponentsample.adapter.ProductRecyclerViewAdapter;
import com.androidarchitecturecomponentsample.models.IndentDetails;
import com.androidarchitecturecomponentsample.models.ProductListResponseModel;
import com.androidarchitecturecomponentsample.utils.AppUrl;
import com.androidarchitecturecomponentsample.volley.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shubham Gupta
 */
public class MainActivity extends AppCompatActivity {
    private List<IndentDetails> indentDetailsList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();
        onJsonRequest();
    }

    private void initLayout() {
        recyclerView = findViewById(R.id.recyclerViewId);
    }

    /**
     * Method is used to getProduct list from server.
     */
    public void onJsonRequest() {
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, AppUrl.PRODUCT_DETAILS_URL, getJsonPayload(), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.e("Dupont", response.toString());
                ProductListResponseModel productListResponseModel = new ProductListResponseModel(response);
                indentDetailsList.addAll(productListResponseModel.getResponse().getIndentDetails());
                setRecyclerView();
            }

            private void setRecyclerView() {

                ProductRecyclerViewAdapter productRecyclerViewAdapter = new ProductRecyclerViewAdapter(MainActivity.this, indentDetailsList);
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(productRecyclerViewAdapter);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Inside REDCLUBAPP", "Error: " + error.getMessage());
                error.printStackTrace();
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
}
