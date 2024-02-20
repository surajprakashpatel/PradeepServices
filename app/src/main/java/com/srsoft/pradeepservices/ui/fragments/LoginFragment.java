package com.srsoft.pradeepservices.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.srsoft.pradeepservices.R;
import com.srsoft.pradeepservices.databinding.FragmentLoginBinding;
import com.srsoft.pradeepservices.ui.activities.DashboardActivity;

import java.lang.reflect.Field;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    public LoginFragment() {

    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater(), container, false);
        initialization();
        return binding.getRoot();

    }

    private void initialization() {

        binding.tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =binding.etEmail.getText().toString();
                if (email.matches("")) {
                    binding.etEmail.setError("Enter Email");
                }else{
                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getContext(), "Reset password link sent to you email!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateUser();
            }
        });
    }

    private void validateUser() {

        String email =binding.etEmail.getText().toString();
        String password= binding.etPassword.getText().toString().toUpperCase();

       if (email.matches("")) {
            binding.etEmail.setError("Enter Email");
        } else if (password.matches("")) {
            binding.etPassword.setError("Set Password");
        }else{
           Toast.makeText(getContext(), "Please wait!", Toast.LENGTH_SHORT).show();
                       FirebaseAuth mAuth = FirebaseAuth.getInstance();
                       mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                             if(task.isSuccessful()){
                                 Intent intent = new Intent(getContext(),DashboardActivity.class);
                                 startActivity(intent);
                                 getActivity().finishAffinity();
                             }else{
                                 Toast.makeText(getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                             }
                           }
                       });

       }
    }
}