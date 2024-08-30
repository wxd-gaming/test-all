#include <jni.h>
#include <stdio.h>
#include <time.h>
#include "System.h"

unsigned long _offset;

// 获取当前时间（毫秒）
JNIEXPORT jlong JNICALL Java_System_getCurrentTimeMillis(JNIEnv* env, jobject thisObj) {
	struct timespec ts;
	clock_gettime(CLOCK_REALTIME, &ts);
	unsigned long milliseconds = (ts.tv_sec * 1000) + (ts.tv_nsec / 1000000);
	return (jlong)(milliseconds + _offset);
}

// Implementation of native method sayHello() of HelloJNI class
JNIEXPORT void JNICALL Java_System_setOffset(JNIEnv* env, jobject thisObj, jlong offset) {
	_offset = (long)offset;
	return;
}