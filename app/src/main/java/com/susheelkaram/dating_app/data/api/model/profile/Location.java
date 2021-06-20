package com.susheelkaram.dating_app.data.api.model.profile;

import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("summary")
	private String summary;

	@SerializedName("full")
	private String full;

	public String getSummary(){
		return summary;
	}

	public String getFull(){
		return full;
	}
}