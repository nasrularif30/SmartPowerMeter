package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.movtech.smartpowermeter.apihelper.BaseApiService;
import com.movtech.smartpowermeter.apihelper.RetrofitClient;
import com.movtech.smartpowermeter.model.Mon3Phase.Data;
import com.movtech.smartpowermeter.model.Mon3Phase.DataItem;
import com.movtech.smartpowermeter.model.Mon3Phase.Mon3PhaseAdapter;
import com.movtech.smartpowermeter.model.Mon3Phase.Mon3PhaseResponse;
import com.movtech.smartpowermeter.model.Mon3Phase.PhaseItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mon3PhaseActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Mon3PhaseAdapter adapter;
    ArrayList<Data> recyclerData = new ArrayList<>();
    ArrayList<String> phaseName = new ArrayList<>();
    List<PhaseItem> dataList;
    TextView tvEtot, tvDetail;
    CardView cvEtot;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon3_phase);

        recyclerView = findViewById(R.id.recycler_mon3phase);
//        tvDetail = findViewById(R.id.tv_detail);
        tvEtot = findViewById(R.id.tv_energy_total);
        cvEtot = findViewById(R.id.cv_energy_total);
        Intent getIntent = getIntent();
        type = getIntent.getStringExtra("type");

//        tvDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Mon3PhaseActivity.this, ActivityTableEnergy.class);
//                intent.putExtra("type", "realtime");
//                startActivity(intent);
//            }
//        });

        cvEtot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mon3PhaseActivity.this, ActivityTableEnergy.class);
                intent.putExtra("type", "realtime");
                startActivity(intent);
            }
        });

        tvEtot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mon3PhaseActivity.this, ActivityTableEnergy.class);
                intent.putExtra("type", "realtime");
                startActivity(intent);
            }
        });

        BaseApiService baseApiService = RetrofitClient.getClient().create(BaseApiService.class);
        Call<Mon3PhaseResponse> call = baseApiService.getThreePhase("monitoring_3phase");
        call.enqueue(new Callback<Mon3PhaseResponse>() {
            @Override
            public void onResponse(Call<Mon3PhaseResponse> call, Response<Mon3PhaseResponse> response) {
                if (response.body().isError()){
                    Toast.makeText(Mon3PhaseActivity.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                }
                else {
                    dataList = response.body().getData().getPhase();
                    for (PhaseItem val: dataList) {
                        phaseName.add(val.getName());
                        recyclerData.add(val.getData());
                    }
                    tvEtot.setText(response.body().getData().getEtotal().toString());
                    adapter = new Mon3PhaseAdapter(Mon3PhaseActivity.this, recyclerData, phaseName);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Mon3PhaseActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Mon3PhaseResponse> call, Throwable t) {
                Toast.makeText(Mon3PhaseActivity.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();

            }
        });
    }

}