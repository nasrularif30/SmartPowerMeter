package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.movtech.smartpowermeter.apihelper.BaseApiService;
import com.movtech.smartpowermeter.apihelper.RetrofitClient;
import com.movtech.smartpowermeter.model.Mon1PhaseTable.DataItem;
import com.movtech.smartpowermeter.model.Mon1PhaseTable.Mon1PhaseTableAdapter;
import com.movtech.smartpowermeter.model.Mon1PhaseTable.Mon1PhaseTableResponse;
import com.movtech.smartpowermeter.model.Mon3Phase.Data;
import com.movtech.smartpowermeter.model.Mon3Phase.Mon3PhaseAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Table1Phase extends AppCompatActivity {

    RecyclerView recyclerView;
    Mon1PhaseTableAdapter adapter;
    ArrayList<DataItem> recyclerData = new ArrayList<>();
    List<DataItem> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table1_phase);

        recyclerView = findViewById(R.id.rv_data);

        BaseApiService baseApiService = RetrofitClient.getClient().create(BaseApiService.class);
        Call<Mon1PhaseTableResponse> call = baseApiService.getTable1Phase("grafik_1phase");
        call.enqueue(new Callback<Mon1PhaseTableResponse>() {
            @Override
            public void onResponse(Call<Mon1PhaseTableResponse> call, Response<Mon1PhaseTableResponse> response) {
                if (response.body().isError()){
                    Toast.makeText(Table1Phase.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                }
                else {
//                    recyclerView.setHasFixedSize(true);
//                    recyclerView.addItemDecoration(new DividerItemDecoration(Table1Phase.this,
//                            DividerItemDecoration.HORIZONTAL));
//                    recyclerView.addItemDecoration(new DividerItemDecoration(Table1Phase.this,
//                            DividerItemDecoration.VERTICAL));
//                    recyclerView.setLayoutManager(new GridLayoutManager(Table1Phase.this,6));
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
    }
}