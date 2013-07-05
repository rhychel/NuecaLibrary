package net.nueca.custom.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerTitleStrip;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomPagerTitleStrip extends PagerTitleStrip {
	public static Typeface typeFace;
	
	public CustomPagerTitleStrip(Context context) {
		super(context);
	}
	
	public CustomPagerTitleStrip(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        for (int i=0; i<this.getChildCount(); i++) {
            if (this.getChildAt(i) instanceof TextView) {
                ((TextView)this.getChildAt(i)).setTypeface(typeFace);
            }
        }
    }
	
}
