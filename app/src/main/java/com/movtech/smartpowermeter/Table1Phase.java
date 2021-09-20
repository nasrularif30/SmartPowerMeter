package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.movtech.smartpowermeter.apihelper.BaseApiService;
import com.movtech.smartpowermeter.apihelper.RetrofitClient;
import com.movtech.smartpowermeter.model.Mon1PhaseHistory.Mon1PhaseHistoryAdapter;
import com.movtech.smartpowermeter.model.Mon1PhaseHistory.Mon1PhaseHistoryResponse;
import com.movtech.smartpowermeter.model.Mon1PhaseTable.DataItem;
import com.movtech.smartpowermeter.model.Mon1PhaseTable.Mon1PhaseTableAdapter;
import com.movtech.smartpowermeter.model.Mon1PhaseTable.Mon1PhaseTableResponse;
import com.movtech.smartpowermeter.model.Mon3Phase.Data;
import com.movtech.smartpowermeter.model.Mon3Phase.Mon3PhaseAdapter;
import com.movtech.smartpowermeter.model.Mon3PhaseHistory.Mon3PhaseHistoryResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Table1Phase extends AppCompatActivity {
    FloatingActionButton fabChart;
    Button btnHistory;
    RecyclerView recyclerView;
    Mon1PhaseTableAdapter adapter;
    Mon1PhaseHistoryAdapter historyAdapter;
    ArrayList<DataItem> recyclerData = new ArrayList<>();
    List<DataItem> dataList;
    List<com.movtech.smartpowermeter.model.Mon1PhaseHistory.DataItem> dataListHistory;
    ArrayList<com.movtech.smartpowermeter.model.Mon1PhaseHistory.DataItem> recyclerHistoryData = new ArrayList<>();
    String type, startDate, endDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table1_phase);

        recyclerView = findViewById(R.id.rv_data);
        fabChart = findViewById(R.id.btnchart);
        btnHistory = findViewById(R.id.btn_history);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Table1Phase.this, DateHistoryActivity.class);
                i.putExtra("phase", "1phase");
                startActivity(i);
            }
        });
        BaseApiService baseApiService = RetrofitClient.getClient().create(BaseApiService.class);
        switch (type){
            case ("realtime"):
                fabChart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Table1Phase.this, ChartActivity.class);
                        i.putExtra("type","realtime");
                        i.putExtra("phase", "1phase");
                        startActivity(i);
                    }
                });
                Call<Mon1PhaseTableResponse> call = baseApiService.getTable1Phase("grafik_1phase", "LSTR001");
                call.enqueue(new Callback<Mon1PhaseTableResponse>() {
                    @Override
                    public void onResponse(Call<Mon1PhaseTableResponse> call, Response<Mon1PhaseTableResponse> response) {
                        if (response.body().isError()){
                            Toast.makeText(Table1Phase.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            dataList = response.body().getData();
                            for (DataItem val: dataList) {
                                recyclerData.add(val);
                            }
                            adapter = new Mon1PhaseTableAdapter(recyclerData);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Table1Phase.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Mon1PhaseTableResponse> call, Throwable t) {
                        Toast.makeText(Table1Phase.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();

                    }
                });
                break;
            case ("history"):
                startDate = intent.getStringExtra("startDate");
                endDate = intent.getStringExtra("endDate");
                fabChart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Table1Phase.this, ChartActivity.class);
                        i.putExtra("type","history");
                        i.putExtra("phase", "1phase");
                        i.putExtra("startDate", startDate);
                        i.putExtra("endDate", endDate);
                        startActivity(i);
                    }
                });
                Call<Mon1PhaseHistoryResponse> callHistory = baseApiService.getHistory1Phase("grafik_bydate", "1phase","LSTR001", startDate, endDate);
                callHistory.enqueue(new Callback<Mon1PhaseHistoryResponse>() {
                    @Override
                    public void onResponse(Call<Mon1PhaseHistoryResponse> call, Response<Mon1PhaseHistoryResponse> response) {
                        if (response.body().isError()){
                            Toast.makeText(Table1Phase.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            dataListHistory = response.body().getData();
                            for (com.movtech.smartpowermeter.model.Mon1PhaseHistory.DataItem val: dataListHistory) {
                                recyclerHistoryData.add(val);
                            }
                            historyAdapter = new Mon1PhaseHistoryAdapter(recyclerHistoryData);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Table1Phase.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(historyAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Mon1PhaseHistoryResponse> call, Throwable t) {
                        Toast.makeText(Table1Phase.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();

                    }
                });
                break;
        }

    }
}