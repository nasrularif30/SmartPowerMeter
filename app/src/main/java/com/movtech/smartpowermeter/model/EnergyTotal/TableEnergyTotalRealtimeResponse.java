package com.movtech.smartpowermeter.model.EnergyTotal;

import java.util.List;

public class TableEnergyTotalRealtimeResponse{
	private List<DataItem> data;
	private boolean error;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}
}