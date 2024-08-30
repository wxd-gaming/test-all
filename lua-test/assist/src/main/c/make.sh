rm -vrf libTimeLib.so
rm -vrf TimeLib.class
rm -vrf TimeLib.h

/usr/local/jdk-1.8.0_301/bin/javac Timelib.java
/usr/local/jdk-1.8.0_301/bin/javah -jni Timelib


gcc -fPIC --shared Timelib.c -o libTimeLib.so -I /usr/local/jdk-1.8.0_301/include/linux/ -I /usr/local/jdk-1.8.0_301/include/

/usr/local/jdk-1.8.0_301/bin/java -Djava.library.path=. Timelib