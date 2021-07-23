package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.movtech.smartpowermeter.apihelper.BaseApiService;
import com.movtech.smartpowermeter.apihelper.RetrofitClient;
import com.movtech.smartpowermeter.apihelper.UtillsApi;
import com.movtech.smartpowermeter.model.LoginModel.LoginResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView tvBelumPunya;
    Button btnLogin;
    Intent intent;
    EditText etUsername, etPassword;

    ProgressDialog progressDialog;
    Context mContext;
    BaseApiService mApiService;

    SharedPreferences sharedPreferences;
    private String id, username, password, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvBelumPunya= findViewById(R.id.tv_belumpunya);
        btnLogin = findViewById(R.id.btn_login);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        mContext = this;

        id = sharedPreferences.getString("id", null);
        username = sharedPreferences.getString("username", null);
        nama = sharedPreferences.getString("nama", null);
        boolean sudahLogin = sharedPreferences.getBoolean("sudahLogin", false);

        tvBelumPunya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = ProgressDialog.show(mContext, null, "Please Wait...");
                Log.i("oaoe", "onClick: "+etUsername.getText().toString()+etPassword.getText().toString());
                if (etUsername.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
                    progressDialog.dismiss();
                    Toast.makeText(mContext, "Username dan password harus diisi!", Toast.LENGTH_LONG).show();

                }
                else {
                    requestLogin();
                }
            }
        });

    }

    private void requestLogin(){
        BaseApiService service = RetrofitClient.getClient().create(BaseApiService.class);
        Call<LoginResponse> call = service.loginRequest(etUsername.getText().toString(), etPassword.getText().toString());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().isError()){
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login gagal!\nPeriksa username dan password anda\n"+response.message(), Toast.LENGTH_LONG).show();
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
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(LoginActivity.this, "Berhasil login!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Gagal\n"+t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        progressDialog.dismiss();
    }
    public void checkLogin(View arg0) {

        final String email = etUsername.getText().toString();
        if (!isValidEmail(email)) {
            //Set error message for email field
            etUsername.setError("Invalid Email");
        }

        final String pass = etPassword.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            etPassword.setError("Password cannot be empty");
        }

        if(isValidEmail(email) && isValidPassword(pass))
        {
            // Validation Completed
        }

    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }
}