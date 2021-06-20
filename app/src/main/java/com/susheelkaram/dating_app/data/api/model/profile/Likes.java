package com.susheelkaram.dating_app.data.api.model.profile;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Likes{

	@SerializedName("likes_received_count")
	private int likesReceivedCount;

	@SerializedName("profiles")
	private List<ProfilesItem> profiles;

	@SerializedName("can_see_profile")
	private boolean canSeeProfile;

	public int getLikesReceivedCount(){
		return likesReceivedCount;
	}

	public List<ProfilesItem> getProfiles(){
		return profiles;
	}

	public boolean isCanSeeProfile(){
		return canSeeProfile;
	}
}