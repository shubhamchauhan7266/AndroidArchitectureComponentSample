package com.androidarchitecturecomponentsample.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
