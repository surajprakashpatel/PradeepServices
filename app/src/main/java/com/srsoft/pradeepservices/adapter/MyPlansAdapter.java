package com.srsoft.pradeepservices.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.srsoft.pradeepservices.databinding.ServiceItemLayoutBinding;
import com.srsoft.pradeepservices.modals.Item;
import com.srsoft.pradeepservices.ui.activities.ImagePreviewActivity;

import java.util.List;

public class MyPlansAdapter extends RecyclerView.Adapter<MyPlansAdapter.MyViewHolder> {

    Intent intent;

    private Context context;
    private List<Item> item;

    public MyPlansAdapter(Context context, List<Item> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ServiceItemLayoutBinding mBinding = ServiceItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item currItem = item.get(position);
        Glide.with(context)
                .load(item.get(position).getLink())
                .into(holder.mBinding.categoryImage);
        holder.mBinding.categoryImage.setPadding(0, 0, 0, 0);
        holder.mBinding.categoryTitle.setVisibility(View.GONE);
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImagePreviewActivity.class);
                intent.putExtra("url", currItem.getLink());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ServiceItemLayoutBinding mBinding;

        MyViewHolder(ServiceItemLayoutBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}