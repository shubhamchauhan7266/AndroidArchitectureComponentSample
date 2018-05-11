package com.androidarchitecturecomponentsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.paging.DataSource;
import android.os.AsyncTask;

import com.androidarchitecturecomponentsample.database.ProductDatabase;
import com.androidarchitecturecomponentsample.database.dao.ProductDao;
import com.androidarchitecturecomponentsample.database.entity.Product;
import com.androidarchitecturecomponentsample.interfaces.AppConstant;
import com.androidarchitecturecomponentsample.interfaces.IDatabaseListener;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductDao mProductDao;
    private DataSource.Factory<Integer,Product> mDataSourceFactory;

    public ProductViewModel(Application application) {
        super(application);
        ProductDatabase productDatabase = ProductDatabase.getInstance(application);
        mProductDao = productDatabase.getProductDao();
        mDataSourceFactory = mProductDao.getAllProducts();
    }

    /**
     *
     * @return DataSource.Factory<Integer,Product>
     */
    public DataSource.Factory<Integer,Product> getAllProducts() {
        return mDataSourceFactory;
    }

    public void insertAll(Object objectData) {
        new ProductCallAsyncTask(AppConstant.INSERT_ALL).execute(objectData);
    }

    private class ProductCallAsyncTask extends AsyncTask<Object, Void, Boolean> {

          private int mRequestCode;

        ProductCallAsyncTask(int requestCode) {
                 mRequestCode = requestCode;
        }

        @Override
        protected Boolean doInBackground(Object... objectData) {

            switch (mRequestCode) {

                case AppConstant.INSERT_ALL: {
                    List<Product> products = (List<Product>) objectData[0];
                    mProductDao.insertAll(products);
                    return true;
                }

                default:
                    return false;
            }
        }
    }

}
