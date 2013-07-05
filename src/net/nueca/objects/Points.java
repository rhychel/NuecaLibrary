package net.nueca.objects;

public class Points {
	
	private int _x, _y;

	public Points() { }
	
	public Points(int[] points) {
		setX(points[0]);
		setY(points[1]);
	}
	
	public Points(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public int getX() {
		return _x;
	}

	public void setX(int _x) {
		this._x = _x;
	}

	public int getY() {
		return _y;
	}

	public void setY(int _y) {
		this._y = _y;
	}
	
	@Override
	public String toString() {
		return "Points: ("+getX()+", "+getY()+")";
	}
	
}
