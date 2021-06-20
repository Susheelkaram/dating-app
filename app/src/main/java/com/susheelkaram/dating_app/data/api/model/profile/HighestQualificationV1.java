package com.susheelkaram.dating_app.data.api.model.profile;

import com.google.gson.annotations.SerializedName;

public class HighestQualificationV1{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("preference_only")
	private boolean preferenceOnly;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public boolean isPreferenceOnly(){
		return preferenceOnly;
	}
}