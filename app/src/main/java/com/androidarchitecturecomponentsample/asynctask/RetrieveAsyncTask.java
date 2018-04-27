package com.androidarchitecturecomponentsample.asynctask;

import android.os.AsyncTask;

import com.androidarchitecturecomponentsample.interfaces.IDatabaseListener;

/**
 * @author :Shubham Chauhan
 * @param <T> : it contains the  Object data
 * @param <X> : it contains the Dao
 */
public class RetrieveAsyncTask<T, X> extends AsyncTask<T, Void, Boolean> {
    private int requestCode;
    private X dao;
    private IDatabaseListener iDatabaseListener;

    public RetrieveAsyncTask(IDatabaseListener iDatabaseListener, X dao, int requestCode) {
        this.iDatabaseListener = iDatabaseListener;
        this.dao = dao;
        this.requestCode = requestCode;
    }

    @Override
    protected Boolean doInBackground(T... object) {
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
