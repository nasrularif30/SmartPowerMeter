package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.movtech.smartpowermeter.apihelper.BaseApiService;
import com.movtech.smartpowermeter.apihelper.RetrofitClient;
import com.movtech.smartpowermeter.model.Mon1Phase.Mon1PhaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    TextView tvBiaya, tvTegangan, tvArus, tvDaya, tvEnergy, tvDetail;
    String nArus, nBiaya, nDaya, nEnergy, nTegangan;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvArus = findViewById(R.id.tv_arus);
        tvBiaya = findViewById(R.id.tv_biaya);
        tvDaya = findViewById(R.id.tv_daya);
        tvEnergy = findViewById(R.id.tv_energi);
        tvTegangan = findViewById(R.id.tv_tegangan);
        tvDetail = findViewById(R.id.tv_detail);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

                tvDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DetailActivity.this, Table1Phase.class);
                        i.putExtra("type","realtime");
                        startActivity(i);
                    }
                });
                BaseApiService baseApiService = RetrofitClient.getClient().create(BaseApiService.class);
                Call<Mon1PhaseResponse> call = baseApiService.getOnePhase("monitoring_1phase");
                call.enqueue(new Callback<Mon1PhaseResponse>() {
                    @Override
                    public void onResponse(Call<Mon1PhaseResponse> call, Response<Mon1PhaseResponse> response) {
                        if (response.body().isError()){
                            Toast.makeText(DetailActivity.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            nArus = response.body().getData().getArus();
                            nDaya = response.body().getData().getPower();
                            nEnergy = response.body().getData().getEnergy();
                            nTegangan = response.body().getData().getVoltage();
                            nBiaya = response.body().getData().getBiaya();
                            tvBiaya.setText("Rp"+nBiaya);
                            tvArus.setText(nArus);
                            tvDaya.setText(nDaya);
                            tvEnergy.setText(nEnergy);
                            tvTegangan.setText(nTegangan);
                        }
                    }

                    @Override
                    public void onFailure(Call<Mon1PhaseResponse> call, Throwable t) {
                        Toast.makeText(DetailActivity.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                    }
                });

    }
}