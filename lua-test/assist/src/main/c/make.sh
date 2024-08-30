/usr/local/jdk-1.8.0_301/bin/javac System.java
/usr/local/jdk-1.8.0_301/bin/javah System
gcc -fPIC --shared System.c -o libSystem.so -I /usr/local/jdk-1.8.0_301/include/linux/ -I /usr/local/jdk-1.8.0_301/include/

/usr/local/jdk-1.8.0_301/bin/java -Djava.library.path=. System