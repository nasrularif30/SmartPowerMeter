package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.InflateException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.movtech.smartpowermeter.apihelper.BaseApiService;
import com.movtech.smartpowermeter.apihelper.RetrofitClient;
import com.movtech.smartpowermeter.model.InfoAkun.InfoAkunResponse;
import com.movtech.smartpowermeter.model.Mon1Phase.Mon1PhaseResponse;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    CircleImageView foto;
    TextView tvNama, tvUsername;
    EditText etUsername, etNama, etPassword, etEmail, etNomor, etAlamat;
    Button  btnLogout, btnSave;
    ImageView btnInfo;
    Intent intent;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    Context mContext;
    BaseApiService mApiService;
    String id, username, nama, password,email,alamat, level;
    Long nohp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnInfo = findViewById(R.id.btn_about);
        btnLogout = findViewById(R.id.btn_logout);
        btnSave = findViewById(R.id.btn_save);
        etAlamat = findViewById(R.id.et_alamat);
        etEmail = findViewById(R.id.et_email);
        etNama = findViewById(R.id.et_name);
        etNomor = findViewById(R.id.et_telfon);
        etUsername = findViewById(R.id.et_username);
        tvUsername = findViewById(R.id.txt_username);
        tvNama = findViewById(R.id.txt_nama);

        etUsername.setClickable(false);
        etNama.setClickable(false);
        etNomor.setClickable(false);
        etEmail.setClickable(false);
        etAlamat.setClickable(false);

        etUsername.setFocusableInTouchMode(false);
        etNama.setFocusableInTouchMode(false);
        etNomor.setFocusableInTouchMode(false);
        etEmail.setFocusableInTouchMode(false);
        etAlamat.setFocusableInTouchMode(false);

        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        mContext = this;

        id = sharedPreferences.getString("id", null);
        username = sharedPreferences.getString("username", null);
        nama = sharedPreferences.getString("nama", null);


        tvNama.setText(nama);
        tvUsername.setText(username);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });
        BaseApiService baseApiService = RetrofitClient.getClient().create(BaseApiService.class);
        Call<InfoAkunResponse> call = baseApiService.getInfoAkun("info_akun", id);
        call.enqueue(new Callback<InfoAkunResponse>() {
            @Override
            public void onResponse(Call<InfoAkunResponse> call, Response<InfoAkunResponse> response) {
                if (response.body().isError()){
                    Toast.makeText(ProfileActivity.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
                }
                else {
                    etUsername.setText(response.body().getData().getUsername());
                    etNama.setText(response.body().getData().getNama());
                    etEmail.setText(response.body().getData().getNama());
                    etNomor.setText(response.body().getData().getTelf());
                    etAlamat.setText(response.body().getData().getAlamat());
                }
            }

            @Override
            public void onFailure(Call<InfoAkunResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Gagal mengambil data!", Toast.LENGTH_LONG).show();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(ProfileActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Apakah anda yakin?")
                        .setContentText("untuk keluar dari akun ini")
                        .setConfirmText("Ya")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("sudahLogin", false);
                                editor.putString("nama", null);
                                editor.putString("username", null);
                                editor.putString("id", null);
                                editor.apply();

                                //Starting login activity
                                Intent intent = new Intent(ProfileActivity.this, SplashScreen.class);
                                startActivity(intent);
                                finish();

                            }
                        })
                        .setCancelButton("Tidak", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}