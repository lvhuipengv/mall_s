package com.example.mall_s.interfaces;


public interface IBasePresenter<V extends IBaseView> {
    //V层接口的关联
    void attachView(V view);

    //V层接口的取消关联
    void detachView();
}
