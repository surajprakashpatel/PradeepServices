package com.srsoft.pradeepservices.ui.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.srsoft.pradeepservices.adapter.PlansAndPoliciesAdapter;
import com.srsoft.pradeepservices.databinding.ActivityPlansPoliciesBinding;
import com.srsoft.pradeepservices.modals.Plans;
import com.srsoft.pradeepservices.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlansPoliciesActivity extends AppCompatActivity {

    private List<Plans> items = new ArrayList<>();
    private PlansAndPoliciesAdapter adapter;
    private ActivityPlansPoliciesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String lang = PreferenceUtils.getString("lang", PlansPoliciesActivity.this);
        if (lang.matches("hindi")) {
            Locale locale = new Locale("hi");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
        binding = ActivityPlansPoliciesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
    }

    private void initialization() {
        binding.btnBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.recyclerView.setLayoutManager(new GridLayoutManager(PlansPoliciesActivity.this, 2));
        adapter = new PlansAndPoliciesAdapter(PlansPoliciesActivity.this, items);
        binding.recyclerView.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("plansAndPolicies").orderBy("id").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            items.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                items.add(document.toObject(Plans.class));
                            }
                            adapter.notifyDataSetChanged();

                        } else {

                        }
                    }

                });

    }
}