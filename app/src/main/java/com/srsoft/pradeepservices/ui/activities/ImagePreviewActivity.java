package com.srsoft.pradeepservices.ui.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.srsoft.pradeepservices.databinding.ActivityImagePreviewBinding;
import com.srsoft.pradeepservices.utils.PreferenceUtils;

import java.util.Locale;

public class ImagePreviewActivity extends AppCompatActivity {

    private ActivityImagePreviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String lang = PreferenceUtils.getString("lang", ImagePreviewActivity.this);
        if (lang.matches("hindi")) {
            Locale locale = new Locale("hi");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
        binding = ActivityImagePreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
    }

    private void initialization() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        binding.btnBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Glide.with(ImagePreviewActivity.this)
                .load(url)
                .into(binding.imagePreview);

    }
}