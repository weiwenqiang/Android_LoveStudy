package com.example.lovestudy.module.RightSlideFinish;

public class SlidrConfig {

	private int colorPrimary = -1;
	private int colorSecondary = -1;
	private SlidrPosition position = SlidrPosition.LEFT;
	private float touchSize = -1f;
	private float sensitivity = 1f;
	private SlidrListener listener;

	private SlidrConfig() {
	}

	public int getPrimaryColor() {
		return colorPrimary;
	}

	public int getSecondaryColor() {
		return colorSecondary;
	}

	public SlidrPosition getPosition() {
		return position;
	}

	public float getTouchSize() {
		return touchSize;
	}

	public float getSensitivity() {
		return sensitivity;
	}

	public SlidrListener getListener() {
		return listener;
	}

	public boolean areStatusBarColorsValid() {
		return colorPrimary != -1 && colorSecondary != -1;
	}

	public static class Builder {

		private SlidrConfig config;

		public Builder() {
			config = new SlidrConfig();
		}

		public Builder primaryColor(int color) {
			config.colorPrimary = color;
			return this;
		}

		public Builder secondaryColor(int color) {
			config.colorSecondary = color;
			return this;
		}

		public Builder position(SlidrPosition position) {
			config.position = position;
			return this;
		}

		public Builder touchSize(float size) {
			config.touchSize = size;
			return this;
		}

		public Builder sensitivity(float sensitivity) {
			config.sensitivity = sensitivity;
			return this;
		}

		public Builder listener(SlidrListener listener) {
			config.listener = listener;
			return this;
		}

		public SlidrConfig build() {
			return config;
		}

	}

}
