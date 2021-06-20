package com.susheelkaram.dating_app.data.api.model.profile;

import com.google.gson.annotations.SerializedName;

public class DrinkingV1{

	@SerializedName("name_alias")
	private String nameAlias;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getNameAlias(){
		return nameAlias;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}