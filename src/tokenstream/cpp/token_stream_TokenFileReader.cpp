#include "token_stream_TokenFileReader.h"
#include <iostream> 
#include <fstream> 
#include <vector>
#include <cstring> 
#include <cstdlib>
extern "C" {

JNIEXPORT jcharArray JNICALL Java_token_1stream_TokenFileReader_read0
(JNIEnv *env, jobject jobj, jstring path) {
        const char *convPath = env->GetStringUTFChars(path, NULL); 

        std::ifstream input(convPath);
        if (input.fail()) {
            jclass FileNotFoundException = env->FindClass("java/io/FileNotFoundException"); 
            env->ThrowNew(FileNotFoundException, ""); 
        }
        input.seekg(0); 
        input.clear(); 
        std::string contents ((std::istreambuf_iterator<char>(input)), std::istreambuf_iterator<char>()); 
        input.close(); 

        
        const char* charArr = contents.c_str(); 
        int charLen = contents.length();  

        jchar* jArr = new jchar[charLen];
        for (int i = 0; i <= charLen; i++) {
            jArr[i] = (jchar) charArr[i]; 
        }

        jcharArray jCharArr = env->NewCharArray(charLen + 1); 
        env->SetCharArrayRegion(jCharArr, 0, charLen, jArr); 
        delete[] jArr; 
        
        env->ReleaseStringUTFChars(path, convPath); 
        return jCharArr; 
    }
}