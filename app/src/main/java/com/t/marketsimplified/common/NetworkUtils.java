package com.t.marketsimplified.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.t.marketsimplified.MarketSimplifiedTask;


public class NetworkUtils {

    public static boolean checkNetworkConnection() {
        ConnectivityManager ConnectionManager = (ConnectivityManager) MarketSimplifiedTask.getApplicationInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ConnectionManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            ToastMessage.toast("No internet connection");
            return false;
        }
    }

}
