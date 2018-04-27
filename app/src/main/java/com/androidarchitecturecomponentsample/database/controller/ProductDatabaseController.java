package com.androidarchitecturecomponentsample.database.controller;

import android.app.Application;
import android.os.AsyncTask;

import com.androidarchitecturecomponentsample.database.ProductDatabase;
import com.androidarchitecturecomponentsample.database.dao.ProductDao;
import com.androidarchitecturecomponentsample.database.entity.Product;
import com.androidarchitecturecomponentsample.interfaces.IDatabaseListener;

import java.util.List;

public class ProductDatabaseController {

    private IDatabaseListener iDatabaseListener;
    private ProductDao mProductDao;
    private List<Product> mAllProducts;

    public ProductDatabaseController(Application application, IDatabaseListener iDatabaseListener) {
        ProductDatabase productDatabase = ProductDatabase.getInstance(application);
        this.iDatabaseListener = iDatabaseListener;
        mProductDao = productDatabase.getProductDao();
    }

    public void execute(int productsCategory, ProductDao dao, Object objectData) {
        ProductCallAsyncTask productCallAsyncTask = new ProductCallAsyncTask(dao, productsCategory);
        productCallAsyncTask.execute(objectData);
    }

    public List<Product> getAllProducts() {
        return mAllProducts;
    }

    private class ProductCallAsyncTask<T, X> extends AsyncTask<T, Void, Boolean> {

        private ProductDao mAsyncTaskDao;
        private int productCat;
        private Object objectData;
        ;

        ProductCallAsyncTask(ProductDao dao, int productCat) {
            mAsyncTaskDao = dao;
            this.productCat = productCat;

        }


        @Override
        protected Boolean doInBackground(T... ts) {

            switch (productCat) {
                case 1: {
                    objectData = mAsyncTaskDao.getAllProducts();
                    return true;
                }
                case 2: {
                    List<Product> products = (List<Product>) ts[0];
                    mAsyncTaskDao.insertAll(products);
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
                iDatabaseListener.onSucess(objectData);
            } else {
                iDatabaseListener.onError("Error during fetching the database ");
            }

        }
    }

}
