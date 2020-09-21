package com.example.mall_s.ui.trolley;

import com.example.mall_s.R;
import com.example.mall_s.base.BaseFragment;
import com.example.mall_s.bean.AddCartInfoBean;
import com.example.mall_s.bean.GoodDetailBean;
import com.example.mall_s.interfaces.IDetail;


public class TrolleyFragment extends BaseFragment<IDetail.IPersenter> implements IDetail.IView {

    @Override
    protected int getLayout() {
        return R.layout.fragment_trolley  ;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected IDetail.IPersenter initpresenter() {
        return null;
    }

    @Override
    public void getGoodDetailReturn(GoodDetailBean result) {

    }

    @Override
    public void addCartInfoReturn(AddCartInfoBean result) {

    }


    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visible) {

    }
}