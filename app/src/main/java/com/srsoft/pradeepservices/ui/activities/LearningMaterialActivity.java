package com.srsoft.pradeepservices.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.srsoft.pradeepservices.R;
import com.srsoft.pradeepservices.adapter.ImportantLinksAdapter;
import com.srsoft.pradeepservices.adapter.PlansAndPoliciesAdapter;
import com.srsoft.pradeepservices.databinding.ActivityLearningMaterialBinding;
import com.srsoft.pradeepservices.modals.Plans;

import java.util.ArrayList;
import java.util.List;

public class LearningMaterialActivity extends AppCompatActivity {

    private List<Plans> items = new ArrayList<>();
    private ImportantLinksAdapter adapter;
    private ActivityLearningMaterialBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLearningMaterialBinding.inflate(getLayoutInflater());
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
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(LearningMaterialActivity.this));
        adapter = new ImportantLinksAdapter(LearningMaterialActivity.this, items,"x");
        binding.recyclerView.setAdapter(adapter);

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("learningMaterial").orderBy("id").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
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