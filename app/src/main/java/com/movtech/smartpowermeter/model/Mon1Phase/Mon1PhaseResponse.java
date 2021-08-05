package com.movtech.smartpowermeter.model.Mon1Phase;

public class Mon1PhaseResponse{
	private Data data;
	private boolean error;

	public Data getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}
}
