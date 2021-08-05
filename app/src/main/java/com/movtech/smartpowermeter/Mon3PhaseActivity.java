package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.movtech.smartpowermeter.apihelper.BaseApiService;
import com.movtech.smartpowermeter.apihelper.RetrofitClient;
import com.movtech.smartpowermeter.model.Mon3Phase.Data;
import com.movtech.smartpowermeter.model.Mon3Phase.DataItem;
import com.movtech.smartpowermeter.model.Mon3Phase.Mon3PhaseAdapter;
import com.movtech.smartpowermeter.model.Mon3Phase.Mon3PhaseResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Mon3PhaseActivity extends AppCompatActivity {
    TextView tvBiaya, tvTegangan, tvArus, tvDaya, tvEnergy, tvBiaya2, tvTegangan2, tvArus2, tvDaya2, tvEnergy2, tvBiaya3, tvTegangan3, tvArus3, tvDaya3, tvEnergy3;
    String nArus, nBiaya, nDaya, nEnergy, nTegangan, nArus2, nBiaya2, nDaya2, nEnergy2, nTegangan2, nArus3, nBiaya3, nDaya3, nEnergy3, nTegangan3;
    RecyclerView recyclerView;
    Mon3PhaseAdapter adapter;
    ArrayList<Data> recyclerData = new ArrayList<>();
    ArrayList<String> phaseName = new ArrayList<>();
    List<DataItem> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon3_phase);

        recyclerView = findViewById(R.id.recycler_mon3phase);

//        tvArus = findViewById(R.id.tv_arus);
//        tvBiaya = findViewById(R.id.tv_biaya);
//        tvDaya = findViewById(R.id.tv_daya);
//        tvEnergy = findViewById(R.id.tv_energi);
//        tvTegangan = findViewById(R.id.tv_tegangan);
//        tvArus2 = findViewById(R.id.tv_arus2);
//        tvBiaya2 = findViewById(R.id.tv_biaya2);
//        tvDaya2 = findViewById(R.id.tv_daya2);
//        tvEnergy2 = findViewById(R.id.tv_energi2);
//        tvTegangan2 = findViewById(R.id.tv_tegangan2);
//        tvArus3 = findViewById(R.id.tv_arus3);
//        tvBiaya3 = findViewById(R.id.tv_biaya3);
//        tvDaya3 = findViewById(R.id.tv_daya3);
//        tvEnergy3 = findViewById(R.id.tv_energi3);
//        tvTegangan3 = findViewById(R.id.tv_tegangan3);

        BaseApiService baseApiService = RetrofitClient.getClient().create(BaseApiService.class);
        Call<Mon3PhaseResponse> call = baseApiService.getThreePhase("monitoring_3phase");
        call.enqueue(new Callback<Mon3PhaseResponse>() {
            @Override
            public void onResponse(Call<Mon3PhaseResponse> call, Response<Mon3PhaseResponse> response) {
                if (response.body().isError()){
                    Toast.makeText(Mon3PhaseActivity.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                }
                else {
                    dataList = response.body().getData();
                    for (DataItem val: dataList) {
                        phaseName.add(val.getName());
                        recyclerData.add(val.getData());
                    }
//                    nArus = response.body().getData().get(0).getData().getAmpere();
//                    nDaya = response.body().getData().getPower();
//                    nEnergy = response.body().getData().getEnergy();
//                    nTegangan = response.body().getData().getVoltage();

//                    tvArus.setText(nArus);
//                    tvDaya.setText(nDaya);
//                    tvEnergy.setText(nEnergy);
//                    tvTegangan.setText(nTegangan);
                    adapter = new Mon3PhaseAdapter(recyclerData, phaseName);
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