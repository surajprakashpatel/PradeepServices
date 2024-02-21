package com.srsoft.pradeepservices.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.srsoft.pradeepservices.R;
import com.srsoft.pradeepservices.databinding.ActivityLanguageSelectorBinding;
import com.srsoft.pradeepservices.utils.PreferenceUtils;

import java.util.Locale;

public class LanguageSelectorActivity extends AppCompatActivity {

    private ActivityLanguageSelectorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityLanguageSelectorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
    }

    private void initialization(){

        binding.english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PreferenceUtils.setString("lang","english",getApplicationContext());
                Intent intent = new Intent(LanguageSelectorActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        binding.hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceUtils.setString("lang","hindi",getApplicationContext());
                Intent intent = new Intent(LanguageSelectorActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
}