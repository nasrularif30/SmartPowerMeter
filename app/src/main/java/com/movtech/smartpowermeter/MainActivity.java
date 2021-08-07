package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    CardView labtel, pju;
    FloatingActionButton btnLogout;
    SharedPreferences sharedPreferences;
    String id, username, nama;
    TextView tvWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        labtel = findViewById(R.id.labtel);
        pju = findViewById(R.id.pju);
        btnLogout = findViewById(R.id.btn_logout);
        sharedPreferences = getSharedPreferences("data_user", Context.MODE_PRIVATE);
        tvWelcome = findViewById(R.id.welcome);

        id = sharedPreferences.getString("id", null);
        username = sharedPreferences.getString("username", null);
        nama = sharedPreferences.getString("nama", null);

        tvWelcome.setText("Selamat Datang,\n"+nama);
        labtel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Mon3PhaseActivity.class);
                startActivity(intent);
            }
        });
        pju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
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
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}