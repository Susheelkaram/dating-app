package com.susheelkaram.dating_app.data.api.model;

import com.google.gson.annotations.SerializedName;
import com.susheelkaram.dating_app.data.api.model.profile.Invites;
import com.susheelkaram.dating_app.data.api.model.profile.Likes;

public class ProfileResponse{

	@SerializedName("invites")
	private Invites invites;

	@SerializedName("likes")
	private Likes likes;

	public Invites getInvites(){
		return invites;
	}

	public Likes getLikes(){
		return likes;
	}
}