package com.movtech.smartpowermeter.model.Mon1PhaseHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.movtech.smartpowermeter.R;

import java.util.ArrayList;

public class Mon1PhaseHistoryAdapter extends RecyclerView.Adapter<Mon1PhaseHistoryAdapter.Mon1PhaseHistoryViewHolder> {
    private ArrayList<com.movtech.smartpowermeter.model.Mon1PhaseHistory.DataItem> dataList;

    public Mon1PhaseHistoryAdapter(ArrayList<DataItem> dataList) {
        this.dataList = dataList;
    }

    @Override
    public Mon1PhaseHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_table_1phase, parent, false);
        return new Mon1PhaseHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Mon1PhaseHistoryAdapter.Mon1PhaseHistoryViewHolder holder, int position) {
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

    public class Mon1PhaseHistoryViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId, txtVoltage, txtArus, txtPower, txtEnergy, txtTime;

        public Mon1PhaseHistoryViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_id);
            txtVoltage = itemView.findViewById(R.id.txt_voltage);
            txtArus = itemView.findViewById(R.id.txt_arus);
            txtPower = itemView.findViewById(R.id.txt_power);
            txtEnergy = itemView.findViewById(R.id.txt_energy);
            txtTime = itemView.findViewById(R.id.txt_time);
        }
    }
}
