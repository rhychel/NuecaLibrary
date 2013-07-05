package net.nueca.tools;

import net.nueca.objects.Points;
import android.view.View;

public class ViewLocations {
	
	private Points points;
	
	public ViewLocations() { }
	
	public ViewLocations(View view) {
		points = new Points(getRelativeLeft(view), getRelativeTop(view));
	}
	
	private int getRelativeLeft(View myView) {
	    if ((View)myView.getParent() == myView.getRootView())
	        return myView.getLeft();
	    else
	        return myView.getLeft() + getRelativeLeft((View)myView.getParent());
	}

	private int getRelativeTop(View myView) {
	    if ((View)myView.getParent() == myView.getRootView())
	        return myView.getTop();
	    else
	        return myView.getTop() + getRelativeTop((View)myView.getParent());
	}
	
	public Points getPoints(View view) {
		points = new Points(getRelativeLeft(view), getRelativeTop(view));
		return points;
	}
	
	public Points getPoints() {
		return points;
	}
	
}
