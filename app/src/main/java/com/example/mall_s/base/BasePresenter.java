package com.example.mall_s.base;


import com.example.mall_s.interfaces.IBasePresenter;
import com.example.mall_s.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    protected V mView;
    WeakReference<V> weakReference;
    CompositeDisposable compositeDisposable;
    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        mView = weakReference.get();
    }

    @Override
    public void detachView() {
        mView = null;
        clearSubscribe();
    }
    public void addSubscribe(Disposable disposable){
        if(compositeDisposable == null) compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(disposable);
    }


    public void clearSubscribe(){
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
    }
}
