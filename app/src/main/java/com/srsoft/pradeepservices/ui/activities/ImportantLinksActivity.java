package com.srsoft.pradeepservices.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.srsoft.pradeepservices.databinding.ActivityImportantLinksBinding;
import com.srsoft.pradeepservices.modals.Item;

import java.util.ArrayList;
import java.util.List;

public class ImportantLinksActivity extends AppCompatActivity {

    private List<Item> items = new ArrayList<>();
    private ImportantLinksAdapter impLinksAdapter;
    private ActivityImportantLinksBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImportantLinksBinding.inflate(getLayoutInflater());
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
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(ImportantLinksActivity.this));
        impLinksAdapter = new ImportantLinksAdapter(ImportantLinksActivity.this, items);
        binding.recyclerView.setAdapter(impLinksAdapter);

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("impLinks").orderBy("id").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                           items.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                items.add(document.toObject(Item.class));
                            }
                            impLinksAdapter.notifyDataSetChanged();

                        } else {

                        }
                    }

                });
    }

}