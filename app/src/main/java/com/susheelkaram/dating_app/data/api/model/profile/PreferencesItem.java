package com.susheelkaram.dating_app.data.api.model.profile;

import com.google.gson.annotations.SerializedName;

public class PreferencesItem{

	@SerializedName("answer")
	private String answer;

	@SerializedName("second_choice")
	private String secondChoice;

	@SerializedName("answer_id")
	private int answerId;

	@SerializedName("first_choice")
	private String firstChoice;

	public String getAnswer(){
		return answer;
	}

	public String getSecondChoice(){
		return secondChoice;
	}

	public int getAnswerId(){
		return answerId;
	}

	public String getFirstChoice(){
		return firstChoice;
	}
}