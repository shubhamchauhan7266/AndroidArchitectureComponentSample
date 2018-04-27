package com.androidarchitecturecomponentsample.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * <h1><font color="orange">OtherUtil</font></h1>
 * This class contains all the other util method</p>
 *
 * @author Shubham Chauhan
 */

public class OtherUtil {

    /**
     * Method is use to check the network is enabled or not.
     *
     * @param pContext context
     * @return true if network is enabled otherwise false
     * @note android.permission.ACCESS_NETWORK_STATE is required
     */
    public static boolean isNetworkEnabled(Context pContext) {
        NetworkInfo activeNetwork = getActiveNetwork(pContext);
        return activeNetwork != null && activeNetwork.isConnected();
    }

    /**
     * Method is used to get Network Information.
     *
     * @param pContext context
     * @return NetworkInfo
     * @note android.permission.ACCESS_NETWORK_STATE is required
     */
    private static NetworkInfo getActiveNetwork(Context pContext) {
        if (pContext == null) {
            return null;
        }
        ConnectivityManager conMngr = (ConnectivityManager) pContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return conMngr == null ? null : conMngr.getActiveNetworkInfo();
    }

}
