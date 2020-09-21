package com.example.mall_s.interfaces;

import com.example.mall_s.bean.AddCartInfoBean;
import com.example.mall_s.bean.CartBean;
import com.example.mall_s.bean.DeleteCartBean;
import com.example.mall_s.bean.GoodDetailBean;

public interface IDetail {
    interface IView extends IBaseView{
        void getGoodDetailReturn(GoodDetailBean result);
        //添加商品信息返回
        void addCartInfoReturn(AddCartInfoBean result);
    }

    interface IPersenter extends IBasePresenter<IView>{
        void getGoodDetail(int id);
        //添加到购物车
        void addCart(int goodsId, int number, int productId);
    }
    /**
     * 购物车接口
     */
    interface ICartView extends IBaseView{
        void getCartListReturn(CartBean result);

        void deleteCartListReturn(DeleteCartBean result);
    }

    interface ICartPersenter extends IBasePresenter<IDetail.ICartView>{

        //获取购物车的数据
        void getCartList();

        //删除购物车数据
        void deleteCartList(String productIds);

    }
}
