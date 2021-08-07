package com.movtech.smartpowermeter.model.Mon3PhaseTable;

public class DataItem{
	private String phase;
	private String id;
	private String idUser;
	private String power;
	private String time;
	private String ampere;
	private String voltage;
	private String energy;

	public String getPhase(){
		return phase;
	}

	public String getId(){
		return id;
	}

	public String getIdUser(){
		return idUser;
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
