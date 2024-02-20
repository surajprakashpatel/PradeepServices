package com.srsoft.pradeepservices.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srsoft.pradeepservices.databinding.ItemCommonBinding;
import com.srsoft.pradeepservices.modals.Item;
import com.srsoft.pradeepservices.modals.Plans;
import com.srsoft.pradeepservices.ui.activities.WebViewActivity;

import java.util.List;

public class ImportantLinksAdapter extends RecyclerView.Adapter<ImportantLinksAdapter.MyViewHolder> {
    private Context context;
    private List<Item> items;

    String x = "y";
    private List<Plans> learningMaterials;

    public ImportantLinksAdapter(Context context, List<Item> items ) {
        this.context = context;
        this.items = items;
    }
    public ImportantLinksAdapter(Context context, List<Plans> learningMaterials,String x){
        this.context = context;
        this.learningMaterials = learningMaterials;
        this.x=x;
    }

    @NonNull
    @Override
    public ImportantLinksAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommonBinding mBinding = ItemCommonBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ImportantLinksAdapter.MyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(x.matches("x")){
            Plans plans = learningMaterials.get(position);
            holder.mBinding.tvTitle.setText(plans.getName());
            holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("url",plans.getLink());
                    context.startActivity(intent);
                }
            });
        }else{
            Item item = items.get(position);


            holder.mBinding.tvTitle.setText(item.getName());
            holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("url",item.getLink());
                    context.startActivity(intent);
                }
            });
        }


    }



    @Override
    public int getItemCount() {
        if(x.matches("x")){
            return learningMaterials.size();
        }else{
            return items.size();
        }


    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemCommonBinding mBinding;

        MyViewHolder(ItemCommonBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}
