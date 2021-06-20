package com.susheelkaram.dating_app.data.api.model.profile;

import com.google.gson.annotations.SerializedName;

public class MotherTongue{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}