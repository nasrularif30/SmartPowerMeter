package com.movtech.smartpowermeter.model.Mon1PhaseTable;

import java.util.List;

public class Mon1PhaseTableResponse {
	private List<DataItem> data;
	private boolean error;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}
}