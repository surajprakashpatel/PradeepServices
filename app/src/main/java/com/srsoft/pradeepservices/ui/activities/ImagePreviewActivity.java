package com.srsoft.pradeepservices.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.srsoft.pradeepservices.R;
import com.srsoft.pradeepservices.databinding.ActivityImagePreviewBinding;

public class ImagePreviewActivity extends AppCompatActivity {

    private ActivityImagePreviewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImagePreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
    }

    private void initialization() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        Glide.with(ImagePreviewActivity.this)
                .load(url)
                .into(binding.imagePreview);
    }
}