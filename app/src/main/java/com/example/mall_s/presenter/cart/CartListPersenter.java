package com.example.mall_s.presenter.cart;


import com.example.mall_s.base.BasePresenter;
import com.example.mall_s.bean.CartBean;
import com.example.mall_s.bean.DeleteCartBean;
import com.example.mall_s.common.CommonSubscriber;
import com.example.mall_s.interfaces.IDetail;
import com.example.mall_s.model.HttpManager;
import com.example.mall_s.utils.RxUtils;

public class CartListPersenter extends BasePresenter<IDetail.ICartView> implements IDetail.ICartPersenter {
    @Override
    public void getCartList() {
        addSubscribe(HttpManager.getInstance().getMApi().getCartList()
                .compose(RxUtils.<CartBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartBean>(mView) {
                    @Override
                    public void onNext(CartBean result) {
                        mView.getCartListReturn(result);
                    }
                }));
    }

    @Override
    public void deleteCartList(String productIds) {
        addSubscribe(HttpManager.getInstance().getMApi().cartDelete(productIds)
                .compose(RxUtils.<DeleteCartBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCartBean>(mView) {
                    @Override
                    public void onNext(DeleteCartBean result) {
                        mView.deleteCartListReturn(result);
                    }
                }));
    }
}
