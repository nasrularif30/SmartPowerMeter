package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Calendar;

public class ChartActivity extends AppCompatActivity {
        String phase, phaseName;
        Calendar calendar;
        String url;
        String type;
        String startDate, endDate;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chart);

            Intent getIntent = getIntent();
            phase = getIntent.getStringExtra("phase");
            phaseName = getIntent.getStringExtra("phaseName");
            type = getIntent.getStringExtra("type");
            startDate = getIntent.getStringExtra("startDate");
            endDate = getIntent.getStringExtra("endDate");

            Log.i("typpp", "onCreate: "+phase+type+phaseName+startDate+endDate);

            WebView view = (WebView) findViewById(R.id.webView);

            switch (type){
                case "realtime":
                    switch (phase){
                        case "1phase":
                            url = "https://iot4energy.id/api/chart.php?need=realtime&type=1phase&phase=LSTR001";
                            view.getSettings().setUseWideViewPort(true);
                            view.getSettings().setLoadWithOverviewMode(true);
                            view.getSettings().setJavaScriptEnabled(true);
                            view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                            view.setInitialScale(1);
                            view.loadUrl(url);
                            break;
                        case "3phase":
                            phaseName = phaseName.replace("Phase ", "");
                            url = "https://iot4energy.id/api/chart.php?need=realtime&type=3phase&phase="+phaseName;
                            view.getSettings().setUseWideViewPort(true);
                            view.getSettings().setLoadWithOverviewMode(true);
                            view.getSettings().setJavaScriptEnabled(true);
                            view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                            view.setInitialScale(1);
                            view.loadUrl(url);
                            break;
                        case "etot":
                            url = "https://iot4energy.id/api/chart_etotal.php?need=realtime";
                            view.getSettings().setUseWideViewPort(true);
                            view.getSettings().setLoadWithOverviewMode(true);
                            view.getSettings().setJavaScriptEnabled(true);
                            view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                            view.setInitialScale(1);
                            view.loadUrl(url);
                            break;
                    }
                    break;
                case "history":
                    switch (phase){
                        case "1phase":
                            url = "https://iot4energy.id/api/chart.php?need=history&type=1phase&phase=LSTR001&startDate="+startDate+"&endDate="+endDate;
                            Log.i("url1p", "url: "+url);
                            view.getSettings().setUseWideViewPort(true);
                            view.getSettings().setLoadWithOverviewMode(true);
                            view.getSettings().setJavaScriptEnabled(true);
                            view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                            view.setInitialScale(1);
                            view.loadUrl(url);
                            break;
                        case "3phase":
                            phaseName = phaseName.replace("Phase ", "");
                            url = "https://iot4energy.id/api/chart.php?need=history&type=3phase&phase="+phaseName+"&startDate="+startDate+"&endDate="+endDate;
                            Log.i("url3p", "url: "+url);view.getSettings().setUseWideViewPort(true);
                            view.getSettings().setLoadWithOverviewMode(true);
                            view.getSettings().setJavaScriptEnabled(true);
                            view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                            view.setInitialScale(1);
                            view.loadUrl(url);
                            break;
                        case "etot":
                            url = "https://iot4energy.id/api/chart_etotal.php?need=history&startDate="+startDate+"&endDate="+endDate;
                            Log.i("url1p", "url: "+url);
                            view.getSettings().setUseWideViewPort(true);
                            view.getSettings().setLoadWithOverviewMode(true);
                            view.getSettings().setJavaScriptEnabled(true);
                            view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                            view.setInitialScale(1);
                            view.loadUrl(url);
                            break;
                    }
                    break;
            }

    }
}