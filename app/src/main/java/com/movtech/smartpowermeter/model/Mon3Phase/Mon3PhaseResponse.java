package com.movtech.smartpowermeter.model.Mon3Phase;

import java.util.List;

public class Mon3PhaseResponse{
	private List<DataItem> data;
	private boolean error;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}
}