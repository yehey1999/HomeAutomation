package com.exclikers.homeautomationsystem;



import android.os.Bundle;


import android.app.Activity;
import android.webkit.WebView;


public class Devices extends Activity  {

    WebView webview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.devices_next);


        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.loadUrl("file:///android_asset/index.html");


    }




}

