package com.example.mall_s.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mall_s.interfaces.IBasePresenter;
import com.example.mall_s.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    private Unbinder bind;
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        bind = ButterKnife.bind(this);

        presenter = initPersenter();
        if (presenter != null) {
            presenter.attachView(this);
            initData();
        }
        initView();
    }

    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P initPersenter();


    @Override
    public void showTips(String tips) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) {
            bind.unbind();
        }
        if (presenter!=null){
            presenter.detachView();
        }
    }
}
