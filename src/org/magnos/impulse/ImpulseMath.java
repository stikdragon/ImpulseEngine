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

public class ImpulseMath
{

	public static final double PI = (float)StrictMath.PI;
	public static final double EPSILON = 0.0001f;
	public static final double EPSILON_SQ = EPSILON * EPSILON;
	public static final double BIAS_RELATIVE = 0.95f;
	public static final double BIAS_ABSOLUTE = 0.01f;
	public static final double DT = 1.0f / 60.0f;
	public static final Vec2 GRAVITY = new Vec2( 0.0f, 50.0f );
	public static final double RESTING = GRAVITY.mul( DT ).lengthSq() + EPSILON;
	public static final double PENETRATION_ALLOWANCE = 0.05f;
	public static final double PENETRATION_CORRETION = 0.4f;

	public static boolean equal( double a, double b )
	{
		return StrictMath.abs( a - b ) <= EPSILON;
	}

	public static double clamp( double min, double max, double a )
	{
		return (a < min ? min : (a > max ? max : a));
	}

	public static int round( double a )
	{
		return (int)(a + 0.5f);
	}

	public static double random( double min, double max )
	{
		return (max - min) * Math.random() + min;
	}

	public static int random( int min, int max )
	{
		return (int)((max - min + 1) * Math.random() + min);
	}

	public static boolean gt( double a, double b )
	{
		return a >= b * BIAS_RELATIVE + a * BIAS_ABSOLUTE;
	}

}
