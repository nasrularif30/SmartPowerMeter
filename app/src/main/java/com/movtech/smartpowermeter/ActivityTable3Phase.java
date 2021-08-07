package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.movtech.smartpowermeter.apihelper.BaseApiService;
import com.movtech.smartpowermeter.apihelper.RetrofitClient;
import com.movtech.smartpowermeter.model.Mon1PhaseTable.Mon1PhaseTableAdapter;
import com.movtech.smartpowermeter.model.Mon3PhaseTable.DataItem;
import com.movtech.smartpowermeter.model.Mon3PhaseTable.Mon3PhaseTableAdapter;
import com.movtech.smartpowermeter.model.Mon3PhaseTable.Mon3PhaseTableResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityTable3Phase extends AppCompatActivity {

    RecyclerView recyclerView;
    Mon3PhaseTableAdapter adapter;
    ArrayList<DataItem> recyclerData = new ArrayList<>();
    List<DataItem> dataList;
    TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table3_phase);

        recyclerView = findViewById(R.id.rv_data);
        txtTitle = findViewById(R.id.tv_title);

        if (getIntent().hasExtra("phaseName")){
            String phaseName = getIntent().getStringExtra("phaseName");
            txtTitle.setText("Table Monitoring 3 Phase (Phase "+phaseName+")");
            BaseApiService baseApiService = RetrofitClient.getClient().create(BaseApiService.class);
            Call<Mon3PhaseTableResponse> call = baseApiService.getTable3Phase("grafik_3phase", phaseName);
            call.enqueue(new Callback<Mon3PhaseTableResponse>() {
                @Override
                public void onResponse(Call<Mon3PhaseTableResponse> call, Response<Mon3PhaseTableResponse> response) {
                    if (response.body().isError()){
                        Toast.makeText(ActivityTable3Phase.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
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
    }
}