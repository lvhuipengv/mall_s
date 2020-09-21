package com.example.mall_s.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mall_s.R;
import com.example.mall_s.bean.HomeBean;
import com.example.mall_s.utils.SystemUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeListAa extends BaseMultiItemQuickAdapter<HomeBean.HomeListBean, BaseViewHolder> {
    private Context context;
    private TopicAdapter topicAdapter;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeListAa(List<HomeBean.HomeListBean> data,Context context) {
        super(data);
        this.context=context;
        addItemType(HomeBean.ITEM_TYPE_BANNER, R.layout.layout_home_banner);
        addItemType(HomeBean.ITEM_TYPE_TAB,R.layout.layout_home_tab);
        addItemType(HomeBean.ITEM_TYPE_TITLETOP,R.layout.layout_title_top);
        addItemType(HomeBean.ITEM_TYPE_BRAND,R.layout.layout_home_brand);
        addItemType(HomeBean.ITEM_TYPE_TITLE,R.layout.layout_title);
        addItemType(HomeBean.ITEM_TYPE_NEW,R.layout.layout_home_newgood);
        addItemType(HomeBean.ITEM_TYPE_HOT,R.layout.layout_home_hot);
        addItemType(HomeBean.ITEM_TYPE_TOPIC,R.layout.layout_home_topiclist);
        addItemType(HomeBean.ITEM_TYPE_CATEGORY,R.layout.layout_home_good);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.HomeListBean item) {
        switch (item.getItemType()){
            case HomeBean.ITEM_TYPE_BANNER:
                updateBanner(helper, (List<HomeBean.DataBean.BannerBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_TAB:
                updateTab(helper, (List<HomeBean.DataBean.ChannelBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_TITLE:
                updateTitle(helper, (String) item.data);
                break;
            case HomeBean.ITEM_TYPE_BRAND:
                updateBrand(helper, (HomeBean.DataBean.BrandListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_NEW:
                updateNewGood(helper, (HomeBean.DataBean.NewGoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_CATEGORY:
                updateGood(helper, (HomeBean.DataBean.CategoryListBean.GoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_HOT:
                udpateHot(helper, (HomeBean.DataBean.HotGoodsListBean) item.data);
                break;
            case HomeBean.ITEM_TYPE_TOPIC:
                updateTopic(helper, (List<HomeBean.DataBean.TopicListBean>) item.data);
                break;
            case HomeBean.ITEM_TYPE_TITLETOP:
                updateTitle(helper, (String) item.data);
                break;
        }
    }

    private void updateGood(BaseViewHolder helper, HomeBean.DataBean.CategoryListBean.GoodsListBean data) {
        ImageView img = helper.getView(R.id.img_newgood);
        TextView name = helper.getView(R.id.txt_newgood_name);
        TextView price = helper.getView(R.id.txt_newgood_price);
        Glide.with(context).load(data.getList_pic_url()).into(img);
        name.setText(data.getName());
        price.setText("￥"+data.getRetail_price());
    }

    private void updateTopic(BaseViewHolder helper, List<HomeBean.DataBean.TopicListBean> data) {
        RecyclerView rv = helper.getView(R.id.recyclerviewTopic);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        rv.setLayoutManager(manager);
        if(topicAdapter == null){
            topicAdapter = new TopicAdapter(context, data);
            rv.setAdapter(topicAdapter);
        }else if(rv.getAdapter() == null){
            rv.setAdapter(topicAdapter);
        }
    }

    private void udpateHot(BaseViewHolder helper, HomeBean.DataBean.HotGoodsListBean data) {
        ImageView img = helper.getView(R.id.img_hot);
        TextView name = helper.getView(R.id.txt_hot_name);
        TextView title = helper.getView(R.id.txt_hot_title);
        TextView price = helper.getView(R.id.txt_hot_price);
        Glide.with(context).load(data.getList_pic_url()).into(img);
        name.setText(data.getName());
        title.setText(data.getGoods_brief());
        price.setText("￥"+data.getRetail_price());
    }

    private void updateNewGood(BaseViewHolder helper, HomeBean.DataBean.NewGoodsListBean data) {
        ImageView img = helper.getView(R.id.img_newgood);
        TextView name = helper.getView(R.id.txt_newgood_name);
        TextView price = helper.getView(R.id.txt_newgood_price);
        Glide.with(context).load(data.getList_pic_url()).into(img);
        name.setText(data.getName());
        price.setText("￥"+data.getRetail_price());
    }

    private void updateBrand(BaseViewHolder helper, HomeBean.DataBean.BrandListBean data) {
        ImageView img = helper.getView(R.id.img_brand);
        TextView name = helper.getView(R.id.txt_brand_name);
        TextView price = helper.getView(R.id.txt_brand_price);
        Glide.with(context).load(data.getNew_pic_url()).into(img);
        name.setText(data.getName());
        price.setText(data.getFloor_price()+"元起");
    }

    private void updateTab(BaseViewHolder helper, List<HomeBean.DataBean.ChannelBean> data) {
        LinearLayout layoutChannels = helper.getView(R.id.layout_tab);
        //只让当前的布局内容添加一次 only one
        if(layoutChannels.getChildCount() == 0){
            for(HomeBean.DataBean.ChannelBean item:data){
                TextView tab = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1);
                int size = SystemUtils.dp2px(context,11);
                tab.setTextSize(size);
                tab.setGravity(Gravity.CENTER);
                tab.setText(item.getName());
                params.leftMargin=10;
                params.rightMargin=10;
                tab.setLayoutParams(params);
//                Drawable icon = context.getDrawable(R.mipmap.ic_channel1);
//                tab.setCompoundDrawables(null,icon,null,null);
                layoutChannels.addView(tab);
            }
        }
    }

    private void updateBanner(BaseViewHolder helper, List<HomeBean.DataBean.BannerBean> data) {
        Banner banner = helper.getView(R.id.banner_home);
        if (banner.getTag()==null||(int )banner.getTag()==0){
            ArrayList<String> imgs = new ArrayList<>();
            for (HomeBean.DataBean.BannerBean item: data){
                imgs.add(item.getImage_url());
            }
            banner.setImages(imgs).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            }).start();
        }
    }

    private void updateTitle(BaseViewHolder helper, String data) {
        TextView title = helper.getView(R.id.txt_title);
        title.setText(data);
    }
}
