package net.nueca.objects;

import android.view.View;

public class ViewSize {
	
	private int height, width;

	public ViewSize() { }
	
	public ViewSize(View view){
		setWidth(view.getMeasuredWidth());
		setHeight(view.getMeasuredHeight());
	}
	
	public ViewSize(int height, int width) {
		setWidth(width);
		setHeight(height);
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
