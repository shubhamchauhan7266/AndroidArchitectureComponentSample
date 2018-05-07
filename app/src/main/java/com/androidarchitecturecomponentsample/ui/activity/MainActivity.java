package com.androidarchitecturecomponentsample.ui.activity;

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
import com.androidarchitecturecomponentsample.database.entity.Product;
import com.androidarchitecturecomponentsample.interfaces.AppConstant;
import com.androidarchitecturecomponentsample.interfaces.OnItemClickListener;
import com.androidarchitecturecomponentsample.models.ProductListModel;
import com.androidarchitecturecomponentsample.models.ProductListResponse;
import com.androidarchitecturecomponentsample.ui.presenter.ProductListPresenter;
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
public class MainActivity extends AppCompatActivity implements OnItemClickListener, ProductListPresenter.ProductListView {
    private List<Product> mIndentDetailsList;
    private RecyclerView mRecyclerView;
    private View mRootView;
    private ProductRecyclerViewAdapter mProductRecyclerViewAdapter;
    private View mProgressbar;
    private ProductListPresenter mProductListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayout();

        mProductListPresenter.getProductList();
    }

    /**
     * this  method is used to initialize the layout
     */
    private void initLayout() {
        mRecyclerView = findViewById(R.id.recyclerViewId);
        mRootView = findViewById(R.id.rootView);
        mProgressbar = findViewById(R.id.fl_progressbar);

        mIndentDetailsList = new ArrayList<>();
        mProductListPresenter = new ProductListPresenter(MainActivity.this,this,getApplication());
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

    @Override
    public void onItemClick(int postion) {
        Toast.makeText(MainActivity.this, "itemClick", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemLongPressedListener(int position) {
        Toast.makeText(MainActivity.this, "item Long pressed ", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onProductListResponse(List<Product> products) {
        mIndentDetailsList = products;
        setRecyclerView();
    }

    @Override
    public void onResponseFailer(Throwable t) {
        Snackbar.make(mRootView, t.toString(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        mProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressbar.setVisibility(View.GONE);
    }
}
