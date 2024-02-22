package com.srsoft.pradeepservices.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.srsoft.pradeepservices.databinding.FragmentRegisterBinding;
import com.srsoft.pradeepservices.modals.UserModal;
import com.srsoft.pradeepservices.ui.activities.DashboardActivity;


public class RegisterFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FragmentRegisterBinding binding;

    public RegisterFragment() {

    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(getLayoutInflater(), container, false);
        initialization();
        return binding.getRoot();

    }

    private void initialization() {

        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateUser();
            }
        });
    }

    private void validateUser() {

        String name = binding.etName.getText().toString();
        String email = binding.etEmail.getText().toString();
        String phone = binding.etPhone.getText().toString();
        String password = binding.etPassword.getText().toString().toUpperCase();

        if (name.matches("")) {
            binding.etName.setError("Enter your name");
        } else if (email.matches("")) {
            binding.etEmail.setError("Enter your email");
        } else if (phone.matches("")) {
            binding.etPhone.setError("Enter Phone Number");
        } else if (password.matches("")) {
            binding.etPassword.setError("Set Password");
        } else {

            Toast.makeText(getContext(), "Please wait!", Toast.LENGTH_SHORT).show();
            mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        String userId = task.getResult().getUser().getUid();
                        UserModal user = new UserModal(name, phone, email, userId);
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("user").document(userId).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getContext(), "Signing In!", Toast.LENGTH_SHORT).show();
                            }
                        });


                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
                                    Intent intent = new Intent(getContext(), DashboardActivity.class);
                                    startActivity(intent);
                                    getActivity().finishAffinity();
                                }
                            }
                        });


                    } else {

                    }
                }
            });
        }
    }
}