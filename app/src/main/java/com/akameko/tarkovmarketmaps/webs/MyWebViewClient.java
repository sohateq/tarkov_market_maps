package com.akameko.tarkovmarketmaps.webs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    String url;
    Context context;

    public MyWebViewClient(String url, Context context) {
        this.url = url;
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //if (Uri.parse(url).getHost().contains("tarkovmap.ru")) {
        if (Uri.parse(url).getHost().contains(this.url)) {
            // This is my website, so do not override; let my WebView load the page
            return false;
        }
        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
        return true;
    }
}
