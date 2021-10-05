package com.movtech.smartpowermeter.model.Mon3Phase;

import java.util.List;

public class Data{
	private List<PhaseItem> phase;
	private String etotal;
	private String power;
	private String ampere;
	private String voltage;
	private String energy;

	public List<PhaseItem> getPhase(){
		return phase;
	}

	public String getEtotal(){
		return etotal;
	}

	public String getPower(){
		return power;
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