package com.example.lovestudy.entity;

import org.json.JSONObject;

public class DocumentItemBean {

	public String Name;
	public String Description;
	
	public DocumentItemBean(JSONObject json) {
		if (json != null) {
			if (json.has("Name")) {
				Name = json.optString("Name");
			}
			if (json.has("Description")) {
				Description = json.optString("Description");
			}
		}
	}
}
