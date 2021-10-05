package com.movtech.smartpowermeter.model.EnergyTotal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.movtech.smartpowermeter.R;
import com.movtech.smartpowermeter.model.EnergyTotal.DataItem;

import java.util.ArrayList;

public class TableEnergyTotalAdapter extends RecyclerView.Adapter<com.movtech.smartpowermeter.model.EnergyTotal.TableEnergyTotalAdapter.EnergyTotalViewHolder> {
    private ArrayList<com.movtech.smartpowermeter.model.EnergyTotal.DataItem> dataList;

    public TableEnergyTotalAdapter(ArrayList<DataItem> dataList) {
        this.dataList = dataList;
    }

    @Override
    public com.movtech.smartpowermeter.model.EnergyTotal.TableEnergyTotalAdapter.EnergyTotalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_table_etotal, parent, false);
        return new com.movtech.smartpowermeter.model.EnergyTotal.TableEnergyTotalAdapter.EnergyTotalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(com.movtech.smartpowermeter.model.EnergyTotal.TableEnergyTotalAdapter.EnergyTotalViewHolder holder, int position) {
        holder.txtId.setText(dataList.get(position).getId());
        holder.txtEnergy.setText(dataList.get(position).getEtotal());
        holder.txtTime.setText(dataList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class EnergyTotalViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId, txtEnergy, txtTime;

        public EnergyTotalViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_id);
            txtEnergy = itemView.findViewById(R.id.txt_energy_total);
            txtTime = itemView.findViewById(R.id.txt_time);
        }
    }
}