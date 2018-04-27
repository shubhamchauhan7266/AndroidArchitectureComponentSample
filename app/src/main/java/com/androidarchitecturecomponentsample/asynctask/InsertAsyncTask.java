package com.androidarchitecturecomponentsample.asynctask;

import android.os.AsyncTask;

import com.androidarchitecturecomponentsample.database.dao.ProductDao;
import com.androidarchitecturecomponentsample.database.entity.Product;

import java.util.List;

/**
 * @author :Shubham Chauhan
 * @param <T> : it contains the Object Data
 * @param <X> :  it contains the Dao
 */
public class InsertAsyncTask<T,X> extends AsyncTask<T, Void, Void> {
    private X mAsyncTaskDao;
    private int mRequestCode;

    InsertAsyncTask(X dao,int requestCode) {
        mAsyncTaskDao = dao;
        mRequestCode=requestCode;
    }

    @Override
    protected Void doInBackground(T... lists) {
        switch (mRequestCode){
        }
        List<Product> products = (List<Product>) lists[0];
//        mAsyncTaskDao.insertAll(lists[0]);
        return null;
    }
}
