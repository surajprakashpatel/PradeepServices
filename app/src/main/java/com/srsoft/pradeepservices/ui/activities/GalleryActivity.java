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
import com.srsoft.pradeepservices.adapter.MyPlansAdapter;
import com.srsoft.pradeepservices.databinding.ActivityGalleryBinding;
import com.srsoft.pradeepservices.modals.Item;
import com.srsoft.pradeepservices.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GalleryActivity extends AppCompatActivity {

    private List<Item> items = new ArrayList<>();
    private MyPlansAdapter myPlansAdapter;
    private ActivityGalleryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String lang = PreferenceUtils.getString("lang", GalleryActivity.this);
        if (lang.matches("hindi")) {
            Locale locale = new Locale("hi");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
        binding = ActivityGalleryBinding.inflate(getLayoutInflater());
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
        binding.recyclerView.setLayoutManager(new GridLayoutManager(GalleryActivity.this, 2));
        myPlansAdapter = new MyPlansAdapter(GalleryActivity.this, items);
        binding.recyclerView.setAdapter(myPlansAdapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("gallery").orderBy("id").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            items.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                items.add(document.toObject(Item.class));
                            }
                            myPlansAdapter.notifyDataSetChanged();

                        } else {

                        }
                    }

                });
    }
}