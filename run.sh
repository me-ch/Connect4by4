#!/bin/sh

if find *.class 
then
	echo "files found"
else
	javac  *.java
fi	
if [ ! -e connect4x4.jar ]
then
	jar -cvfm connect4x4.jar MANIFEST.MF *.class
fi
java -jar connect4x4.jar