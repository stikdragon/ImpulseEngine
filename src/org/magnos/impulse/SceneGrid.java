package org.magnos.impulse;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class SceneGrid {
	public static class GridCell {
		int			cx;
		int			cy;
		Set<Body>	bodies	= new HashSet<>();

		GridCell(int cx, int cy) {
			super();
			this.cx = cx;
			this.cy = cy;
		}

	}

	private GridCell[]	cells;
	private int			divisions;
	private Vec2		min;
	private Vec2		max;
	private Vec2		cellSize;

	public SceneGrid(ImpulseScene<?> owner) {
	}

	public void init(Vec2 min, Vec2 max, int divisions) {
		this.divisions = divisions;
		this.min = min;
		this.max = max;
		this.cellSize = new Vec2(max.sub(min)).mul(1.0 / divisions);
		clear();
	}

	/**
	 * returns <code>null</code> if out of bounds
	 * 
	 * @param pos
	 * @return
	 */
	public GridCell getCell(Vec2 pos) {
		if (pos.x < min.x || pos.y < min.y)
			return null;
		if (pos.x > max.x || pos.y > max.y)
			return null;
		int x = (int) (pos.x / cellSize.x);
		int y = (int) (pos.y / cellSize.y);
		return cells[y * divisions + x];
	}

	public final int getDivisions() {
		return divisions;
	}

	public final Vec2 getCellSize() {
		return cellSize;
	}

	public void clear() {
		cells = new GridCell[divisions * divisions];
		for (int i = 0; i < cells.length; ++i)
			cells[i] = new GridCell(i % divisions, i / divisions);
	}

	private int clamp(int n) {
		if (n < 0)
			return 0;
		if (n >= divisions)
			n = divisions - 1;
		return n;
	}

	public void forEachNeighbour(GridCell cell, int x0, int y0, int x1, int y1, Consumer<Body> callback) {
		int _x0 = clamp(cell.cx + x0);
		int _y0 = clamp(cell.cy + y0);
		int _x1 = clamp(cell.cx + x1);
		int _y1 = clamp(cell.cy + y1);

		for (int y = _y0; y <= _y1; ++y) {
			for (int x = _x0; x <= _x1; ++x) {
				for (Body b : cells[y * divisions + x].bodies)
					callback.accept(b);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < divisions; ++y) {
			for (int x = 0; x < divisions; ++x) {
				String s = "     " + Integer.toString(cells[y * divisions + x].bodies.size());
				sb.append(s.substring(s.length() - 3));
				sb.append("  ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
