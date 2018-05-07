package com.androidarchitecturecomponentsample.database.controller;

import android.app.Application;
import android.os.AsyncTask;

import com.androidarchitecturecomponentsample.database.ProductDatabase;
import com.androidarchitecturecomponentsample.database.dao.ProductDao;
import com.androidarchitecturecomponentsample.database.entity.Product;
import com.androidarchitecturecomponentsample.interfaces.AppConstant;
import com.androidarchitecturecomponentsample.interfaces.IDatabaseListener;

import java.util.List;

public class ProductDatabaseController {

    private IDatabaseListener iDatabaseListener;
    private ProductDao mProductDao;

    public ProductDatabaseController(Application application, IDatabaseListener iDatabaseListener) {
        ProductDatabase productDatabase = ProductDatabase.getInstance(application);
        this.iDatabaseListener = iDatabaseListener;
        mProductDao = productDatabase.getProductDao();
    }

    public void insertAll(Object objectData) {
        new ProductCallAsyncTask(AppConstant.INSERT_ALL).execute(objectData);
    }

    public void getAllProducts() {
        new ProductCallAsyncTask(AppConstant.RETRIEVE_ALL).execute();
    }


    private class ProductCallAsyncTask extends AsyncTask<Object, Void, Boolean> {

          private int mRequestCode;
        private Object mResponse;

        ProductCallAsyncTask(int requestCode) {
                 mRequestCode = requestCode;
        }

        @Override
        protected Boolean doInBackground(Object... objectData) {

            switch (mRequestCode) {
                case AppConstant.RETRIEVE_ALL: {
                    mResponse = mProductDao.getAllProducts();
                    return true;
                }

                case AppConstant.INSERT_ALL: {
                    List<Product> products = (List<Product>) objectData[0];
                    mProductDao.insertAll(products);
                    return true;
                }

                default:
                    return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                iDatabaseListener.onDbOperationSucess(mRequestCode,mResponse);
            } else {
                iDatabaseListener.onDbOperationFailed(mRequestCode,"Error during fetching the database ");
            }
        }
    }

}
