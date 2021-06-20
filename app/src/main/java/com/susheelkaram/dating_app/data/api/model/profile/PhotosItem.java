package com.susheelkaram.dating_app.data.api.model.profile;

import com.google.gson.annotations.SerializedName;

public class PhotosItem{

	@SerializedName("photo_id")
	private int photoId;

	@SerializedName("photo")
	private String photo;

	@SerializedName("selected")
	private boolean selected;

	@SerializedName("status")
	private String status;

	public int getPhotoId(){
		return photoId;
	}

	public String getPhoto(){
		return photo;
	}

	public boolean isSelected(){
		return selected;
	}

	public String getStatus(){
		return status;
	}
}