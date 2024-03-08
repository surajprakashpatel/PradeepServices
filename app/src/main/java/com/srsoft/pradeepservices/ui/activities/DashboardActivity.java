package com.srsoft.pradeepservices.ui.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.srsoft.pradeepservices.R;
import com.srsoft.pradeepservices.adapter.ServicesAdapater;
import com.srsoft.pradeepservices.adapter.SliderAdapter;
import com.srsoft.pradeepservices.databinding.ActivityDashboardBinding;
import com.srsoft.pradeepservices.modals.Advertisement;
import com.srsoft.pradeepservices.modals.Services;
import com.srsoft.pradeepservices.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    private List<Services> services = new ArrayList<>();

    private List<Advertisement> sliderList = new ArrayList<>();

    SliderAdapter sliderAdapter;

    private int pos = 0;
    private ServicesAdapater servicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
    }

    private void initialization() {

        binding.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.switchButton.isChecked()) {
                    PreferenceUtils.setString("lang", "english", getApplicationContext());
                    setLocale("en");
                } else {
                    PreferenceUtils.setString("lang", "hindi", getApplicationContext());
                    setLocale("hi");
                }
            }
        });

        binding.textView2.setText(R.string.nice_to_see_you_again);
        setSlider();
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (currentHour > 4) binding.textView.setText(R.string.good_morning);
        if (currentHour > 11) binding.textView.setText(R.string.good_afternoon);
        if (currentHour > 15) binding.textView.setText(R.string.good_evening);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        binding.sliderShimmer.startShimmer();

        Services service1 = new Services(getString(R.string.about_us), R.drawable.about);
        Services service2 = new Services(getString(R.string.our_special_plans), R.drawable.myplans);
        Services service3 = new Services(getString(R.string.gallery), R.drawable.gallery);
        Services service4 = new Services(getString(R.string.plans_and_policies), R.drawable.plansandpolicies);
        Services service5 = new Services(getString(R.string.enquiry_feedback), R.drawable.enquiry_feedback);
        Services service6 = new Services(getString(R.string.learning_material), R.drawable.learning_material);
        Services service7 = new Services(getString(R.string.important_links), R.drawable.legal_document);
        Services service8 = new Services(getString(R.string.your_policies), R.drawable.mypolicies);
        services.add(service1);
        services.add(service2);
        services.add(service8);
        services.add(service3);
        services.add(service4);
        services.add(service5);
        services.add(service6);
        services.add(service7);

        GridLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 3);
        binding.serviceRecycler.setLayoutManager(layoutManager);
        servicesAdapter = new ServicesAdapater(DashboardActivity.this, services);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.gamesShimmer.hideShimmer();
                binding.gamesShimmer.setVisibility(View.GONE);
                binding.serviceRecycler.setAdapter(servicesAdapter);
            }
        }, 2000);

    }


    private void setSlider() {

        getBanner();
        Handler sliderHandler = new Handler();
        sliderAdapter = new SliderAdapter(DashboardActivity.this, sliderList, binding.slidePager);
        binding.slidePager.setAdapter(sliderAdapter);
        binding.dotsIndicator.setViewPager2(binding.slidePager);

        binding.slidePager.setClipToPadding(false);
        binding.slidePager.setClipChildren(false);
        binding.slidePager.setOffscreenPageLimit(3);
        binding.slidePager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(15));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
               /* float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);*/
            }
        });
        binding.slidePager.setPageTransformer(compositePageTransformer);

        binding.slidePager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 5500); // slide duration 2 seconds
            }
        });
    }

    private void getBanner() {

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("utils").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            sliderList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                sliderList.add(document.toObject(Advertisement.class));
                            }
                            sliderAdapter.notifyDataSetChanged();

                            binding.sliderShimmer.hideShimmer();
                            binding.sliderShimmer.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(DashboardActivity.this, "Unable to Load", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {

            if (binding.slidePager.getCurrentItem() == (sliderList.size() - 1)) {
                pos = 0;
            } else {
                pos = binding.slidePager.getCurrentItem() + 1;
            }

            binding.slidePager.setCurrentItem(pos);
        }
    };

    private void setLocale(String languageCode) {
        Locale currentLocale = getResources().getConfiguration().locale;
        if (!currentLocale.getLanguage().equals(languageCode)) {

            Locale locale = new Locale(languageCode);
            Locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
            recreate();
        }
    }
}