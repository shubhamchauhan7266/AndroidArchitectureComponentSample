package com.androidarchitecturecomponentsample.database.handler;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.androidarchitecturecomponentsample.database.ProductDatabase;
import com.androidarchitecturecomponentsample.database.dao.ProductDao;
import com.androidarchitecturecomponentsample.database.entity.Product;

import java.util.List;

public class ProductDatabaseHandler {

    private ProductDao mProductDao;
    private List<Product> mAllProducts;

    public ProductDatabaseHandler(Application application) {
        ProductDatabase productDatabase = ProductDatabase.getInstance(application);
        mProductDao = productDatabase.getProductDao();
        new fetchAsyncTask(mProductDao).execute();
    }

    public List<Product> getAllProducts() {
        return mAllProducts;
    }


    public void insertAll (List<Product> products) {
        new insertAsyncTask(mProductDao).execute(products);
    }

    private static class insertAsyncTask extends AsyncTask<List<Product>, Void, Void> {

        private ProductDao mAsyncTaskDao;

        insertAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(final List<Product>... params) {
            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }

    private class fetchAsyncTask extends AsyncTask<Void, Void, List<Product>> {

        private ProductDao mAsyncTaskDao;

        fetchAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAllProducts();
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            mAllProducts=products;
        }
    }
}
