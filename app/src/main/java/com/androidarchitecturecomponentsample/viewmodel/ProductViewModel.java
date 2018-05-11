package com.androidarchitecturecomponentsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.paging.DataSource;
import android.os.AsyncTask;

import com.androidarchitecturecomponentsample.database.ProductDatabase;
import com.androidarchitecturecomponentsample.database.dao.ProductDao;
import com.androidarchitecturecomponentsample.database.entity.Product;
import com.androidarchitecturecomponentsample.interfaces.AppConstant;

import java.util.List;

/**
 * This class is a view model class.
 *
 * @author Shubham Chauhan
 */
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
     * Method is used to get All Products from Database.
     *
     * @return DataSource.Factory<Integer,Product>
     */
    public DataSource.Factory<Integer,Product> getAllProducts() {
        return mDataSourceFactory;
    }

    public void insertAll(Object objectData) {
        new ProductCallAsyncTask(AppConstant.INSERT_ALL,mProductDao).execute(objectData);
    }

    /**
     * This AsyncTask class is used to perform Db operation in background thread.
     */
    private static class ProductCallAsyncTask extends AsyncTask<Object, Void, Boolean> {

          private int requestCode;
          private ProductDao productDao;

        ProductCallAsyncTask(int requestCode,ProductDao productDao) {
                 this.requestCode = requestCode;
                 this.productDao = productDao;
        }

        @Override
        protected Boolean doInBackground(Object... objectData) {

            switch (requestCode) {

                case AppConstant.INSERT_ALL: {
                    List<Product> products = (List<Product>) objectData[0];
                    productDao.insertAll(products);
                    return true;
                }

                default:
                    return false;
            }
        }
    }

}
