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
import android.widget.Toast;

import com.movtech.smartpowermeter.apihelper.BaseApiService;
import com.movtech.smartpowermeter.apihelper.RetrofitClient;
import com.movtech.smartpowermeter.apihelper.UtillsApi;
import com.movtech.smartpowermeter.model.RegisterModel.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextView tvSudahPunya;
    EditText etUsername, etNama, etPassword, etEmail, etNomor, etAlamat;
    Button btnRegister;
    Intent intent;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    Context mContext;
    BaseApiService mApiService;
    String id, username, nama, password,email,nohp,alamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvSudahPunya = findViewById(R.id.tv_sudahpunya);
        btnRegister = findViewById(R.id.btn_register);
        etAlamat = findViewById(R.id.et_alamat);
        etEmail = findViewById(R.id.et_email);
        etNama = findViewById(R.id.et_name);
        etNomor = findViewById(R.id.et_telfon);
        etPassword = findViewById(R.id.et_password);
        etUsername = findViewById(R.id.et_username);

        username = etUsername.getText().toString();
        nama = etNama.getText().toString();
        password = etPassword.getText().toString();
        email = etEmail.getText().toString();
        nohp = etNomor.getText().toString();
        alamat = etAlamat.getText().toString();

        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        mContext = this;

        id = sharedPreferences.getString("id", null);
        username = sharedPreferences.getString("username", null);
        nama = sharedPreferences.getString("nama", null);
        boolean sudahLogin = sharedPreferences.getBoolean("sudahLogin", false);

        tvSudahPunya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RegisterActivity.this, MainActivity.class);
                if (username == null || nama == null || password == null || email == null || nohp == null || alamat == null){
                    Toast.makeText(RegisterActivity.this, "Data Harus Lengkap!", Toast.LENGTH_LONG).show();
                }
                else {
                    BaseApiService service = RetrofitClient.getClient().create(BaseApiService.class);
                    Call<RegisterResponse> call = service.registerRequest(username, nama, email, password, nohp, alamat);
                    call.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            if (response.body().isError()){
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Username, email telah digunakan\n", Toast.LENGTH_LONG).show();
                            }
                            else {
                                progressDialog.dismiss();
                                sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("sudahLogin", true);
                                editor.putString("nama", response.body().getUser().getNama());
                                editor.putString("username", response.body().getUser().getUsername());
                                editor.putString("id", response.body().getUid());
                                editor.apply();
                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(i);
                                Toast.makeText(RegisterActivity.this, "Berhasil mendaftar!", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}