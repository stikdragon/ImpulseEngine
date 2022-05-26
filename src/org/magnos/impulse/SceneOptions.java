package org.magnos.impulse;

public class SceneOptions {
	private int		iterations		= 10;
	private int		gridDivisions	= -1;
	private AABB	gridBounds		= null;

	public SceneOptions(int iterations) {
		super();
		this.iterations = iterations;
	}

	public final int getIterations() {
		return iterations;
	}

	public final void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public final int getGridDivisions() {
		return gridDivisions;
	}

	public final void setGridDivisions(int gridDivisions) {
		this.gridDivisions = gridDivisions;
	}

	public final AABB getGridBounds() {
		return gridBounds;
	}

	public final void setGridBounds(AABB gridBounds) {
		this.gridBounds = gridBounds;
	}
}
