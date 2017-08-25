package com.example.hellojni.views.hellojni;

import com.example.hellojni.models.User;
import com.example.hellojni.views.base.BasePresenter;

public class HelloPresenter extends BasePresenter<HelloJniMvpView> {

    /* this is used to load the 'hello-jni' library on application
     * startup. The library has already been unpacked into
     * /data/data/com.example.hellojni/lib/libhello-jni.so at
     * installation time by the package manager.
     */
    static {
        System.loadLibrary("hello-jni");
        System.loadLibrary("app-utils");
    }

    private int mId = 123456789;
    private String mMessage = "Hello from JAVA";

    void helloFromJni() {
        getMvpView().showMessage(stringFromJNI());
    }

    void performXyz() {
        int sum = addInteger(10, 15);
        getMvpView().showMessage("Sum = " + sum);

        double avg = average(10, 15);
        getMvpView().showMessage("Average = " + avg);

        //String str = "Có dấu đây nè :P";
        String str = "123456789";
        getMvpView().showMessage("Reserved string = " + reverseString(str));

        double[] sumAndAvg = sumAndAverage(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        if (sumAndAvg != null) {
            getMvpView().showMessage("Sum = " + sumAndAvg[0] + ", Avg = " + sumAndAvg[1]);
        }
    }

    void modifyObject() {
        modifyThisClass();
        getMvpView().showMessage("ID = " + mId + ", Message = " + mMessage);
    }

    void performCallbackFromC() {
        User user = new User(123456, "Nguyen Van Tuan", new double[] {1, 2, 3, 4, 5});
        user.performXyz();
    }

    /* This is another native method declaration that is *not*
     * implemented by 'hello-jni'. This is simply to show that
     * you can declare as many native methods in your Java code
     * as you want, their implementation is searched in the
     * currently loaded native libraries only the first time
     * you call them.
     *
     * Trying to call this function will result in a
     * java.lang.UnsatisfiedLinkError exception !
     */
    public native String unimplementedStringFromJNI();

    public native String stringFromJNI();

    private native int addInteger(int number1, int number2);

    private native double average(int number1, int number2);

    private native String reverseString(String str);

    private native double[] sumAndAverage(int[] array);

    private native void modifyThisClass();
}
