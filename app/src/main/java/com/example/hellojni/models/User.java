package com.example.hellojni.models;

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
}
