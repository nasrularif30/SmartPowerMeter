package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.movtech.smartpowermeter.apihelper.BaseApiService;
import com.movtech.smartpowermeter.apihelper.RetrofitClient;
import com.movtech.smartpowermeter.model.Mon1PhaseTable.Mon1PhaseTableAdapter;
import com.movtech.smartpowermeter.model.Mon3PhaseHistory.Mon3PhaseHistoryAdapter;
import com.movtech.smartpowermeter.model.Mon3PhaseHistory.Mon3PhaseHistoryResponse;
import com.movtech.smartpowermeter.model.Mon3PhaseTable.DataItem;
import com.movtech.smartpowermeter.model.Mon3PhaseTable.Mon3PhaseTableAdapter;
import com.movtech.smartpowermeter.model.Mon3PhaseTable.Mon3PhaseTableResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.movtech.smartpowermeter.Table1Phase.refreshtime;

public class ActivityTable3Phase extends AppCompatActivity {

    RecyclerView recyclerView;
    Mon3PhaseTableAdapter adapter;
    Mon3PhaseHistoryAdapter historyAdapter;
    ArrayList<DataItem> recyclerData = new ArrayList<>();
    ArrayList<com.movtech.smartpowermeter.model.Mon3PhaseHistory.DataItem> recyclerHistoryData = new ArrayList<>();
    List<DataItem> dataList;
    List<com.movtech.smartpowermeter.model.Mon3PhaseHistory.DataItem> dataListHistory;
    TextView txtTitle;
    String type, phase, phaseName, startDate, endDate;
    Button btnHistory;
    FloatingActionButton fabChart;
    Handler handler = new Handler();
    Runnable refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table3_phase);

        recyclerView = findViewById(R.id.rv_data);
        txtTitle = findViewById(R.id.tv_title);
        btnHistory = findViewById(R.id.btn_history);
        fabChart = findViewById(R.id.btnchart);

        Intent getIntent = getIntent();
        type = getIntent.getStringExtra("type");
        phase = getIntent.getStringExtra("phase");
        phaseName = getIntent.getStringExtra("phaseName");
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTable3Phase.this, DateHistoryActivity.class);
                intent.putExtra("phase", phase);
                intent.putExtra("phaseName", phaseName);
                startActivity(intent);
            }
        });
                switch (type){
                    case "realtime":
                        fabChart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ActivityTable3Phase.this, ChartActivity.class);
                                intent.putExtra("type", type);
                                intent.putExtra("phase", phase);
                                intent.putExtra("phaseName", phaseName);
                                startActivity(intent);
                            }
                        });
                        refresh = new Runnable() {
                            public void run() {
                        if (getIntent().hasExtra("phaseName")){
                            String phaseName = getIntent().getStringExtra("phaseName");
                            txtTitle.setText("Table Monitoring 3 Phase ("+phaseName+")");
                            BaseApiService baseApiService = RetrofitClient.getClient().create(BaseApiService.class);
                            Call<Mon3PhaseTableResponse> call = baseApiService.getTable3Phase("grafik_3phase", phaseName);
                            call.enqueue(new Callback<Mon3PhaseTableResponse>() {
                                @Override
                                public void onResponse(Call<Mon3PhaseTableResponse> call, Response<Mon3PhaseTableResponse> response) {
                                    if (response.body().isError()){
                                        Toast.makeText(ActivityTable3Phase.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        recyclerData.clear();
                                        dataList = response.body().getData();
                                        for (DataItem val: dataList) {
                                            recyclerData.add(val);
                                        }
                                        adapter = new Mon3PhaseTableAdapter(recyclerData);
                                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityTable3Phase.this);
                                        recyclerView.setLayoutManager(layoutManager);
                                        recyclerView.setAdapter(adapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Mon3PhaseTableResponse> call, Throwable t) {

                                }
                            });
                        }
                        handler.postDelayed(refresh, refreshtime);
                            }
                        };
                        handler.post(refresh);

                        break;
                    case "history":
                        startDate = getIntent.getStringExtra("startDate");
                        endDate = getIntent.getStringExtra("endDate");
                        fabChart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ActivityTable3Phase.this, ChartActivity.class);
                                intent.putExtra("type", type);
                                intent.putExtra("phase", phase);
                                intent.putExtra("phaseName", phaseName);
                                intent.putExtra("startDate", startDate);
                                intent.putExtra("endDate", endDate);
                                startActivity(intent);
                            }
                        });
                        if (getIntent().hasExtra("phaseName")){
                            String phaseName = getIntent().getStringExtra("phaseName");
                            txtTitle.setText("Table Monitoring 3 Phase ("+phaseName+")");
                            BaseApiService baseApiService = RetrofitClient.getClient().create(BaseApiService.class);
                            Call<Mon3PhaseHistoryResponse> call = baseApiService.getHistory3Phase("grafik_3phase", "3phase", phaseName,startDate,endDate);
                            call.enqueue(new Callback<Mon3PhaseHistoryResponse>() {
                                @Override
                                public void onResponse(Call<Mon3PhaseHistoryResponse> call, Response<Mon3PhaseHistoryResponse> response) {
                                    if (response.body().isError()){
                                        Toast.makeText(ActivityTable3Phase.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        dataListHistory = response.body().getData();
                                        for (com.movtech.smartpowermeter.model.Mon3PhaseHistory.DataItem val: dataListHistory) {
                                            recyclerHistoryData.add(val);
                                        }
                                        historyAdapter = new Mon3PhaseHistoryAdapter(recyclerHistoryData);
                                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityTable3Phase.this);
                                        recyclerView.setLayoutManager(layoutManager);
                                        recyclerView.setAdapter(historyAdapter);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Mon3PhaseHistoryResponse> call, Throwable t) {

                                }
                            });
                        }
                        break;
                }


    }
}