package org.magnos.impulse;

public class AABB {
	public double	x0;
	public double	y0;
	public double	x1;
	public double	y1;

	public AABB() {
		super();
	}
	
	public AABB(double x0, double y0, double x1, double y1) {
		super();
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}

	public final boolean in(double x, double y) {
		return x >= x0 && y >= y0 && x <= x1 && y <= y1;
	}

	public Vec2 getMax() {
		return new Vec2(x1, y1);
	}

	public Vec2 getMin() {
		return new Vec2(x0, y0);
	}

	public double getW() {
		return x1 - x0;
	}

	public double getH() {
		return y1 - y0;
	}
}
