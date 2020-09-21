package com.example.mall_s.common;


import com.example.mall_s.app.MyApp;

import java.io.File;

public class Constants {


    public static String base_Url = "https://cdwan.cn/";
    public static final String[] sexa = {"男", "女"};

    //网络缓存的地址
    public static final String PATH_DATA = MyApp.app.getCacheDir().getPath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/client";
}