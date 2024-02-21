package com.srsoft.pradeepservices.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.srsoft.pradeepservices.databinding.SliderLayoutBinding;
import com.srsoft.pradeepservices.modals.Advertisement;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.MyViewHolder> {

    private Context context;
    private List<Advertisement> items;
    private ViewPager2 viewPager2;

    public SliderAdapter(Context context, List<Advertisement> items, ViewPager2 viewPager2) {
        this.context = context;
        this.items = items;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SliderLayoutBinding mBinding = SliderLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Advertisement item = items.get(position);

        Glide.with(context)
                .load(item.getLink())
                /*.placeholder(circularProgressDrawable)*/
                .into(holder.mBinding.sliderImage);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        SliderLayoutBinding mBinding;

        MyViewHolder(SliderLayoutBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }

}
