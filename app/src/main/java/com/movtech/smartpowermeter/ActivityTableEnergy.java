package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.movtech.smartpowermeter.apihelper.BaseApiService;
import com.movtech.smartpowermeter.apihelper.RetrofitClient;
import com.movtech.smartpowermeter.model.EnergyTotal.DataItem;
import com.movtech.smartpowermeter.model.EnergyTotal.TableEnergyTotalAdapter;
import com.movtech.smartpowermeter.model.EnergyTotal.TableEnergyTotalRealtimeResponse;
import com.movtech.smartpowermeter.model.Mon1PhaseHistory.Mon1PhaseHistoryAdapter;
import com.movtech.smartpowermeter.model.Mon1PhaseTable.Mon1PhaseTableAdapter;
import com.movtech.smartpowermeter.model.Mon3PhaseTable.Mon3PhaseTableAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.movtech.smartpowermeter.Table1Phase.refreshtime;

public class ActivityTableEnergy extends AppCompatActivity {
    FloatingActionButton fabChart;
    Button btnHistory;
    RecyclerView recyclerView;
    String type, startDate, endDate;
    TableEnergyTotalAdapter adapter;
    ArrayList<com.movtech.smartpowermeter.model.EnergyTotal.DataItem> recyclerData = new ArrayList<>();
    List<com.movtech.smartpowermeter.model.EnergyTotal.DataItem> dataList;
    Handler handler = new Handler();
    Runnable refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_energy);

        recyclerView = findViewById(R.id.rv_data);
        fabChart = findViewById(R.id.btnchart);
        btnHistory = findViewById(R.id.btn_history);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        startDate = intent.getStringExtra("startDate");
        endDate = intent.getStringExtra("endDate");
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityTableEnergy.this, DateHistoryActivity.class);
                i.putExtra("phase", "etot");
                startActivity(i);
            }
        });
                BaseApiService baseApiService = RetrofitClient.getClient().create(BaseApiService.class);
                switch (type){
                    case "realtime":
                        fabChart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(ActivityTableEnergy.this, ChartActivity.class);
                                i.putExtra("phase", "etot");
                                i.putExtra("type", "realtime");
                                startActivity(i);
                            }
                        });
                        refresh = new Runnable() {
                            public void run() {
                        Call<TableEnergyTotalRealtimeResponse> call = baseApiService.getTableEnergyTotal("energi_total");
                        call.enqueue(new Callback<TableEnergyTotalRealtimeResponse>() {
                            @Override
                            public void onResponse(Call<TableEnergyTotalRealtimeResponse> call, Response<TableEnergyTotalRealtimeResponse> response) {
                                if (response.body().isError()){
                                    Toast.makeText(ActivityTableEnergy.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    dataList = response.body().getData();
                                    for (DataItem val: dataList) {
                                        recyclerData.add(val);
                                    }
                                    adapter = new TableEnergyTotalAdapter(recyclerData);
                                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityTableEnergy.this);
                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setAdapter(adapter);
                                }
                            }

                            @Override
                            public void onFailure(Call<TableEnergyTotalRealtimeResponse> call, Throwable t) {

                            }
                        });
                        handler.postDelayed(refresh, refreshtime);
                }
            };
        handler.post(refresh);
                        break;
                    case "history":
                        fabChart.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(ActivityTableEnergy.this, ChartActivity.class);
                                i.putExtra("phase", "etot");
                                i.putExtra("type", "history");
                                i.putExtra("startDate", startDate);
                                i.putExtra("endDate", endDate);
                                startActivity(i);
                            }
                        });
                        Call<TableEnergyTotalRealtimeResponse> call2 = baseApiService.getTableEnergyTotalHistory("energi_total_bydate", startDate, endDate);
                        call2.enqueue(new Callback<TableEnergyTotalRealtimeResponse>() {
                            @Override
                            public void onResponse(Call<TableEnergyTotalRealtimeResponse> call, Response<TableEnergyTotalRealtimeResponse> response) {
                                if (response.body().isError()){
                                    Toast.makeText(ActivityTableEnergy.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    recyclerData.clear();
                                    dataList = response.body().getData();
                                    for (DataItem val: dataList) {
                                        recyclerData.add(val);
                                    }
                                    adapter = new TableEnergyTotalAdapter(recyclerData);
                                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityTableEnergy.this);
                                    recyclerView.setLayoutManager(layoutManager);
                                    recyclerView.setAdapter(adapter);
                                }
                            }

                            @Override
                            public void onFailure(Call<TableEnergyTotalRealtimeResponse> call, Throwable t) {

                            }
                        });
                        break;
                }

    }
}