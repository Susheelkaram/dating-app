package com.susheelkaram.dating_app.data.api.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileDataListItem{

	@SerializedName("preferences")
	private List<PreferencesItem> preferences;

	@SerializedName("question")
	private String question;

	@SerializedName("invitation_type")
	private String invitationType;

	public List<PreferencesItem> getPreferences(){
		return preferences;
	}

	public String getQuestion(){
		return question;
	}

	public String getInvitationType(){
		return invitationType;
	}
}