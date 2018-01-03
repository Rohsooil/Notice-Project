package com.example.food8.rotice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Wind_List_Activity extends AppCompatActivity {

    WebView webView;
    TextView textView;
    String htmlPageUrl = "http://wind.gachon.ac.kr/client/program/programList.do?programType=newProgram";
    String Content = "";
    String jvScript = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wind_list);
        //textView = (TextView)findViewById(R.id.wind_view);


        webView = (WebView) findViewById(R.id.wind_view);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setBackgroundColor(0);
        webView.getSettings().setJavaScriptEnabled(true);
//        //webView.loadUrl(htmlPageUrl);
        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute();
    }


    private class JsoupAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            try {
                Connection.Response response = Jsoup.connect(htmlPageUrl)
                        .method(Connection.Method.GET)
                        .execute();
                Document doc = response.parse();

                //Document doc = Jsoup.connect(htmlPageUrl).get();
                Elements links = doc.select(".list_con");
                Elements script = doc.select("#cBody + script");
                for(Element element : links){
                    result += element.html().trim() + "\n";
                }
                for(Element element : script){
                    jvScript += element.html().trim() + "\n";
                }
//                Log.v("html!", Content);
                Log.v("Script!",jvScript);
                //InputStream is = getInputStreamFromUrl(htmlPageUrl);

                //result = convertInputStreamToString(is);//이 함수는 이 페이지를 참고


                Log.v("html!", result);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            webView.loadData(result,"text/html","UTF-8");
            webView.loadUrl("javascript:[script "+jvScript+"]");
        }
    }
}
