package com.example.mall_s.model.api;


import com.example.mall_s.bean.AddCartInfoBean;
import com.example.mall_s.bean.CartBean;
import com.example.mall_s.bean.DeleteCartBean;
import com.example.mall_s.bean.GoodDetailBean;
import com.example.mall_s.bean.HomeBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MApi {
    @GET("api/index")
    Flowable<HomeBean>getHome();
    @GET("goods/detail")
    Flowable<GoodDetailBean> getGoodDetail(@Query("id") int id);
    //添加到购物车
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCartInfoBean> addCart(@Field("goodsId") int goodsId, @Field("number") int number, @Field("productId") int productId);

    @GET("cart/index")
    Flowable<CartBean> getCartList();

    //删除购物车
    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCartBean> cartDelete(@Field("productIds") String productIds);
}
