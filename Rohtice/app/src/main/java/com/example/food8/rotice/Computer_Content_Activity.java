package com.example.food8.rotice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Computer_Content_Activity extends AppCompatActivity {
    WebView webView;
    WebSettings webSettings;
    private String htmlPageUrl = "";
    private String Content = "";
    private String tableStyle = "<style>td{white-space:pre;padding-left:5px;}</style>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer__content);
        htmlPageUrl = getIntent().getStringExtra("url").toString();
        webView = (WebView) findViewById(R.id.webView);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setBackgroundColor(0);
        webSettings = webView.getSettings();
        webSettings.setTextZoom(70);
        //webSettings.setDefaultFontSize(13);
        Content = tableStyle;
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
    }
    

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(htmlPageUrl).get();
                Elements links = doc.select(".text");
                for(Element element : links){
                    Content += element.html().trim() + "\n";
                }
                Log.v("html!", Content);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            webView.loadData(Content,"text/html","UTF-8");
        }
    }
}
