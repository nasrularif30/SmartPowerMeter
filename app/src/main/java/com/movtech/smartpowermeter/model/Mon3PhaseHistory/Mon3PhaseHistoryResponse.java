package com.movtech.smartpowermeter.model.Mon3PhaseHistory;

import java.util.List;

public class Mon3PhaseHistoryResponse{
	private List<DataItem> data;
	private boolean error;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}
}