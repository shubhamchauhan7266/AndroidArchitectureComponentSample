package com.androidarchitecturecomponentsample.asynctask;

import android.os.AsyncTask;

import com.androidarchitecturecomponentsample.interfaces.IDatabaseListener;

/**
 * @param <T> : it contains the object
 * @param <X> : it contains the dao
 * @author :Shubham Gupta
 */
public class UpdateAsyncTask<T, X> extends AsyncTask<T, Void, Boolean> {
    private int reqCode;
    private IDatabaseListener iDatabaseListener;
    Object data;
    X dao;

    public UpdateAsyncTask(IDatabaseListener iDatabaseListener, X dao, int requestCode) {
        this.dao = dao;
        this.iDatabaseListener = iDatabaseListener;
        this.reqCode = requestCode;
    }

    @Override
    protected Boolean doInBackground(T... object) {
        this.data = object[0];
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (result) {
            iDatabaseListener.onSucess(true);
        } else {
            iDatabaseListener.onError("Error Occured while fetching data from Database.");
        }
    }

}
