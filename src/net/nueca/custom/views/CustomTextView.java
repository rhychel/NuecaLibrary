package net.nueca.custom.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {
	
	public static Typeface typeFace;
	
	public CustomTextView(Context context) {
		super(context);
	}
	
	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public void setTypeface(Typeface tf) {
		super.setTypeface(typeFace);
	}
	
}
