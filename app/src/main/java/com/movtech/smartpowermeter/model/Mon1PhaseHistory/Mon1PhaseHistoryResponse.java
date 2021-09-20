package com.movtech.smartpowermeter.model.Mon1PhaseHistory;

import java.util.List;

public class Mon1PhaseHistoryResponse{
	private List<DataItem> data;
	private boolean error;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}
}