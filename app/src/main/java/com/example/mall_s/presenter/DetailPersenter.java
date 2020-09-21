package com.example.mall_s.presenter;


import com.example.mall_s.base.BasePresenter;
import com.example.mall_s.bean.AddCartInfoBean;
import com.example.mall_s.bean.GoodDetailBean;
import com.example.mall_s.common.CommonSubscriber;
import com.example.mall_s.interfaces.IDetail;
import com.example.mall_s.model.HttpManager;
import com.example.mall_s.utils.RxUtils;

public class DetailPersenter extends BasePresenter<IDetail.IView> implements IDetail.IPersenter {
    @Override
    public void getGoodDetail(int id) {
        addSubscribe(HttpManager.getInstance().getMApi().getGoodDetail(id)
                .compose(RxUtils.<GoodDetailBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodDetailBean>(mView) {
                    @Override
                    public void onNext(GoodDetailBean result) {
                        mView.getGoodDetailReturn(result);
                    }
                }));
    }

    @Override
    public void addCart(int goodsId, int number, int productId) {
        addSubscribe(HttpManager.getInstance().getMApi().addCart(goodsId,number,productId)
                .compose(RxUtils.<AddCartInfoBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCartInfoBean>(mView) {
                    @Override
                    public void onNext(AddCartInfoBean result) {
                        mView.addCartInfoReturn(result);
                    }
                }));
    }

}
