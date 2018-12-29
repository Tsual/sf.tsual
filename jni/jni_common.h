#pragma once

#include "stdafx.h"
#include <jni.h>

struct char_array
{
	int length;
	char* ptr;
};

char_array copy_jarray(JNIEnv* env, jbyteArray ja) {
	char_array rtn;
	rtn.length = env->GetArrayLength(ja);
	rtn.ptr = new char[rtn.length];
	auto jbi = env->GetByteArrayElements(ja, JNI_FALSE);
	for (int i = 0; i < rtn.length; i++) {
		rtn.ptr[i] = jbi[i];
	}
	return rtn;
}

char_array read_jstring(JNIEnv* env, jstring jstr)
{
	jmethodID mid = env->GetMethodID(env->GetObjectClass(jstr), "getBytes", "()[B");
	jbyteArray barr = (jbyteArray)env->CallObjectMethod(jstr, mid);
	return copy_jarray(env, barr);
}

jstring write_jstring(JNIEnv* env, char_array ca)
{
	jclass strClass = env->FindClass("Ljava/lang/String;");
	jmethodID ctorID = env->GetMethodID(strClass, "<init>", "([BLjava/lang/String;)V");
	jbyteArray bytes = env->NewByteArray(ca.length);
	env->SetByteArrayRegion(bytes, 0, ca.length, (jbyte*)ca.ptr);
	jstring encoding = env->NewStringUTF("utf-8");
	return (jstring)env->NewObject(strClass, ctorID, bytes, encoding);
}
