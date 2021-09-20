package com.movtech.smartpowermeter.model.Mon1PhaseTable;

public class DataItem{
	private String id;
	private String power;
	private String time;
	private String ampere;
	private String voltage;
	private String energy;

	public String getId(){
		return id;
	}

	public String getPower(){
		return power;
	}

	public String getTime(){
		return time;
	}

	public String getAmpere(){
		return ampere;
	}

	public String getVoltage(){
		return voltage;
	}

	public String getEnergy(){
		return energy;
	}
}
