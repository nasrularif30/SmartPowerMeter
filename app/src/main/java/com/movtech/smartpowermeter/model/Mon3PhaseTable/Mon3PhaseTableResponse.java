package com.movtech.smartpowermeter.model.Mon3PhaseTable;

import java.util.List;

public class Mon3PhaseTableResponse{
	private List<DataItem> data;
	private boolean error;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}
}