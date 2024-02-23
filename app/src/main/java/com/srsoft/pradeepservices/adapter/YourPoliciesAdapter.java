package com.srsoft.pradeepservices.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.srsoft.pradeepservices.databinding.ItemCommonBinding;
import com.srsoft.pradeepservices.modals.MyPolicies;

import java.util.List;

public class YourPoliciesAdapter extends RecyclerView.Adapter<YourPoliciesAdapter.MyViewHolder> {
    private Context context;
    private List<MyPolicies> items;


    public YourPoliciesAdapter(Context context, List<MyPolicies> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public YourPoliciesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommonBinding mBinding = ItemCommonBinding.inflate(LayoutInflater.from(context), parent, false);
        return new YourPoliciesAdapter.MyViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MyPolicies item = items.get(position);

        holder.mBinding.tvTitle.setText(item.getPlanName());

        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Your Plan/Policy");
                builder.setMessage("Policy Number: " + item.getPolicyNumber() +
                        "\n\nPlan Name: " + item.getPlanName() +
                        "\n\nCustomer Name: " + item.getName() +
                        "\n\nDate of Birth: " + item.getDob() +
                        "\n\nMobile Number: " + item.getMobileNumber() +
                        "\n\nCommencement Date: " + item.getDateofCommencement() +
                        "\n\nMode: " + item.getPremiumFrequency()+
                        "\n\nMaturity Date: " + item.getMaturity() +
                        "\n\nLast Premium: " + item.getLastPremiumDate() +
                        "\n\nNominee Name: " + item.getNomineeName() +
                        "\n\nNominee Age: " + item.getNomineeAge() +
                        "\n\nSum Assured: " + item.getSumAssured()
                );
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemCommonBinding mBinding;

        MyViewHolder(ItemCommonBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}
