package com.movtech.smartpowermeter.model.Mon3PhaseHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.movtech.smartpowermeter.R;

import java.util.ArrayList;

public class Mon3PhaseHistoryAdapter extends RecyclerView.Adapter<Mon3PhaseHistoryAdapter.Mon3PhaseHistoryViewHolder> {
    private ArrayList<com.movtech.smartpowermeter.model.Mon3PhaseHistory.DataItem> dataList;

    public Mon3PhaseHistoryAdapter(ArrayList<DataItem> dataList) {
        this.dataList = dataList;
    }

    @Override
    public Mon3PhaseHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_table_3phase, parent, false);
        return new Mon3PhaseHistoryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(Mon3PhaseHistoryViewHolder holder, int position) {
        holder.txtId.setText(dataList.get(position).getId());
        holder.txtVoltage.setText(dataList.get(position).getVoltage());
        holder.txtArus.setText(dataList.get(position).getAmpere());
        holder.txtPower.setText(dataList.get(position).getPower());
        holder.txtEnergy.setText(dataList.get(position).getEnergy());
        holder.txtTime.setText(dataList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class Mon3PhaseHistoryViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNo, txtId, txtVoltage, txtArus, txtPower, txtEnergy, txtTime;

        public Mon3PhaseHistoryViewHolder(View itemView) {
            super(itemView);
//            txtNo = itemView.findViewById(R.id.txt_no);
            txtId = itemView.findViewById(R.id.txt_id);
            txtVoltage = itemView.findViewById(R.id.txt_voltage);
            txtArus = itemView.findViewById(R.id.txt_arus);
            txtPower = itemView.findViewById(R.id.txt_power);
            txtEnergy = itemView.findViewById(R.id.txt_energy);
            txtTime = itemView.findViewById(R.id.txt_time);
        }
    }
}
