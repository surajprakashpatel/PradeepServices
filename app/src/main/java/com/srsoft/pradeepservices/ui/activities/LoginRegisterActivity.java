package com.srsoft.pradeepservices.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.srsoft.pradeepservices.R;
import com.srsoft.pradeepservices.adapter.MyFragmentAdapter;
import com.srsoft.pradeepservices.databinding.ActivityLoginRegisterBinding;
import com.srsoft.pradeepservices.utils.PreferenceUtils;

import java.util.Locale;

public class LoginRegisterActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    MyFragmentAdapter adapter;
    private ActivityLoginRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialization();

    }

    private void initialization() {
        String lang = PreferenceUtils.getString("lang",LoginRegisterActivity.this);
        if(lang.matches("hindi")){
            Locale locale = new Locale("hi");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }

        tabLayout = binding.tabLayout;
        viewPager2 = binding.viewPager2;

        tabLayout.addTab(tabLayout.newTab().setText(R.string.register));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.login));

        FragmentManager fragmentManager = getSupportFragmentManager();

        adapter = new MyFragmentAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }
    @Override
    public void onResume() {
        super.onResume();
//        mHandler = new Handler();

    }


    @Override
    public void onPause() {
        super.onPause();
//        mHandler.removeCallbacksAndMessages(null);
    }
}