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

import org.magnos.impulse.SceneGrid.GridCell;

public class Body {

	public final Vec2				position	= new Vec2();
	public final Vec2				velocity	= new Vec2();
	public final Vec2				force		= new Vec2();
	public double					angularVelocity;
	public double					torque;
	public double					orient;
	public double					mass, invMass, inertia, invInertia;
	public double					staticFriction;
	public double					dynamicFriction;
	public double					restitution;
	public final Shape				shape;
	private final ImpulseScene<?>	scene;
	public GridCell					lastCell	= null;
	public int						sequence;

	public Body(ImpulseScene<?> scene, Shape shape, double x, double y) {
		this.shape = shape;
		this.scene = scene;

		position.set(x, y);
		velocity.set(0, 0);
		angularVelocity = 0;
		torque = 0;
		orient = ImpulseMath.random(-ImpulseMath.PI, ImpulseMath.PI);
		force.set(0, 0);
		staticFriction = 0.5f;
		dynamicFriction = 0.3f;
		restitution = 0.2f;

		shape.body = this;
		shape.initialize();
	}

	public void applyForce(Vec2 f) {
		// force += f;
		force.addi(f);
	}

	public void applyImpulse(Vec2 impulse, Vec2 contactVector) {
		// velocity += im * impulse;
		// angularVelocity += iI * Cross( contactVector, impulse );

		velocity.addsi(impulse, invMass);
		angularVelocity += invInertia * Vec2.cross(contactVector, impulse);
	}

	public void setStatic() {
		inertia = 0.0f;
		invInertia = 0.0f;
		mass = 0.0f;
		invMass = 0.0f;
	}

	public void setOrient(double radians) {
		orient = radians;
		shape.setOrient(radians);
	}

	public void setPosition(double x, double y) {
		position.set(x, y);
		scene.positionUpdated(this);
	}

}
