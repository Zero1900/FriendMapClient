package com.example.friendmap;

import android.R.integer;

public class FMEvent {
	public enum Type{
		Location,
		OtherLocation,
	}
	private Type type;
	private Object data;
	public FMEvent(Type type,Object data){
		this.type=type;
		this.data =data;
	}
	public Type getType(){
		return type;
	}
	public Object getData(){
		return data;
	}
}
