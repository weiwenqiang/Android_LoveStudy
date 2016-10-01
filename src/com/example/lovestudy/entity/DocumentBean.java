package com.example.lovestudy.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DocumentBean {
	public String Title;
	public List<DocumentItemBean> itemBeans = new ArrayList<DocumentItemBean>();

	public DocumentBean(JSONObject json) {
		if (json != null) {
			if (json.has("Title")) {
				Title = json.optString("Title");
			}
			if (json.has("Content")) {
				JSONArray Content = json.optJSONArray("Content");
				if (Content != null && Content.length() > 0) {
					for (int i = 0; i < Content.length(); i++) {
						try {
							JSONObject jsonObject = Content.getJSONObject(i);
							DocumentItemBean documentItemBean = new DocumentItemBean(jsonObject);
							itemBeans.add(documentItemBean);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}

			}
		}
	}
}
