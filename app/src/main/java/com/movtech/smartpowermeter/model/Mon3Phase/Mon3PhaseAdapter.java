package com.movtech.smartpowermeter.model.Mon3Phase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.movtech.smartpowermeter.R;

import java.util.ArrayList;

public class Mon3PhaseAdapter extends RecyclerView.Adapter<Mon3PhaseAdapter.Mon3PhaseViewHolder> {
    private ArrayList<Data> dataList;
    private ArrayList<String> phaseName;

    public Mon3PhaseAdapter(ArrayList<Data> dataList, ArrayList<String> phaseName) {
        this.dataList = dataList;
        this.phaseName = phaseName;
    }

    @Override
    public Mon3PhaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_mon3_phase, parent, false);
        return new Mon3PhaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Mon3PhaseViewHolder holder, int position) {
        holder.txtBiaya.setText(phaseName.get(position));
        holder.txtTegangan.setText(dataList.get(position).getVoltage());
        holder.txtArus.setText(dataList.get(position).getAmpere());
        holder.txtDaya.setText(dataList.get(position).getPower());
        holder.txtEnergy.setText(dataList.get(position).getEnergy());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class Mon3PhaseViewHolder extends RecyclerView.ViewHolder{
        private TextView txtBiaya, txtTegangan, txtArus, txtDaya, txtEnergy;

        public Mon3PhaseViewHolder(View itemView) {
            super(itemView);
            txtBiaya = itemView.findViewById(R.id.tv_biaya);
            txtTegangan = itemView.findViewById(R.id.tv_tegangan);
            txtArus = itemView.findViewById(R.id.tv_arus);
            txtDaya = itemView.findViewById(R.id.tv_daya);
            txtEnergy = itemView.findViewById(R.id.tv_energi);
        }
    }
}
