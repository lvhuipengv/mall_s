package com.example.mall_s.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mall_s.R;
import com.example.mall_s.base.BaseActivity;
import com.example.mall_s.bean.AddCartInfoBean;
import com.example.mall_s.bean.GoodDetailBean;
import com.example.mall_s.common.CartCustomView;
import com.example.mall_s.interfaces.IDetail;
import com.example.mall_s.presenter.DetailPersenter;
import com.example.mall_s.utils.SpUtils;
import com.example.mall_s.utils.SystemUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailGoodActivity extends BaseActivity<IDetail.IPersenter> implements IDetail.IView {


    @BindView(R.id.layout_back)
    RelativeLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_des)
    TextView txtDes;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_product)
    TextView txtProduct;
    @BindView(R.id.layout_norms)
    FrameLayout layoutNorms;
    @BindView(R.id.layout_comment)
    FrameLayout layoutComment;
    @BindView(R.id.layout_imgs)
    LinearLayout layoutImgs;
    @BindView(R.id.layout_parameter)
    LinearLayout layoutParameter;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.layout_collect)
    RelativeLayout layoutCollect;
    @BindView(R.id.img_cart)
    ImageView imgCart;
    @BindView(R.id.layout_cart)
    RelativeLayout layoutCart;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.nest)
    NestedScrollView nest;
    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.time_commit)
    TextView timeCommit;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.txt_addcart)
    TextView txtAddcart;
    private String show;
    private int pric;
    private int currentNum = 1;
    private GoodDetailBean goodDetailBean;
    private String html = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                $\n" +
            "            </body>\n" +
            "        </html>";
    private PopupWindow mPopWindow;

    @Override
    protected int getLayout() {
        return R.layout.activity_detail_good;
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        presenter.getGoodDetail(id);
    }

    @Override
    protected void initView() {
        txtAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcart();
            }
        });
        layoutNorms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopWindow();
            }
        });
    }

    private void addcart() {
        String token = SpUtils.getInstance().getString("token");
        boolean islogin =!TextUtils.isEmpty(token);
        if(islogin){
            //判断当前的规格弹框是否打开
            if(mPopWindow != null && mPopWindow.isShowing()){
                //添加到购物车的操作
                if(goodDetailBean.getData().getProductList().size() > 0){
                    int goodsId = goodDetailBean.getData().getProductList().get(0).getGoods_id();
                    int productId = goodDetailBean.getData().getProductList().get(0).getId();
                    presenter.addCart(goodsId,currentNum,productId);
                    mPopWindow.dismiss();
                    mPopWindow = null;
                }else{
                    Toast.makeText(this,"没有产品数据",Toast.LENGTH_SHORT).show();
                }
            }else{
                showPopWindow();
            }
        }else{
            Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show();
            //Intent跳转到登录

        }
    }

    @Override
    protected IDetail.IPersenter initPersenter() {
        return new DetailPersenter();
    }

    @Override
    public void getGoodDetailReturn(GoodDetailBean result) {
        goodDetailBean=result;
        txtProduct.setText(result.getData().getBrand().getName() + ">");
        //banner刷新
        updateBanner(result.getData().getGallery());
        //评论
        if (result.getData().getComment().getCount() > 0) {
            layoutComment.setVisibility(View.VISIBLE);
            updateComment(result.getData().getComment());
        } else {
            layoutComment.setVisibility(View.GONE);
        }
        //设置参数
        updateParameter(result.getData().getAttribute());
        //详情信息的展示
        updateDetailInfo(result.getData().getInfo());
    }

    @Override
    public void addCartInfoReturn(AddCartInfoBean result) {

    }

    @Override
    public void showLoading(int visible) {

    }

    /**
     * 刷新banner
     */
    private void updateBanner(List<GoodDetailBean.DataBeanX.GalleryBean> gallery) {
        if (banner.getTag() == null || (int) banner.getTag() == 0) {
            List<String> imgs = new ArrayList<>();
            for (GoodDetailBean.DataBeanX.GalleryBean item : gallery) {
                imgs.add(item.getImg_url());
            }
            show = imgs.get(0);
            banner.setImages(imgs).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            }).start();

        }
    }

    /**
     * 刷新评论
     *
     * @param commentBean
     */
    private void updateComment(GoodDetailBean.DataBeanX.CommentBean commentBean) {
        timeCommit.setText(commentBean.getData().getAdd_time());
        commit.setText(commentBean.getData().getContent());
        if (!TextUtils.isEmpty(commentBean.getData().getNickname())) {
            nickname.setText(commentBean.getData().getNickname());
        }
        List<GoodDetailBean.DataBeanX.CommentBean.DataBean.PicListBean> pic_list = commentBean.getData().getPic_list();
        for (int i = 0; i < pic_list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_img, null);
            ImageView imageView = view.findViewById(R.id.img_com);
            Glide.with(this).load(pic_list.get(i).getPic_url()).into(imageView);
            layoutImgs.addView(view);
        }
    }

    /**
     * 刷新参数的布局
     *
     * @param attributeBeans
     */
    private void updateParameter(List<GoodDetailBean.DataBeanX.AttributeBean> attributeBeans) {
        layoutParameter.removeAllViews(); //清空
        for (GoodDetailBean.DataBeanX.AttributeBean item : attributeBeans) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_parameter, null);
            TextView name = view.findViewById(R.id.txt_parameter_name);
            TextView value = view.findViewById(R.id.txt_parameter_value);
            name.setText(item.getName());
            value.setText(item.getValue());
            layoutParameter.addView(view);
        }
    }

    private void updateDetailInfo(GoodDetailBean.DataBeanX.InfoBean infoBean) {
        txtName.setText(infoBean.getName());
        txtDes.setText(infoBean.getGoods_brief());
        txtPrice.setText("￥" + infoBean.getRetail_price());
        pric = infoBean.getRetail_price();
        if (!TextUtils.isEmpty(infoBean.getGoods_desc())) {
            String h5 = infoBean.getGoods_desc();
            html = html.replace("$", h5);
            webView.loadDataWithBaseURL("about:blank", html, "text/html", "utf-8", null);
            //webView.loadData(html,"text/html","utf-8");
        }
    }

    /**
     * 设置弹框
     */
    private void showPopWindow() {
        if (mPopWindow != null && mPopWindow.isShowing()) {

        } else {
            View popview = LayoutInflater.from(this).inflate(R.layout.layout_popwindow_good, null);
            int height = SystemUtils.dp2px(this, 250);
            mPopWindow = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT, height);
            mPopWindow.setFocusable(false);
//            mPopWindow.setBackgroundDrawable(null);
//            mPopWindow.setOutsideTouchable(true);
            popview.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            CartCustomView cartCustomView = popview.findViewById(R.id.cartwindow);
            ImageView img = popview.findViewById(R.id.img_good);
            Glide.with(this).load(show).into(img);
            TextView price = popview.findViewById(R.id.txt_price);
            price.setText("价格：￥" + pric);
            TextView txtClose = popview.findViewById(R.id.txt_close);
            txtClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPopWindow.dismiss();
                    mPopWindow = null;
                }
            });

            bg.setVisibility(View.VISIBLE);
            mPopWindow.showAsDropDown(layoutBottom, 0, 0);
            mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    bg.setVisibility(View.GONE);
                }
            });
            cartCustomView.initView();
            cartCustomView.setOnClickListener(new CartCustomView.IClick() {
                @Override
                public void clickCB(int value) {
                    currentNum = value;
                }
            });
        }
    }


}