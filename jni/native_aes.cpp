#include "stdafx.h"
#include "sf_jni_NativeAesCtr.h"
#include "sf_jni_HalloJni.h"

#include "jni_common.h"
#include "aes.h"
#include "filters.h"
#include "secblock.h"
#include "hex.h"
#include "modes.h"
#include "osrng.h"

using namespace std;
using namespace CryptoPP;

byte* voidByteArray(const int length) {
	byte* rtn = new byte[length];
	memset(rtn, 0x00, length);
	return rtn;
}

byte* hash0(char* txt) {
	size_t messageLen = std::strlen(txt);
	byte sha_out[32];
	//memset(sha_out, 0x00, 16);
	SHA256 sha;
	sha.CalculateDigest(sha_out, (byte*)txt, messageLen);
	return sha_out;
}

byte* encode(byte* hash_key, byte* input, const int msg_len) {
	byte* out = new byte[msg_len];
	SecByteBlock key(hash_key, 32);
	SecByteBlock iv(voidByteArray(16), 16);
	size_t messageLen = msg_len;
	CTR_Mode<AES>::Encryption cfbEncryption(key, key.size(), iv);
	cfbEncryption.ProcessData(out, input, messageLen);
	return out;
}

byte* decode(byte* hash_key, byte* input, const int msg_len) {
	byte* out = new byte[msg_len];
	SecByteBlock key(hash_key, 32);
	SecByteBlock iv(voidByteArray(16), 16);
	size_t messageLen = msg_len;
	CTR_Mode<AES>::Decryption cfbDecryption(key, key.size(), iv);
	cfbDecryption.ProcessData(out, input, messageLen);
	return out;
}

byte* HexDecode(const string str) {
	string decoded;
	auto cstr = (byte*)str.c_str();

	HexDecoder decoder;
	decoder.Put((byte*)str.data(), str.size());
	decoder.MessageEnd();

	word64 size = decoder.MaxRetrievable();
	if (size&& size <= SIZE_MAX)
	{
		decoded.resize(size);
		decoder.Get((byte*)&decoded[0], decoded.size());
	}
	return (byte*)decoded.data();
}

string HexEncode(const byte* barray) {
	string encoded;

	HexEncoder encoder;
	encoder.Put(barray, strlen((char*)barray));
	encoder.MessageEnd();

	word64 size = encoder.MaxRetrievable();
	if (size)
	{
		encoded.resize(size);
		encoder.Get((byte*)&encoded[0], encoded.size());
	}
	return encoded;
}



/*
 * Class:     sf_jni_NativeAes
 * Method:    decrypt
 * Signature: ([B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_sf_jni_NativeAesCtr_decrypt
(JNIEnv * jni, jobject var0, jbyteArray var1) {
	auto callerclass = jni->GetObjectClass(var0);
	auto mid = jni->GetMethodID(callerclass, "getKey", "()Ljava/lang/String;");
	auto js_key = jni->CallObjectMethod(var0, mid);

	//read jarray
	auto skey = read_jstring(jni, (jstring)js_key);
	auto input = copy_jarray(jni, var1);

	//encrypt
	auto out = skey.length == 32 ? decode((byte*)skey.ptr, (byte*)input.ptr, input.length) : decode(hash0(skey.ptr), (byte*)input.ptr, input.length);


	//write jarray
	jbyteArray jbytes = jni->NewByteArray(input.length);
	jni->SetByteArrayRegion(jbytes, 0, input.length, (jbyte*)out);
	return jbytes;
}

/*
 * Class:     sf_jni_NativeAes
 * Method:    encrypt
 * Signature: ([B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_sf_jni_NativeAesCtr_encrypt
(JNIEnv * jni, jobject var0, jbyteArray var1) {
	auto callerclass = jni->GetObjectClass(var0);
	auto mid = jni->GetMethodID(callerclass, "getKey", "()Ljava/lang/String;");
	auto js_key = jni->CallObjectMethod(var0, mid);

	//read jarray
	auto skey = read_jstring(jni, (jstring)js_key);
	auto input = copy_jarray(jni, var1);

	//encrypt
	auto out = skey.length == 32 ? encode((byte*)skey.ptr, (byte*)input.ptr, input.length) : encode(hash0(skey.ptr), (byte*)input.ptr, input.length);

	//write jarray
	jbyteArray jbytes = jni->NewByteArray(input.length);
	jni->SetByteArrayRegion(jbytes, 0, input.length, (jbyte*)out);
	return jbytes;
}

/*
 * Class:     sf_jni_HalloJni
 * Method:    foo
 * Signature: ([BLjava/lang/String;ILjava/lang/Object;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_sf_jni_HalloJni_foo(JNIEnv* env, jclass var0, jbyteArray var1, jstring var2, jint var3, jobject var4)
{
	return NULL;
}