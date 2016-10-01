package com.example.lovestudy.entity;

import org.json.JSONObject;

public class MoreBean {
	public String Name;
	public int IsNewGroup;
	public String Key;

	public MoreBean(JSONObject json) {
		if (json != null) {
			if (json.has("Name")) {
				Name = json.optString("Name");
			}
			if (json.has("IsNewGroup")) {
				IsNewGroup = json.optInt("IsNewGroup");
			}
			if (json.has("Key")) {
				Key = json.optString("Key");
			}
		}
	}

}
