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