package com.srsoft.pradeepservices.ui.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.srsoft.pradeepservices.adapter.YourPoliciesAdapter;
import com.srsoft.pradeepservices.databinding.ActivityCustomerServiceBinding;
import com.srsoft.pradeepservices.modals.MyPolicies;
import com.srsoft.pradeepservices.utils.PreferenceUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomerServiceActivity extends AppCompatActivity {

    List<MyPolicies> items = new ArrayList<>();

    Date givenDate = null;
    List<String> docId = new ArrayList<>();
    YourPoliciesAdapter adapter;
    private ActivityCustomerServiceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String lang = PreferenceUtils.getString("lang", CustomerServiceActivity.this);
        if (lang.matches("hindi")) {
            Locale locale = new Locale("hi");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
        binding = ActivityCustomerServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
    }


    private void initialization() {

        binding.btnBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(CustomerServiceActivity.this));
        adapter = new YourPoliciesAdapter(CustomerServiceActivity.this, items,docId);
        binding.recyclerView.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("user").document(user.getUid()).collection("policies")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            items.clear();
                            docId.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                items.add(document.toObject(MyPolicies.class));
                                docId.add(document.getId());
                                String dateString = document.getString("nextDueDate");
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                try {
                                    givenDate = sdf.parse(dateString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (givenDate != null) {
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(givenDate);
                                    Calendar currentCalendar = Calendar.getInstance();

                                    if (calendar.before(currentCalendar)) {
                                        // Given date is in the past, add one month
                                        if(document.getString("premiumFrequency").matches("Monthly")){
                                            calendar.add(Calendar.MONTH, 1);
                                        }
                                        if(document.getString("premiumFrequency").matches("Quarterly")){
                                            calendar.add(Calendar.MONTH, 3);
                                        } if(document.getString("premiumFrequency").matches("Half Yearly")){
                                            calendar.add(Calendar.MONTH, 6);
                                        }
                                        if(document.getString("premiumFrequency").matches("Yearly")){
                                            calendar.add(Calendar.MONTH, 12);
                                        }
                                        givenDate = calendar.getTime();
                                        db.collection("user").document(user.getUid())
                                                .collection("policies").document(document.getId()
                                                ).update("nextDueDate", sdf.format(givenDate));

                                    } else {

                                    }

                                }
                            }
                            adapter.notifyDataSetChanged();

                        } else {

                        }
                    }
                });
        binding.addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerServiceActivity.this, AddPolicyActivity.class);
                startActivity(intent);
            }

        });


    }
}