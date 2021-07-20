package com.movtech.smartpowermeter.model.RegisterModel;

public class RegisterResponse{
	private String uid;
	private boolean error;
	private User user;

	public String getUid(){
		return uid;
	}

	public boolean isError(){
		return error;
	}

	public User getUser(){
		return user;
	}
}
