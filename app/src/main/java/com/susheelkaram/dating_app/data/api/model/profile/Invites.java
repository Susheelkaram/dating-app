package com.susheelkaram.dating_app.data.api.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Invites{

	@SerializedName("pending_invitations_count")
	private int pendingInvitationsCount;

	@SerializedName("profiles")
	private List<ProfilesItem> profiles;

	@SerializedName("totalPages")
	private int totalPages;

	public int getPendingInvitationsCount(){
		return pendingInvitationsCount;
	}

	public List<ProfilesItem> getProfiles(){
		return profiles;
	}

	public int getTotalPages(){
		return totalPages;
	}
}