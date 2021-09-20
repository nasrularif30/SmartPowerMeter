package com.movtech.smartpowermeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DateHistoryActivity extends AppCompatActivity {
    String phase, phaseName;
    TextView tvStartDate, tvEndDate;
    CardView cvStartDate, cvEndDate;
    String startDate, endDate;
    Button btnTampil;
    Calendar mCalendar;
    final public static SimpleDateFormat DateDataFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("id","ID"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_history);

        mCalendar = Calendar.getInstance();
        tvStartDate = findViewById(R.id.tvtglawal);
        tvEndDate = findViewById(R.id.tvtglakhir);
        btnTampil = findViewById(R.id.btn_tampilhistory);
        cvStartDate = findViewById(R.id.tglawal);
        cvEndDate = findViewById(R.id.tglakhir);

        cvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DateHistoryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, month);
                        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        tvStartDate.setText(DateDataFormat.format(mCalendar.getTime()));
                    }
                }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        cvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DateHistoryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, month);
                        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        tvEndDate.setText(DateDataFormat.format(mCalendar.getTime()));
                    }
                }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        Intent intent = getIntent();
        phase = intent.getStringExtra("phase");
        phaseName = intent.getStringExtra("phaseName");
        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDate = tvStartDate.getText().toString();
                endDate = tvEndDate.getText().toString();
                Log.i("strt", "onClick: "+startDate+"_"+endDate);
                if (startDate.equals("--Pilih Tanggal Awal--")||endDate.equals("--Pilih Tanggal Akhir--")||startDate.isEmpty()||endDate.isEmpty()){
                    new SweetAlertDialog(DateHistoryActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Silakan Lengkapi Data!")
                            .setContentText("Tanggal awal dan akhir tidak boleh kosong")
                            .setConfirmText("OK")
                            .show();
                }
                else {
                    switch (phase){
                        case "1phase":
                            Intent i = new Intent(DateHistoryActivity.this, Table1Phase.class);
                            i.putExtra("startDate", startDate);
                            i.putExtra("endDate", endDate);
                            i.putExtra("phase", "1phase");
                            i.putExtra("type", "history");
                            startActivity(i);
                            break;
                        case "3phase":
                            Intent i31 = new Intent(DateHistoryActivity.this, ActivityTable3Phase.class);
                            i31.putExtra("startDate", startDate);
                            i31.putExtra("endDate", endDate);
                            i31.putExtra("phase", "3phase");
                            i31.putExtra("phaseName", phaseName);
                            i31.putExtra("type", "history");
                            startActivity(i31);
                            break;

                    }

                }
            }
        });
    }
}