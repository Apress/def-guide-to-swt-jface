Source Code for The Definitive Guide to SWT and JFace
-----------------------------------------------------
Unzip the contents of this file to a directory. It should contain directories
for each chapter (e.g. Chapter03, Chapter04, etc.). Each chapter's directory
contains source code for that chapter in the book--note that Chapters 1 and 2
have no source code.

Each chapter's directory contains an Ant build file called build.xml for your
convenience. See http://ant.apache.org for more information on Ant. To use it,
first edit the build.xml with the directory information specific to your system
(build.xml contains comments for more information on how to do that), and then
run ant from within that directory, specifying the main class of the program
you want to run. For example, to run the class examples.ch4.StackLayoutTest,
change to the Chapter04 directory and type:

	ant -Dmain.class=examples.ch4.StackLayoutTest
	
If you just want to compile all the code for a chapter, change to that
chapter's directory and type:

	ant compile
	
Of course, you don't have to use Ant. You can compile the classes yourself from
the command line using the Java compiler (javac) and run them using the Java
interpreter (java or javaw). You can import the code into your favorite IDE
(Eclipse, NetBeans, JBuilder, IDEA, et al.) and compile and run from within
your IDE.

Send questions, comments, bug reports, etc. to the authors:

Rob Warner (rwarner@interspatial.com)
Robert Harris (rbrt_harris@yahoo.com)

We look forward to hearing from you!

