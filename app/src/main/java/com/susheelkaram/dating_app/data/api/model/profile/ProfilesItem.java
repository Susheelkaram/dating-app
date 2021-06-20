package com.susheelkaram.dating_app.data.api.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfilesItem{

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("general_information")
	private GeneralInformation generalInformation;

	@SerializedName("first_name")
	private String firstName;

	private List<PhotosItem> photos;

	public List<PhotosItem> getPhotos() {
		return photos;
	}

	public String getAvatar(){
		return avatar;
	}

	public String getFirstName() {
		return firstName;
	}

	public GeneralInformation getGeneralInformation(){
		return generalInformation;
	}
}