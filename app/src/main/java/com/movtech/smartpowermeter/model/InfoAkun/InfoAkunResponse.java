package com.movtech.smartpowermeter.model.InfoAkun;

public class InfoAkunResponse{
	private Data data;
	private boolean error;

	public Data getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}
}
