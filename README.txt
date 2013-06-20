Java port of Impulse Engine
Created by Philip Diffenderfer

Java port of Impulse Engine is a small 2D physics written in Java and has no third-party-library dependencies. This project was created and developed in Eclipse, the formatter is included.

In the test folder, there is a simple game engine that uses Java2D in order to show how the library works.

This port is meant to be nearly an exact port, additional comments and functions have been added to the vector (Vec2) and matrix (Mat2) classes. The port does needlessly create Vec2 and Mat2 objects (opposed to using a cache of these objects) but no negative effects have been measured.

This has been tested with Java 1.6 and 1.7.

Original README:

<i>
Impulse Engine
Created by Randy Gaul

Impulse Engine is a small 2D physics engine written in C++. The engine is intended to be used in an educational manner by other looking to learn the inner workings of physics engines.

Should build in Windows with Visual Studio 2008-2012. Should build on Linux and OSX platforms with the use of C++11 std::chrono. Thanks to whackashoe for the cross-platform port.

If you have any questions feel free to contact Randy at: r dot gaul at digipen dot edu.

http://randygaul.net
</i>
