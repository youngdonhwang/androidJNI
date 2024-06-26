//
// Created by tuan.nv on 8/24/2017.
//

#include <string.h>
#include <jni.h>

JNIEXPORT jint JNICALL
Java_com_example_hellojni_views_hellojni_HelloPresenter_addInteger(JNIEnv *env,
                                                                   jobject this,
                                                                   jint number1,
                                                                   jint number2) {
    jint result = number1 + number2;
    return result;
}

JNIEXPORT jdouble JNICALL
Java_com_example_hellojni_views_hellojni_HelloPresenter_average(JNIEnv *env,
                                                                jobject this,
                                                                jint number1,
                                                                jint number2) {
    return (jdouble) ((number1 + number2) / 2.0);
}

JNIEXPORT jstring JNICALL
Java_com_example_hellojni_views_hellojni_HelloPresenter_reverseString(JNIEnv *env, jobject instance,
                                                                      jstring str_) {
    char *str = (*env)->GetStringUTFChars(env, str_, 0);
    if (str == NULL) return (*env)->NewStringUTF(env, "ERROR: String is NULL");

    jint l = strlen(str);
    jint i = 0;
    while (i < l - i - 1) {
        char c = str[i];
        str[i] = str[l - i - 1];
        str[l - i - 1] = c;

        i++;
    }

    (*env)->ReleaseStringUTFChars(env, str_, str);
    return (*env)->NewStringUTF(env, str);
}

JNIEXPORT jdoubleArray JNICALL
Java_com_example_hellojni_views_hellojni_HelloPresenter_sumAndAverage(JNIEnv *env, jobject instance,
                                                                      jintArray array_) {
    jint *array = (*env)->GetIntArrayElements(env, array_, NULL);
    if (array == NULL) return NULL;

    jdouble result[2];
    double sum = 0;
    jsize length = (*env)->GetArrayLength(env, array_);

    for (jint i = 0; i < length; i++) {
        sum += array[i];
    }
    result[0] = sum;
    result[1] = sum / length;

    (*env)->ReleaseIntArrayElements(env, array_, array, 0);
    jdoubleArray outArray = (*env)->NewDoubleArray(env, 2);
    if (outArray == NULL) return NULL;
    (*env)->SetDoubleArrayRegion(env, outArray, 0, 2, result);
    return outArray;
}

JNIEXPORT void JNICALL
Java_com_example_hellojni_views_hellojni_HelloPresenter_modifyThisClass(JNIEnv *env,
                                                                        jobject helloActivity) {
    jclass c_class = (*env)->GetObjectClass(env, helloActivity);

    // Get mId and mMessage field ID
    jfieldID fidId = (*env)->GetFieldID(env, c_class, "mId", "I");
    jfieldID fidMessage = (*env)->GetFieldID(env, c_class, "mMessage", "Ljava/lang/String;");
    if (fidId == NULL || fidMessage == NULL)
        return;

    // Get value
    jint mId = (*env)->GetIntField(env, helloActivity, fidId);
    jstring mMessage = (*env)->GetObjectField(env, helloActivity, fidMessage);

    // Update value
    mId = 987654321;
    (*env)->SetIntField(env, helloActivity, fidId, mId);
    mMessage = (*env)->NewStringUTF(env, "Hello from JNI");
    if (mMessage != NULL)
        (*env)->SetObjectField(env, helloActivity, fidMessage, mMessage);
}

JNIEXPORT void JNICALL
Java_com_example_hellojni_models_User_executeUserNativeMethod(JNIEnv *env, jobject userObj) {
    // Get a class reference to user object
    jclass userClass = (*env)->GetObjectClass(env, userObj);

    jmethodID getUserInString = (*env)->GetMethodID(env, userClass, "getUserInString", "()Ljava/lang/String;");
    jmethodID logUserInfo = (*env)->GetStaticMethodID(env, userClass, "logUserInfo", "(Ljava/lang/String;)V");
    jmethodID getAverageScore = (*env)->GetMethodID(env, userClass, "getAverageScore", "()D");

    if (getUserInString == NULL || logUserInfo == NULL || getAverageScore == NULL)
        return;

    jstring userInfo = (*env)->CallObjectMethod(env, userObj, getUserInString);
    jdouble avgScore = (*env)->CallDoubleMethod(env, userObj, getAverageScore);
    (*env)->CallStaticVoidMethod(env, userClass, logUserInfo, userInfo);
}

/*
jstring appendDouble(JNIEnv *env, jstring str, jdouble value) {
    ___CONCAT("", "");
}*/
