package com.example.mall_s.interfaces.home;

import com.example.mall_s.bean.HomeBean;
import com.example.mall_s.interfaces.IBasePresenter;
import com.example.mall_s.interfaces.IBaseView;

import java.util.List;

public interface IHome {
    interface IView extends IBaseView {
        void getHomeDataReturn(List<HomeBean.HomeListBean> result);
    }

    interface IPresenter extends IBasePresenter<IView> {
        void getHomeData();
    }
}
