package com.example.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WanBean.DataBean.DatasBean> list;


    public RecyAdapter(Context context, ArrayList<WanBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recy_item, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WanBean.DataBean.DatasBean datasBean = list.get(position);
        RequestOptions crop = new RequestOptions().circleCrop();
        holder.mItemTvRecy.setText(datasBean.getTitle());
        Glide.with(context).load(datasBean.getEnvelopePic())
                .apply(crop)
                .into(holder.mItemImgRecy);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemLis.onClickItem(position);
            }
        });
    }

    private OnClickItemLis onClickItemLis;

    public void setOnClickItemLis(OnClickItemLis onClickItemLis) {
        this.onClickItemLis = onClickItemLis;
    }

    interface OnClickItemLis{
        void onClickItem(int position);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mItemImgRecy;
        private TextView mItemTvRecy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemImgRecy = (ImageView) itemView.findViewById(R.id.recy_item_img);
            mItemTvRecy = (TextView) itemView.findViewById(R.id.recy_item_tv);
        }
    }
}
