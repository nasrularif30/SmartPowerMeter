package com.movtech.smartpowermeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView labtel, pju, pju2;
    FloatingActionButton btnLogout;
    SharedPreferences sharedPreferences;
    String id, username, nama;
    TextView tvWelcome;
    static MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFCMToken();
        FirebaseMessaging.getInstance().subscribeToTopic("splu");
        labtel = findViewById(R.id.labtel);
        pju = findViewById(R.id.pju1);
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
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        mainActivity = this;
    }
    public static MainActivity getInstance(){
        return mainActivity;
    }
    public void ClickOpen(int i){
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("kode",i);
        startActivity(intent);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.pju1 :
                ClickOpen(1);
                break;
        }

    }
    private void getFCMToken(){
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                Log.i("FireBaseToken", "Firebase Token : " + token);
//                getDeviceToken(token);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("FireBaseToken", "onFailure : " + e.toString());
            }
        });
    }

}