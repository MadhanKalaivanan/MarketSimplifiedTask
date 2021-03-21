package com.t.marketsimplified.common;

import android.widget.Toast;

import com.t.marketsimplified.MarketSimplifiedTask;

public class ToastMessage {
    public static void toast(String s){
        Toast.makeText(MarketSimplifiedTask.getApplicationInstance(), s, Toast.LENGTH_SHORT).show();
    }
}
