package com.example.newapp;
import android.app.Application;
public class GlobalClass extends Application{
	public String UserName;
	public String Password;
	public String GetUserName()
	{
		return UserName; 
	}
	
	public void Setusername(String _susername)
	{
		UserName=_susername;
	}
	public String GetPassword()
	{
		return Password;
	}
	public void Setpassword(String _spassword)
	{
		Password=_spassword;
	}
	
	
	
}
