package com.androidarchitecturecomponentsample.ui.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.androidarchitecturecomponentsample.R;
import com.androidarchitecturecomponentsample.adapter.ProductRecyclerViewAdapter;
import com.androidarchitecturecomponentsample.database.entity.Product;
import com.androidarchitecturecomponentsample.interfaces.OnItemClickListener;
import com.androidarchitecturecomponentsample.presenter.ProductListPresenter;

/**
 * @author Shubham Gupta
 */
public class MainActivity extends AppCompatActivity implements OnItemClickListener, ProductListPresenter.ProductListView {
    private LiveData<PagedList<Product>> mIndentDetailsList;
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

        mProductListPresenter.getProductListFromDatabase().observe(this, new Observer<PagedList<Product>>() {
            @Override
            public void onChanged(@Nullable PagedList<Product> products) {
                mProductRecyclerViewAdapter.submitList(products);
            }
        });

        mProductListPresenter.getProductList();
    }

    /**
     * this  method is used to initialize the layout
     */
    private void initLayout() {
        mRecyclerView = findViewById(R.id.recyclerViewId);
        mRootView = findViewById(R.id.rootView);
        mProgressbar = findViewById(R.id.fl_progressbar);

        mProductListPresenter = new ProductListPresenter(MainActivity.this, this, getApplication());
        setRecyclerView();
    }

    /**
     * this method is used to set the recycler View
     */
    private void setRecyclerView() {
        mProductRecyclerViewAdapter = new ProductRecyclerViewAdapter(MainActivity.this);
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
    public void showProgressBar() {
        mProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressbar.setVisibility(View.GONE);
    }
}
