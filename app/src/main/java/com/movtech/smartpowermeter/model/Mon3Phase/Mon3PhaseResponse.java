package com.movtech.smartpowermeter.model.Mon3Phase;

public class Mon3PhaseResponse{
	private Data data;
	private boolean error;

	public Data getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}
}
