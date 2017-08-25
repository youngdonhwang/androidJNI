package com.example.hellojni.models;

import android.util.Log;

/**
 * Created by tuan.nv on 8/25/2017.
 *
 */

public class User {
    private int mUserId;
    private String mName;
    private double[] mTopScore;

    public User(int mUserId, String mName, double[] mTopScore) {
        this.mUserId = mUserId;
        this.mName = mName;
        this.mTopScore = mTopScore;
    }

    public void performXyz() {
        this.executeUserNativeMethod();
    }

    private String getUserInString() {
        return mUserId + " - " + mName;
    }

    public static void logUserInfo(String user) {
        Log.d("USER", user);
    }

    private double getAverageScore() {
        double sum = 0;
        for (double score : mTopScore) {
            sum += score;
        }

        return sum / mTopScore.length;
    }

    private native void executeUserNativeMethod();

    static {
        System.loadLibrary("app-utils");
    }
}
