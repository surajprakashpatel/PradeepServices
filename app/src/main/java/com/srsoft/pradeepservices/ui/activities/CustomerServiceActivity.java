package com.srsoft.pradeepservices.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.srsoft.pradeepservices.databinding.ActivityCustomerServiceBinding;

public class CustomerServiceActivity extends AppCompatActivity {

    private ActivityCustomerServiceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}