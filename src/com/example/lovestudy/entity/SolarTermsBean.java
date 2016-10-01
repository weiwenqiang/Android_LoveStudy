package com.example.lovestudy.entity;

public class SolarTermsBean {

	public String title;
	
	public int resouceId;
	
	public String introduction;
	
	public SolarTermsBean() {
		super();
	}

	public SolarTermsBean(String title, int resouceId, String introduction) {
		super();
		this.title = title;
		this.resouceId = resouceId;
		this.introduction = introduction;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getResouceId() {
		return resouceId;
	}

	public void setResouceId(int resouceId) {
		this.resouceId = resouceId;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
}
