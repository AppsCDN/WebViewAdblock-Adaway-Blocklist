package com.monstertechno.adblocker;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.monstertechno.adblocker.util.AdBlocker;

import java.util.HashMap;
import java.util.Map;

public class AdBlockerWebView {

    static Map<String, Boolean> loadedUrls = new HashMap<>();

    public static boolean blockAds(WebView view, String url) {
        boolean ad;
        if (!loadedUrls.containsKey(url)) {
            ad = AdBlocker.isAd(url);
            loadedUrls.put(url, ad);
        } else {
            ad = loadedUrls.get(url);
        }
        return ad;
    }

    public static class init {
            Context context;

        public init(Context context) {
            AdBlocker.init(context);
            this.context = context;
        }

        public void initializeWebView(WebView mWebView) {
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                webSettings.setDisplayZoomControls(false);
            }
            webSettings.setBuiltInZoomControls(true);
            webSettings.setSupportZoom(true);
            webSettings.setDomStorageEnabled(true);
        }
    }
}
