package com.movtech.smartpowermeter.model.LoginModel;

public class LoginResponse{
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
