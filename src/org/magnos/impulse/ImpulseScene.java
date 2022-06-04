/*
    Copyright (c) 2013 Randy Gaul http://RandyGaul.net

    This software is provided 'as-is', without any express or implied
    warranty. In no event will the authors be held liable for any damages
    arising from the use of this software.

    Permission is granted to anyone to use this software for any purpose,
    including commercial applications, and to alter it and redistribute it
    freely, subject to the following restrictions:
      1. The origin of this software must not be misrepresented; you must not
         claim that you wrote the original software. If you use this software
         in a product, an acknowledgment in the product documentation would be
         appreciated but is not required.
      2. Altered source versions must be plainly marked as such, and must not be
         misrepresented as being the original software.
      3. This notice may not be removed or altered from any source distribution.
      
    Port to Java by Philip Diffenderfer http://magnos.org
*/

package org.magnos.impulse;

import java.util.ArrayList;
import java.util.List;

import org.magnos.impulse.SceneGrid.GridCell;

public class ImpulseScene<T extends Body> {

	public double		dt;
	public int			iterations;
	public List<T>		bodies		= new ArrayList<T>();
	public ManifoldList	contacts	= new ManifoldList();
	public Vec2			gravity		= new Vec2(0.0f, 50.0f);
	private Vec2		tmpv		= new Vec2();
	private SceneGrid	grid;
	private AABB		tbox		= new AABB();
	private int			sequence	= 0;

	public ImpulseScene(double dt, int iterations) {
		this(dt, new SceneOptions(iterations));
	}

	public ImpulseScene(double dt, SceneOptions opts) {
		this.dt = dt;
		this.iterations = opts.getIterations();
		if (opts.getGridBounds() != null)
			createPartitioningGrid(opts.getGridBounds(), opts.getGridDivisions());
	}

	/**
	 * if you set one of these then you are constraining your simulation to the
	 * bounds, anything that would fall outside of it will cease to take part in
	 * collisions. additionally you cannot have bodies that are bigger than the
	 * grid size
	 * 
	 * @param bounds
	 * @param divisions
	 */
	private void createPartitioningGrid(AABB bounds, int divisions) {
		grid = new SceneGrid(this);
		grid.init(bounds.getMin(), bounds.getMax(), divisions);
	}

	public void step() {
		// Generate new collision info
		contacts.clear();
		int[] counter = new int[1];
		if (grid != null) {
			//
			// if we have a grid then we can only worry
			// about things near each body 
			//
			for (T a : bodies) {
				GridCell c = grid.getCell(a.position);
				if (c == null)// it's wandered out of range 
					continue;
				grid.forEachNeighbour(c, -1, -1, 1, 1, b -> {
					if (b.sequence > a.sequence) {
						if (a.invMass == 0 && b.invMass == 0)
							return;
						counter[0]++;
						Manifold m = contacts.add();
						m.A = a;
						m.B = b;
						m.solve();

						if (m.contactCount == 0)
							contacts.removeLast();
					}
				});
			}

		} else {

			for (int i = 0; i < bodies.size(); ++i) {
				T a = bodies.get(i);

				for (int j = i + 1; j < bodies.size(); ++j) {
					T b = bodies.get(j);

					if (a.invMass == 0 && b.invMass == 0)
						continue;

					counter[0]++;
					Manifold m = contacts.add();
					m.A = a;
					m.B = b;
					m.solve();

					if (m.contactCount == 0)
						contacts.removeLast();
				}
			}
		}

		// Integrate forces
		bodies.forEach(b -> integrateForces(b, dt));

		// Initialize collision
		contacts.forEach(Manifold::initialize);

		// Solve collisions
		for (int j = 0; j < iterations; ++j)
			contacts.forEach(Manifold::applyImpulse);

		//
		// apply env friction
		//
		for (Body b : bodies) {
			tmpv.set(b.velocity);
			if (tmpv.lengthSq() > ImpulseMath.EPSILON) {
				//
				// work out magnitude of reverse impulse, this is a ratio of the 
				// speed raised to a power, with a minimum static friction
				//				
				double f = Math.max(100, Math.pow(b.velocity.lengthSq() * 0.01, 2.0));
				tmpv.normalize().muli(dt * -f);
				if (tmpv.lengthSq() >= b.velocity.lengthSq()) // don't apply such a big impulse that we go backwards
					b.velocity.set(0, 0);
				else
					b.velocity.addi(tmpv);
			}
		}

		// Integrate velocities
		bodies.forEach(b -> integrateVelocity(b, dt));

		// Correct positions
		contacts.forEach(Manifold::positionalCorrection);

		if (grid != null)
			bodies.forEach(this::updatePartitionPosition);

		// Clear all forces
		for (Body b : bodies) {
			b.force.set(0, 0);
			b.torque = 0;
		}
	}

	private void updatePartitionPosition(Body b) {
		GridCell cell = grid.getCell(b.position);
		if (b.lastCell != cell) {
			if (b.lastCell != null)
				b.lastCell.bodies.remove(b);
			if (cell != null)
				cell.bodies.add(b);
			b.lastCell = cell;
		}
	}

	public void positionUpdated(Body body) {
		updatePartitionPosition(body);
	}

	public void add(T b) {
		if (grid != null) {
			b.shape.calcDimensions(tbox);
			if (tbox.getW() > grid.getCellSize().x || tbox.getH() > grid.getCellSize().y)
				throw new IllegalStateException("There is a partitioning grid assigned, and the given shape would exceed its cell size");
			updatePartitionPosition(b);
		}
		b.sequence = ++sequence;
		bodies.add(b);
	}

	public void remove(T b) {
		if (grid != null) {
			GridCell c = grid.getCell(b.position);
			if (c != null)
				c.bodies.remove(b);
		}
		bodies.remove(b);

	}

	public void clear() {
		contacts.clear();
		bodies.clear();
		if (grid != null)
			grid.clear();
	}

	// Acceleration
	// F = mA
	// => A = F * 1/m

	// Explicit Euler
	// x += v * dt
	// v += (1/m * F) * dt

	// Semi-Implicit (Symplectic) Euler
	// v += (1/m * F) * dt
	// x += v * dt

	// see http://www.niksula.hut.fi/~hkankaan/Homepages/gravity.html
	public void integrateForces(T b, double dt) {
		//		if(b->im == 0.0f)
		//			return;
		//		b->velocity += (b->force * b->im + gravity) * (dt / 2.0f);
		//		b->angularVelocity += b->torque * b->iI * (dt / 2.0f);

		if (b.invMass == 0.0f) {
			return;
		}

		double dts = dt * 0.5f;

		b.velocity.addsi(b.force, b.invMass * dts);
		if (gravity != null)
			b.velocity.addsi(gravity, dts);
		b.angularVelocity += b.torque * b.invInertia * dts;
	}

	public void integrateVelocity(T b, double dt) {
		//		if(b->im == 0.0f)
		//			return;
		//		b->position += b->velocity * dt;
		//		b->orient += b->angularVelocity * dt;
		//		b->SetOrient( b->orient );
		//		IntegrateForces( b, dt );

		if (b.invMass == 0.0f) {
			return;
		}

		b.position.addsi(b.velocity, dt);
		b.orient += b.angularVelocity * dt;
		b.setOrient(b.orient);

		integrateForces(b, dt);
	}

	public final Vec2 getGravity() {
		return gravity;
	}

	public final void setGravity(Vec2 gravity) {
		this.gravity = gravity;
	}

	public final SceneGrid getGrid() {
		return grid;
	}

}
