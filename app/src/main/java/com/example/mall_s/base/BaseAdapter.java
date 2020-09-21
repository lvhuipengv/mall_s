package com.example.mall_s.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    protected Context context;
    private List<T> list;
    private IckickL ickickL;

    public void setIckickL(IckickL ickickL) {
        this.ickickL = ickickL;
    }

    public BaseAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    protected abstract int getLayout();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(getLayout(), parent, false);
        final BaseViewHolder viewHolder = new BaseViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ickickL.Iclick(viewHolder.getLayoutPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder vh = (BaseViewHolder) holder;
        T data = list.get(position);
        bindData(vh,data);
    }

    protected abstract void bindData(BaseViewHolder viewHolder, T data);

    @Override
    public int getItemCount() {
        return list.size();
    }

     public interface IckickL {
        void Iclick(int postion);
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        SparseArray views = new SparseArray();

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public View getViewById(int id) {
            View view = (View) views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.append(id, view);
            }
            return view;
        }
    }
}
