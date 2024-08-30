   CC=gcc
   CFLAGS=-I/usr/local/jdk-1.8.0_301/include -I/usr/local/jdk-1.8.0_301/include/linux
   LIBS=-L/usr/local/jdk-1.8.0_301/jre/lib/amd64/server -ljvm

   all: libSystem.o $(CC) $(CFLAGS) -shared -o libSystem.so libSystem.o $(LIBS)

   NativeMethods.o: System.c System.h $(CC) $(CFLAGS) -c System.c

   clean: rm -f *.o libSystem.so
   