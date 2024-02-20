package com.srsoft.pradeepservices.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.srsoft.pradeepservices.R;
import com.srsoft.pradeepservices.databinding.ActivityFeedbackBinding;

import java.util.HashMap;
import java.util.Map;

public class FeedbackActivity extends AppCompatActivity {

    private ActivityFeedbackBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityFeedbackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
    }

    private void initialization() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String feedback = binding.etObservation.getText().toString();

        binding.btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> data = new HashMap<>();
                data.put("feedback",feedback);
                data.put("userId", user.getUid());
                FirebaseFirestore database = FirebaseFirestore.getInstance();
                database.collection("feedback").document(user.getUid()).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            new AlertDialog.Builder(FeedbackActivity.this)
                                    .setCancelable(false)
                                    .setMessage("Submitted !")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    })
                                    .show();
                        }else{

                        }
                    }
                });

            }
        });

        binding.btnBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}