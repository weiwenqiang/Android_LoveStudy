package com.example.lovestudy.module.RightSlideFinish;

public interface SlidrListener {
	
	public void onSlideStateChanged(int state);

	public void onSlideChange(int pos);

	public void onSlideOpened();

	public void onSlideClosed();
	
}
