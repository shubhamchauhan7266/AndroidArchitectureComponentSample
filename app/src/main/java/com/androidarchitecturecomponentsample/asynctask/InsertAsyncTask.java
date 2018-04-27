package com.androidarchitecturecomponentsample.asynctask;

import android.os.AsyncTask;

import com.androidarchitecturecomponentsample.database.dao.ProductDao;
import com.androidarchitecturecomponentsample.database.entity.Product;
import com.androidarchitecturecomponentsample.interfaces.AppConstant;
import com.androidarchitecturecomponentsample.interfaces.IDatabaseListener;

import java.util.List;

/**
 * @author :Shubham Chauhan
 * @param <T> : it contains the Object Data
 * @param <X> :  it contains the Dao
 */
public class InsertAsyncTask<T,X> extends AsyncTask<T, Void, Boolean> {
    private X mAsyncTaskDao;
    private int mRequestCode;
    private IDatabaseListener mIDatabaseListener;

    InsertAsyncTask(IDatabaseListener iDatabaseListener,X dao, int requestCode) {
        mAsyncTaskDao = dao;
        mRequestCode = requestCode;
        this.mIDatabaseListener=iDatabaseListener;
    }

    @SafeVarargs
    @Override
    protected final Boolean doInBackground(T... lists) {
        switch (mRequestCode) {
            case AppConstant.INSERT_ALL:
                if(lists!=null && lists.length>0){
                    List<Product> products = (List<Product>) lists[0];
                    ProductDao productDao = (ProductDao) mAsyncTaskDao;
                    productDao.insertAll(products);
                }
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (result) {
            mIDatabaseListener.onSucess(true);
        } else {
            mIDatabaseListener.onError("Error Occured while delete data from Database.");
        }
    }
}
