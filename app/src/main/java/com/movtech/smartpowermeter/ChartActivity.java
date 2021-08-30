package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
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
    LineChart lineChart;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;
    float yvalue;
    String phase;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        lineChart = findViewById(R.id.line_chart);
//        lineChart.setNoDataText("Data Grafik Tidak Tersedia");
//        lineChart.setNoDataTextColor(Color.rgb(3, 150, 244));
//        Bundle bundle = getIntent().getExtras();
//        phase = bundle.getString("phase");


        String url = "https://iot4energy.id/api/chart1phase.php";
        WebView view = (WebView) findViewById(R.id.webView);
        WebSettings settings = view.getSettings();
        view.getSettings().setUseWideViewPort(true);
        view.getSettings().setLoadWithOverviewMode(true);
        view.getSettings().setJavaScriptEnabled(true);
        view.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        view.setInitialScale(1);
        view.loadUrl(url);

    }
    private void setLineChartData(ArrayList<Entry> voltage, ArrayList<Entry> arus, ArrayList<Entry> power, ArrayList<Entry> energy){
        iLineDataSets = new ArrayList<>();
        LineDataSet voltageLineDataSet = new LineDataSet(voltage, "Voltage");
        voltageLineDataSet.setDrawCircles(true);
        voltageLineDataSet.setCircleRadius(2);
        voltageLineDataSet.setDrawValues(false);
        voltageLineDataSet.setLineWidth(2);
        voltageLineDataSet.setColor(Color.RED);
        voltageLineDataSet.setCircleColor(Color.RED);
        iLineDataSets.add(voltageLineDataSet);

        LineDataSet arusLineDataSet = new LineDataSet(voltage, "Arus");
        arusLineDataSet.setDrawCircles(true);
        arusLineDataSet.setCircleRadius(2);
        arusLineDataSet.setDrawValues(false);
        arusLineDataSet.setLineWidth(2);
        arusLineDataSet.setColor(Color.YELLOW);
        arusLineDataSet.setCircleColor(Color.YELLOW);
        iLineDataSets.add(arusLineDataSet);

        LineDataSet powerLineDataSet = new LineDataSet(voltage, "Power");
        powerLineDataSet.setDrawCircles(true);
        powerLineDataSet.setCircleRadius(2);
        powerLineDataSet.setDrawValues(false);
        powerLineDataSet.setLineWidth(2);
        powerLineDataSet.setColor(Color.BLUE);
        powerLineDataSet.setCircleColor(Color.BLUE);
        iLineDataSets.add(powerLineDataSet);

        LineDataSet energyLineDataSet = new LineDataSet(voltage, "Energy");
        energyLineDataSet.setDrawCircles(true);
        energyLineDataSet.setCircleRadius(2);
        energyLineDataSet.setDrawValues(false);
        energyLineDataSet.setLineWidth(2);
        energyLineDataSet.setColor(Color.GREEN);
        energyLineDataSet.setCircleColor(Color.GREEN);
        iLineDataSets.add(energyLineDataSet);

        lineData = new LineData(iLineDataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}