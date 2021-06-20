package com.susheelkaram.dating_app.data.api.model.profile;

import com.google.gson.annotations.SerializedName;

public class GeneralInformation{

	@SerializedName("ref_id")
	private String refId;

	@SerializedName("politics")
	private Object politics;

	@SerializedName("drinking")
	private String drinking;

	@SerializedName("gender")
	private String gender;

	@SerializedName("date_of_birth")
	private String dateOfBirth;

	@SerializedName("smoking_v1")
	private SmokingV1 smokingV1;

	@SerializedName("kid")
	private Object kid;

	@SerializedName("settle")
	private Object settle;

	@SerializedName("faith")
	private Faith faith;

	@SerializedName("cast")
	private Object cast;

	@SerializedName("drinking_v1")
	private DrinkingV1 drinkingV1;

	@SerializedName("marital_status")
	private String maritalStatus;

	@SerializedName("sun_sign")
	private String sunSign;

	@SerializedName("marital_status_v1")
	private MaritalStatusV1 maritalStatusV1;

	@SerializedName("sun_sign_v1")
	private SunSignV1 sunSignV1;

	@SerializedName("smoking")
	private String smoking;

	@SerializedName("date_of_birth_v1")
	private String dateOfBirthV1;

	@SerializedName("location")
	private Location location;

	@SerializedName("diet")
	private Object diet;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("pet")
	private Object pet;

	@SerializedName("age")
	private int age;

	@SerializedName("mother_tongue")
	private MotherTongue motherTongue;

	@SerializedName("height")
	private int height;

	public String getRefId(){
		return refId;
	}

	public Object getPolitics(){
		return politics;
	}

	public String getDrinking(){
		return drinking;
	}

	public String getGender(){
		return gender;
	}

	public String getDateOfBirth(){
		return dateOfBirth;
	}

	public SmokingV1 getSmokingV1(){
		return smokingV1;
	}

	public Object getKid(){
		return kid;
	}

	public Object getSettle(){
		return settle;
	}

	public Faith getFaith(){
		return faith;
	}

	public Object getCast(){
		return cast;
	}

	public DrinkingV1 getDrinkingV1(){
		return drinkingV1;
	}

	public String getMaritalStatus(){
		return maritalStatus;
	}

	public String getSunSign(){
		return sunSign;
	}

	public MaritalStatusV1 getMaritalStatusV1(){
		return maritalStatusV1;
	}

	public SunSignV1 getSunSignV1(){
		return sunSignV1;
	}

	public String getSmoking(){
		return smoking;
	}

	public String getDateOfBirthV1(){
		return dateOfBirthV1;
	}

	public Location getLocation(){
		return location;
	}

	public Object getDiet(){
		return diet;
	}

	public String getFirstName(){
		return firstName;
	}

	public Object getPet(){
		return pet;
	}

	public int getAge(){
		return age;
	}

	public MotherTongue getMotherTongue(){
		return motherTongue;
	}

	public int getHeight(){
		return height;
	}
}