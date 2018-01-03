package com.example.food8.rotice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Wind_List_Activity extends AppCompatActivity {

    WebView webView;
    TextView textView;
    String htmlPageUrl = "http://wind.gachon.ac.kr/client/program/programList.do?programType=newProgram";
    String Content = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wind_list);
        //textView = (TextView)findViewById(R.id.wind_view);
        webView = (WebView) findViewById(R.id.wind_view);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setBackgroundColor(0);
        webView.loadUrl(htmlPageUrl);
        //JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        //jsoupAsyncTask.execute();
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
                Elements links = doc.select("html");
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
            //textView.setText(Content);
        }
    }
}
