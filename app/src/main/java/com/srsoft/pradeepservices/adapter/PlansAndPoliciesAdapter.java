package com.srsoft.pradeepservices.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srsoft.pradeepservices.databinding.PlansItemLayoutBinding;
import com.srsoft.pradeepservices.modals.Plans;
import com.srsoft.pradeepservices.ui.activities.WebViewActivity;

import java.util.List;

public class PlansAndPoliciesAdapter extends RecyclerView.Adapter<PlansAndPoliciesAdapter.MyViewHolder> {

    Intent intent;

    private Context context;
    private List<Plans> item;

    public PlansAndPoliciesAdapter(Context context, List<Plans> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public PlansAndPoliciesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlansItemLayoutBinding mBinding = PlansItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new PlansAndPoliciesAdapter.MyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlansAndPoliciesAdapter.MyViewHolder holder, int position) {

        Plans currItem = item.get(position);
        holder.mBinding.categoryTitle.setText(currItem.getName());
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
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

        PlansItemLayoutBinding mBinding;

        MyViewHolder(PlansItemLayoutBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}