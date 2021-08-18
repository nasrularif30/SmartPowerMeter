package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.movtech.smartpowermeter.apihelper.BaseApiService;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    CircleImageView foto;
    TextView tvNama, tvUsername;
    EditText etUsername, etNama, etPassword, etEmail, etNomor, etAlamat;
    Button btnEdit, btnLogout, btnSave;
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

        btnEdit = findViewById(R.id.btn_ubah);
        btnLogout = findViewById(R.id.btn_logout);
        btnSave = findViewById(R.id.btn_save);
        etAlamat = findViewById(R.id.et_alamat);
        etEmail = findViewById(R.id.et_email);
        etNama = findViewById(R.id.et_name);
        etNomor = findViewById(R.id.et_telfon);
        etPassword = findViewById(R.id.et_password);
        etUsername = findViewById(R.id.et_username);
        tvUsername = findViewById(R.id.txt_username);
        tvNama = findViewById(R.id.txt_nama);

        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        mContext = this;

        id = sharedPreferences.getString("id", null);
        username = sharedPreferences.getString("username", null);
        nama = sharedPreferences.getString("nama", null);

        tvNama.setText(nama);
        tvUsername.setText(username);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("sudahLogin", false);
                editor.putString("nama", null);
                editor.putString("username", null);
                editor.putString("id", null);
                editor.apply();

                //Starting login activity
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}