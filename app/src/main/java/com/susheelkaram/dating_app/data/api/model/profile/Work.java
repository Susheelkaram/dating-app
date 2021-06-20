package com.susheelkaram.dating_app.data.api.model.profile;

import com.google.gson.annotations.SerializedName;

public class Work{

	@SerializedName("experience_v1")
	private ExperienceV1 experienceV1;

	@SerializedName("industry_v1")
	private IndustryV1 industryV1;

	@SerializedName("highest_qualification_v1")
	private HighestQualificationV1 highestQualificationV1;

	@SerializedName("industry")
	private String industry;

	@SerializedName("monthly_income_v1")
	private Object monthlyIncomeV1;

	@SerializedName("experience")
	private String experience;

	@SerializedName("highest_qualification")
	private String highestQualification;

	@SerializedName("field_of_study")
	private String fieldOfStudy;

	@SerializedName("field_of_study_v1")
	private FieldOfStudyV1 fieldOfStudyV1;

	public ExperienceV1 getExperienceV1(){
		return experienceV1;
	}

	public IndustryV1 getIndustryV1(){
		return industryV1;
	}

	public HighestQualificationV1 getHighestQualificationV1(){
		return highestQualificationV1;
	}

	public String getIndustry(){
		return industry;
	}

	public Object getMonthlyIncomeV1(){
		return monthlyIncomeV1;
	}

	public String getExperience(){
		return experience;
	}

	public String getHighestQualification(){
		return highestQualification;
	}

	public String getFieldOfStudy(){
		return fieldOfStudy;
	}

	public FieldOfStudyV1 getFieldOfStudyV1(){
		return fieldOfStudyV1;
	}
}