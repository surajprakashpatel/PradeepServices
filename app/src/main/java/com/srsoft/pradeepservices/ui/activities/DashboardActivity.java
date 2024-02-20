package com.srsoft.pradeepservices.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.srsoft.pradeepservices.R;
import com.srsoft.pradeepservices.adapter.ServicesAdapater;
import com.srsoft.pradeepservices.databinding.ActivityDashboardBinding;
import com.srsoft.pradeepservices.modals.Services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    private List<Services> services = new ArrayList<>();

    private ServicesAdapater servicesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
    }
    private void initialization() {


        int currentHour = Calendar.getInstance().get(Calendar.HOUR);
        if(currentHour < 13 && currentHour < 4) binding.textView.setText("Good afternoon!"); else binding.textView.setText("Good morning!");
        if(currentHour > 17 )binding.textView.setText("Good evening!");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        binding.sliderShimmer.startShimmer();

        Services service1 = new Services("About us",R.drawable.about);
        Services service2 = new Services("Our Special Plans",R.drawable.myplans);
        Services service3 = new Services("Gallery",R.drawable.gallery);
        Services service4 = new Services("Plans and Policies",R.drawable.plansandpolicies);
        Services service5 = new Services("Enquiry & Feedback",R.drawable.enquiry_feedback);
        Services service6 = new Services("Learning Material",R.drawable.learning_material);
        Services service7 = new Services("Important Links",R.drawable.legal_document);
        services.add(service1);
        services.add(service2);
        services.add(service3);
        services.add(service4);
        services.add(service5);
        services.add(service6);
        services.add(service7);

        GridLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 3);
        binding.serviceRecycler.setLayoutManager(layoutManager);
        servicesAdapter = new ServicesAdapater(DashboardActivity.this, services);

        getBanner();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.gamesShimmer.hideShimmer();
                binding.gamesShimmer.setVisibility(View.GONE);
                binding.serviceRecycler.setAdapter(servicesAdapter);
            }
        },2000);

    }

    private void getBanner() {

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("utils").document("bannerImg").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String imageUrl = document.getString("link");
                                Glide.with(DashboardActivity.this)
                                        .load(imageUrl)
                                        .into(binding.slidePager);
                                binding.sliderShimmer.hideShimmer();

                            } else {

                            }
                        } else {

                        }
                    }
                });

    }

}