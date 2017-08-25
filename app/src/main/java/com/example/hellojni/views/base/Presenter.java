package com.example.hellojni.views.base;


public interface Presenter<V extends MvpView> {

    V getMvpView();

    void attachView(V mvpView);

    void detachView();
}
