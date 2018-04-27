package com.androidarchitecturecomponentsample.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
/**
 * @author Shubham Gupta
 */
public class VolleySingleton {
    private static RequestQueue requestQueue = null;

    private VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    /**
     * You can get instance of VolleySingletonClass using this method
     *
     * @param context Activity context
     * @return RequestQueue(Volley) instance
     */
    public static RequestQueue getInstance(Context context) {

        if (requestQueue == null) {
            new VolleySingleton(context);
        }
        return requestQueue;
    }


}
