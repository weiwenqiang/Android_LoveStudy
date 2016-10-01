package com.example.lovestudy.entity;

public class BaseTextClassBean {

	public String hint;

	public Class<?> activityName;
	
	public BaseTextClassBean() {
		super();
	}

	public BaseTextClassBean(String hint, Class<?> activityName) {
		super();
		this.hint = hint;
		this.activityName = activityName;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Class<?> getActivityName() {
		return activityName;
	}

	public void setActivityName(Class<?> activityName) {
		this.activityName = activityName;
	}

}
