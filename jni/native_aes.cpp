#include "stdafx.h"
#include "sf_jni_NativeAes.h"
#include "sf_jni_HalloJni.h"

#include "jni_common.h"
#include "cryptopp/aes.h"
#include "cryptopp/filters.h"
#include "cryptopp/modes.h"

using namespace std;





/*
 * Class:     sf_jni_NativeAes
 * Method:    decrypt
 * Signature: ([B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_sf_jni_NativeAes_decrypt
(JNIEnv * jni, jobject var0, jbyteArray var1) {
	//init key iv
	unsigned char key[CryptoPP::AES::DEFAULT_KEYLENGTH], iv[CryptoPP::AES::BLOCKSIZE];
	memset(key, 0x00, CryptoPP::AES::DEFAULT_KEYLENGTH);
	memset(iv, 0x00, CryptoPP::AES::BLOCKSIZE);

	//read jarray
	auto skey = read_jstring(jni, (jstring)(jni->GetObjectField(var0, jni->FromReflectedField(var0))));
	auto buf = jni->GetByteArrayElements(var1, JNI_FALSE);

	//decrypt
	string rtn_buffer;
	CryptoPP::AES::Decryption aesDecryption(key, CryptoPP::AES::DEFAULT_KEYLENGTH);
	CryptoPP::CBC_Mode_ExternalCipher::Decryption cbcDeceyption(aesDecryption, iv);
	CryptoPP::StreamTransformationFilter stfDecryptor(cbcDeceyption, new CryptoPP::StringSink(rtn_buffer));
	stfDecryptor.Put((unsigned char*)buf, strlen((char*)buf));
	stfDecryptor.MessageEnd();

	//write jarray
	jbyteArray jbytes = jni->NewByteArray(strlen(rtn_buffer.c_str()));
	jni->SetByteArrayRegion(jbytes, 0, strlen(rtn_buffer.c_str()), (jbyte*)rtn_buffer.c_str());

	//release jarray
	jni->ReleaseByteArrayElements(var1, buf, 0);

	return jbytes;
}

/*
 * Class:     sf_jni_NativeAes
 * Method:    encrypt
 * Signature: ([B)[B
 */
JNIEXPORT jbyteArray JNICALL Java_sf_jni_NativeAes_encrypt
(JNIEnv * jni, jobject var0, jbyteArray var1) {
	//init key iv
	unsigned char key[CryptoPP::AES::DEFAULT_KEYLENGTH], iv[CryptoPP::AES::BLOCKSIZE];
	memset(key, 0x00, CryptoPP::AES::DEFAULT_KEYLENGTH);
	memset(iv, 0x00, CryptoPP::AES::BLOCKSIZE);

	//read jarray
	auto skey = read_jstring(jni, (jstring)(jni->GetObjectField(var0, jni->FromReflectedField(var0))));
	auto buf = jni->GetByteArrayElements(var1, JNI_FALSE);

	//encrypt
	string rtn_buffer;
	CryptoPP::AES::Encryption aesEncryption(key, CryptoPP::AES::DEFAULT_KEYLENGTH);
	CryptoPP::CBC_Mode_ExternalCipher::Encryption cbcEncryption(aesEncryption, iv);
	CryptoPP::StreamTransformationFilter stfEncryptor(cbcEncryption, new CryptoPP::StringSink(rtn_buffer));
	stfEncryptor.Put((unsigned char*)buf, strlen((char*)buf));
	stfEncryptor.MessageEnd();

	//write jarray
	jbyteArray jbytes = jni->NewByteArray(strlen(rtn_buffer.c_str()));
	jni->SetByteArrayRegion(jbytes, 0, strlen(rtn_buffer.c_str()), (jbyte*)rtn_buffer.c_str());

	//release jarray
	jni->ReleaseByteArrayElements(var1, buf, 0);

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











//// ConsoleApplication2.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
////
//
//#include "pch.h"
//#include <iostream>
//
//#include "aes.h"
//#include "filters.h"
//#include "secblock.h"
//#include "hex.h"
//#include "modes.h"
//
//using namespace std;
//using namespace CryptoPP;
//
//byte* AES_CTR_Encrypt(const char *, const byte*);
//byte* AES_CTR_Decrypt(const char *, const byte*);
//string HexEncode(const byte*);
//byte* HexDecode(const string);
//
//int main()
//{
//	std::cout << "Hello World!\n";
//	auto fa = AES_CTR_Encrypt(NULL, NULL);
//	std::cout << fa << "\n" <<
//		HexEncode(fa) << "\n";
//}
//
//
//byte* voidByteArray(const int length) {
//	byte* rtn = new byte[length];
//	memset(rtn, 0x00, length);
//	return rtn;
//}
//
//SecByteBlock HexDecodeString(const char *hex)
//{
//	StringSource ss(hex, true, new HexDecoder);
//	SecByteBlock result((size_t)ss.MaxRetrievable());
//	ss.Get(result, result.size());
//	return result;
//}
//
//byte* HexDecode(const string str) {
//	return NULL;
//}
//
//string HexEncode(const byte* barray) {
//	string encoded;
//
//	HexEncoder encoder;
//	encoder.Put(barray, strlen((char*)barray));
//	encoder.MessageEnd();
//
//	word64 size = encoder.MaxRetrievable();
//	if (size)
//	{
//		encoded.resize(size);
//		encoder.Get((byte*)&encoded[0], encoded.size());
//	}
//
//	return encoded;
//}
//
//
//byte* AES_CTR_Encrypt(const char *hexKey, const byte*input)
//{
//	string rtn_buffer;
//	SecByteBlock key(voidByteArray(16), 16);
//	SecByteBlock iv(voidByteArray(16), 16);
//	CTR_Mode<AES>::Encryption aes(key, key.size(), iv);
//	StreamTransformationFilter stf(aes, new StringSink(rtn_buffer));
//	stf.Put(input, strlen((char*)input));
//	stf.MessageEnd();
//	return (byte*)rtn_buffer.data();
//}