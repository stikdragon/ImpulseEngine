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

public class Vec2
{

	public double x, y;

	public Vec2()
	{
	}

	public Vec2( double x, double y )
	{
		set( x, y );
	}

	public Vec2( Vec2 v )
	{
		set( v );
	}

	public void set( double x, double y )
	{
		this.x = x;
		this.y = y;
	}

	public Vec2 set( Vec2 v )
	{
		x = v.x;
		y = v.y;
		return this;
	}

	/**
	 * Negates this vector and returns this.
	 */
	public Vec2 negi()
	{
		return neg( this );
	}

	/**
	 * Sets out to the negation of this vector and returns out.
	 */
	public Vec2 neg( Vec2 out )
	{
		out.x = -x;
		out.y = -y;
		return out;
	}

	/**
	 * Returns a new vector that is the negation to this vector.
	 */
	public Vec2 neg()
	{
		return neg( new Vec2() );
	}

	/**
	 * Multiplies this vector by s and returns this.
	 */
	public Vec2 muli( double s )
	{
		return mul( s, this );
	}

	/**
	 * Sets out to this vector multiplied by s and returns out.
	 */
	public Vec2 mul( double s, Vec2 out )
	{
		out.x = s * x;
		out.y = s * y;
		return out;
	}

	/**
	 * Returns a new vector that is a multiplication of this vector and s.
	 */
	public Vec2 mul( double s )
	{
		return mul( s, new Vec2() );
	}

	/**
	 * Divides this vector by s and returns this.
	 */
	public Vec2 divi( double s )
	{
		return div( s, this );
	}

	/**
	 * Sets out to the division of this vector and s and returns out.
	 */
	public Vec2 div( double s, Vec2 out )
	{
		out.x = x / s;
		out.y = y / s;
		return out;
	}

	/**
	 * Returns a new vector that is a division between this vector and s.
	 */
	public Vec2 div( double s )
	{
		return div( s, new Vec2() );
	}

	/**
	 * Adds s to this vector and returns this. 
	 */
	public Vec2 addi( double s )
	{
		return add( s, this );
	}

	/**
	 * Sets out to the sum of this vector and s and returns out.
	 */
	public Vec2 add( double s, Vec2 out )
	{
		out.x = x + s;
		out.y = y + s;
		return out;
	}

	/**
	 * Returns a new vector that is the sum between this vector and s.
	 */
	public Vec2 add( double s )
	{
		return add( s, new Vec2() );
	}

	/**
	 * Multiplies this vector by v and returns this.
	 */
	public Vec2 muli( Vec2 v )
	{
		return mul( v, this );
	}

	/**
	 * Sets out to the product of this vector and v and returns out.
	 */
	public Vec2 mul( Vec2 v, Vec2 out )
	{
		out.x = x * v.x;
		out.y = y * v.y;
		return out;
	}

	/**
	 * Returns a new vector that is the product of this vector and v.
	 */
	public Vec2 mul( Vec2 v )
	{
		return mul( v, new Vec2() );
	}

	/**
	 * Divides this vector by v and returns this.
	 */
	public Vec2 divi( Vec2 v )
	{
		return div( v, this );
	}

	/**
	 * Sets out to the division of this vector and v and returns out.
	 */
	public Vec2 div( Vec2 v, Vec2 out )
	{
		out.x = x / v.x;
		out.y = y / v.y;
		return out;
	}

	/**
	 * Returns a new vector that is the division of this vector by v.
	 */
	public Vec2 div( Vec2 v )
	{
		return div( v, new Vec2() );
	}

	/**
	 * Adds v to this vector and returns this.
	 */
	public Vec2 addi( Vec2 v )
	{
		return add( v, this );
	}

	/**
	 * Sets out to the addition of this vector and v and returns out.
	 */
	public Vec2 add( Vec2 v, Vec2 out )
	{
		out.x = x + v.x;
		out.y = y + v.y;
		return out;
	}

	/**
	 * Returns a new vector that is the addition of this vector and v.
	 */
	public Vec2 add( Vec2 v )
	{
		return add( v, new Vec2() );
	}

	/**
	 * Adds v * s to this vector and returns this.
	 */
	public Vec2 addsi( Vec2 v, double s )
	{
		return adds( v, s, this );
	}

	/**
	 * Sets out to the addition of this vector and v * s and returns out.
	 */
	public Vec2 adds( Vec2 v, double s, Vec2 out )
	{
		out.x = x + v.x * s;
		out.y = y + v.y * s;
		return out;
	}

	/**
	 * Returns a new vector that is the addition of this vector and v * s.
	 */
	public Vec2 adds( Vec2 v, double s )
	{
		return adds( v, s, new Vec2() );
	}

