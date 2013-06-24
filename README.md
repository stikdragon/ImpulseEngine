Java port of Impulse Engine
Created by Philip Diffenderfer

The Impulse Engine is a small 2D physics library written in Java and has no third-party-library dependencies. 

This port is meant to be nearly an exact port, additional comments and functions have been added to the vector (Vec2) and matrix (Mat2) classes. The port does needlessly instantiate Vec2 and Mat2 objects (opposed to using a cache of these objects) but no negative effects have been measured.

This project was created and developed in Eclipse, the formatter is included.

In the test folder, there is a simple game engine that uses Java2D in order to show how the library works (taken from my blog - [http://gameprogblog.com](http://gh.magnos.org/?r=http://gameprogblog.com)). To run the demo download jimpulse-demo.jar and run it.

This has been tested with Java 1.6 and 1.7.

<i>Screenshot of TestImpulseEngine</i>

<a href="http://s1150.photobucket.com/user/ClickerMonkey/media/ImpulseEngine_zps05c537c9.png.html" target="_blank"><img src="http://i1150.photobucket.com/albums/o604/ClickerMonkey/ImpulseEngine_zps05c537c9.png" border="0" alt=" photo ImpulseEngine_zps05c537c9.png"/></a>
