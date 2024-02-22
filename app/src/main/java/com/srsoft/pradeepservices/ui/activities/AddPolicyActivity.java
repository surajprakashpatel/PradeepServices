package com.srsoft.pradeepservices.ui.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import com.srsoft.pradeepservices.R;
import com.srsoft.pradeepservices.databinding.ActivityAddPolicyBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddPolicyActivity extends AppCompatActivity {

    private ActivityAddPolicyBinding binding;

    String paymentFrequency = "monthly";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        initialization();
    }

    private void initialization() {

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.paymentFrequency, android.R.layout.simple_spinner_item);
        binding.spinner.setAdapter(spinnerAdapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paymentFrequency = binding.spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        DateEditTextListener dateEditTextListener = new DateEditTextListener(binding.etDOB);
        DateEditTextListener dateEditTextListener1 = new DateEditTextListener(binding.etDateofCommencement);
        DateEditTextListener dateEditTextListener2 = new DateEditTextListener(binding.etLastPremiumDate);
        DateEditTextListener dateEditTextListener3 = new DateEditTextListener(binding.etMaturity);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> data = new HashMap<>();
                data.put("name", binding.etCustomerName.getText().toString());
                data.put("dob", binding.etDOB.getText().toString());
                data.put("dateofCommencement", binding.etDateofCommencement.getText().toString());
                data.put("lastPremiumDate", binding.etLastPremiumDate.getText().toString());
                data.put("maturity", binding.etMaturity.getText().toString());
                data.put("sumAssured", binding.etSumAssured.getText().toString());
                data.put("planName", binding.etPlanName.getText().toString());
                data.put("premiumFrequency", paymentFrequency);
                FirebaseInAppMessaging.getInstance().triggerEvent(paymentFrequency);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                db.collection("user").document(user.getUid()).collection("policies")
                        .add(data).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(AddPolicyActivity.this, "success!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(AddPolicyActivity.this, DashboardActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                }
                            }
                        });
            }
        });
    }

    public class DateEditTextListener implements View.OnFocusChangeListener {

        private final EditText editText;

        public DateEditTextListener(EditText editText) {
            this.editText = editText;
            editText.setOnFocusChangeListener(this);
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                // Open the date picker dialog
                openDatePickerDialog();
            }
        }

        private void openDatePickerDialog() {
            // Get current date
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a date picker dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(editText.getContext(),
                    (view, year1, month1, dayOfMonth) -> {
                        // Set the selected date in the EditText
                        calendar.set(year1, month1, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        editText.setText(dateFormat.format(calendar.getTime()));
                    }, year, month, day);

            // Show the date picker dialog
            datePickerDialog.show();
            FirebaseInAppMessaging.getInstance().triggerEvent(day + "");

        }
    }
}