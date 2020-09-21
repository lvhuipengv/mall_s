package com.example.mall_s.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mall_s.R;
import com.example.mall_s.base.BaseAdapter;
import com.example.mall_s.bean.HomeBean;

import java.util.List;

public class TopicAdapter extends BaseAdapter<HomeBean.DataBean.TopicListBean> {
    public TopicAdapter(Context context, List<HomeBean.DataBean.TopicListBean> list) {
        super(context, list);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_home_topic;
    }

    @Override
    protected void bindData(BaseViewHolder viewHolder, HomeBean.DataBean.TopicListBean data) {
        ImageView img = (ImageView) viewHolder.getViewById(R.id.img_topic);
        TextView name = (TextView) viewHolder.getViewById(R.id.txt_topic_name);
        TextView sub = (TextView) viewHolder.getViewById(R.id.txt_topic_sub);
        TextView price = (TextView) viewHolder.getViewById(R.id.txt_topic_price);
        Glide.with(context).load(data.getItem_pic_url()).into(img);
        name.setText(data.getTitle());
        sub.setText(data.getSubtitle());
        price.setText("￥"+data.getPrice_info()+"元起");
    }
}
