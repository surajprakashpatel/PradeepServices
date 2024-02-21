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
import com.srsoft.pradeepservices.modals.Services;
import com.srsoft.pradeepservices.ui.activities.AboutUsActivity;
import com.srsoft.pradeepservices.ui.activities.CustomerServiceActivity;
import com.srsoft.pradeepservices.ui.activities.FeedbackActivity;
import com.srsoft.pradeepservices.ui.activities.GalleryActivity;
import com.srsoft.pradeepservices.ui.activities.ImportantLinksActivity;
import com.srsoft.pradeepservices.ui.activities.LearningMaterialActivity;
import com.srsoft.pradeepservices.ui.activities.MyPlansActivity;
import com.srsoft.pradeepservices.ui.activities.PlansPoliciesActivity;

import java.util.List;

public class ServicesAdapater extends RecyclerView.Adapter<ServicesAdapater.MyViewHolder> {

    Intent intent;

    private Context context;
    private List<Services> services;

    public ServicesAdapater(Context context, List<Services> services) {
        this.context = context;
        this.services = services;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ServiceItemLayoutBinding mBinding = ServiceItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context)
                .load(services.get(position).getImage())
                .into(holder.mBinding.categoryImage);
        holder.mBinding.categoryTitle.setText(services.get(position).getName());
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                switch (position){
                    case 0 :
                       intent = new Intent(context, AboutUsActivity.class);
                        break;
                    case 1 :
                       intent = new Intent(context, MyPlansActivity.class);
                        break;
                    case 2 :
                        intent = new Intent(context, CustomerServiceActivity.class);
                        break;
                    case 3 :
                        intent = new Intent(context, GalleryActivity.class);
                        break;
                    case 4 :
                        intent = new Intent(context, PlansPoliciesActivity.class);
                        break;
                    case 5 :
                        intent = new Intent(context, FeedbackActivity.class);
                        break;
                    case 6 :
                        intent = new Intent(context, LearningMaterialActivity.class);
                        break;
                    case 7 :
                        intent = new Intent(context, ImportantLinksActivity.class);
                        break;
                }
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ServiceItemLayoutBinding mBinding;
        MyViewHolder(ServiceItemLayoutBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}
