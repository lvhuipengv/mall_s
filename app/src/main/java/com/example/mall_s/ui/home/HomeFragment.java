package com.example.mall_s.ui.home;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mall_s.R;
import com.example.mall_s.adapter.HomeListAa;
import com.example.mall_s.base.BaseFragment;
import com.example.mall_s.bean.HomeBean;
import com.example.mall_s.interfaces.home.IHome;
import com.example.mall_s.presenter.HomePresenter;
import com.example.mall_s.ui.DetailGoodActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<IHome.IPresenter> implements IHome.IView {

    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    HomeListAa homeListAa;
    List<HomeBean.HomeListBean> list;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        homeListAa = new HomeListAa(list, getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        homeListAa.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                int type = list.get(i).currentType;
                switch (type) {
                    case HomeBean.ITEM_TYPE_BANNER:
                    case HomeBean.ITEM_TYPE_TITLE:
                    case HomeBean.ITEM_TYPE_TAB:
                    case HomeBean.ITEM_TYPE_HOT:
                    case HomeBean.ITEM_TYPE_TITLETOP:
                    case HomeBean.ITEM_TYPE_TOPIC:
                        return 2;
                    case HomeBean.ITEM_TYPE_BRAND:
                    case HomeBean.ITEM_TYPE_NEW:
                    case HomeBean.ITEM_TYPE_CATEGORY:
                        return 1;

                }
                return 0;
            }
        });
        rvHome.setLayoutManager(gridLayoutManager);
        homeListAa.bindToRecyclerView(rvHome);
        homeListAa.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int type = list.get(position).currentType;
                Intent intent = new Intent();
                switch (type){
                    case HomeBean.ITEM_TYPE_BANNER:
                        break;
                    case HomeBean.ITEM_TYPE_BRAND:
                        break;
                    case HomeBean.ITEM_TYPE_HOT:
                        HomeBean.DataBean.HotGoodsListBean bean = (HomeBean.DataBean.HotGoodsListBean) list.get(position).data;
                        intent.putExtra("id",bean.getId());
                        intent.setClass(getContext(), DetailGoodActivity.class);
                        startActivity(intent);
                        break;
                    case HomeBean.ITEM_TYPE_TITLE:
                        break;
                    case HomeBean.ITEM_TYPE_TITLETOP:
                        break;
                    case HomeBean.ITEM_TYPE_TOPIC:
                        break;
                    case HomeBean.ITEM_TYPE_CATEGORY:
                        break;
                }
            }
        });
    }

    @Override
    protected IHome.IPresenter initpresenter() {
        return new HomePresenter();
    }

    @Override
    public void getHomeDataReturn(List<HomeBean.HomeListBean> result) {
        list.addAll(result);
        homeListAa.notifyDataSetChanged();
    }

    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visible) {

    }
}