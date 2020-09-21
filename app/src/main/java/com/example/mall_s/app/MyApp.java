package com.example.mall_s.app;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

public class MyApp extends MultiDexApplication {
    public static Context app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