	/**
	 * Subtracts v from this vector and returns this.
	 */
	public Vec2 subi( Vec2 v )
	{
		return sub( v, this );
	}

	/**
	 * Sets out to the subtraction of v from this vector and returns out.
	 */
	public Vec2 sub( Vec2 v, Vec2 out )
	{
		out.x = x - v.x;
		out.y = y - v.y;
		return out;
	}

	/**
	 * Returns a new vector that is the subtraction of v from this vector.
	 */
	public Vec2 sub( Vec2 v )
	{
		return sub( v, new Vec2() );
	}

	/**
	 * Returns the squared length of this vector.
	 */
	public double lengthSq()
	{
		return x * x + y * y;
	}

	/**
	 * Returns the length of this vector.
	 */
	public double length()
	{
		return StrictMath.sqrt( x * x + y * y );
	}

	/**
	 * Rotates this vector by the given radians.
	 */
	public void rotate( double radians )
	{
		double c = StrictMath.cos( radians );
		double s = StrictMath.sin( radians );

		double xp = x * c - y * s;
		double yp = x * s + y * c;

		x = xp;
		y = yp;
	}

	/**
	 * Normalizes this vector, making it a unit vector. A unit vector has a length of 1.0.
	 */
	public Vec2 normalize()
	{
		double lenSq = lengthSq();

		if (lenSq > ImpulseMath.EPSILON_SQ)
		{
			double invLen = 1.0f / StrictMath.sqrt( lenSq );
			x *= invLen;
			y *= invLen;
		}
		return this;
	}

	/**
	 * Sets this vector to the minimum between a and b.
	 */
	public Vec2 mini( Vec2 a, Vec2 b )
	{
		return min( a, b, this );
	}

	/**
	 * Sets this vector to the maximum between a and b.
	 */
	public Vec2 maxi( Vec2 a, Vec2 b )
	{
		return max( a, b, this );
	}

	/**
	 * Returns the dot product between this vector and v.
	 */
	public double dot( Vec2 v )
	{
		return dot( this, v );
	}

	/**
	 * Returns the squared distance between this vector and v.
	 */
	public double distanceSq( Vec2 v )
	{
		return distanceSq( this, v );
	}

	/**
	 * Returns the distance between this vector and v.
	 */
	public double distance( Vec2 v )
	{
		return distance( this, v );
	}

	/**
	 * Sets this vector to the cross between v and a and returns this.
	 */
	public Vec2 cross( Vec2 v, double a )
	{
		return cross( v, a, this );
	}

	/**
	 * Sets this vector to the cross between a and v and returns this.
	 */
	public Vec2 cross( double a, Vec2 v )
	{
		return cross( a, v, this );
	}

	/**
	 * Returns the scalar cross between this vector and v. This is essentially
	 * the length of the cross product if this vector were 3d. This can also
	 * indicate which way v is facing relative to this vector.
	 */
	public double cross( Vec2 v )
	{
		return cross( this, v );
	}

	public static Vec2 min( Vec2 a, Vec2 b, Vec2 out )
	{
		out.x = StrictMath.min( a.x, b.x );
		out.y = StrictMath.min( a.y, b.y );
		return out;
	}

	public static Vec2 max( Vec2 a, Vec2 b, Vec2 out )
	{
		out.x = StrictMath.max( a.x, b.x );
		out.y = StrictMath.max( a.y, b.y );
		return out;
	}

	public static double dot( Vec2 a, Vec2 b )
	{
		return a.x * b.x + a.y * b.y;
	}

	public static double distanceSq( Vec2 a, Vec2 b )
	{
		double dx = a.x - b.x;
		double dy = a.y - b.y;

		return dx * dx + dy * dy;
	}

	public static double distance( Vec2 a, Vec2 b )
	{
		double dx = a.x - b.x;
		double dy = a.y - b.y;

		return StrictMath.sqrt( dx * dx + dy * dy );
	}

	public static Vec2 cross( Vec2 v, double a, Vec2 out )
	{
		out.x = v.y * a;
		out.y = v.x * -a;
		return out;
	}

	public static Vec2 cross( double a, Vec2 v, Vec2 out )
	{
		out.x = v.y * -a;
		out.y = v.x * a;
		return out;
	}

	public static double cross( Vec2 a, Vec2 b )
	{
		return a.x * b.y - a.y * b.x;
	}

	/**
	 * Returns an array of allocated Vec2 of the requested length.
	 */
	public static Vec2[] arrayOf( int length )
	{
		Vec2[] array = new Vec2[length];

		while (--length >= 0)
		{
			array[length] = new Vec2();
		}

		return array;
	}

	@Override
	public String toString() {
		return String.format("%.3f, %.3f", x, y);
	}

}
