package com.srsoft.pradeepservices.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.srsoft.pradeepservices.ui.fragments.LoginFragment;
import com.srsoft.pradeepservices.ui.fragments.RegisterFragment;


public class MyFragmentAdapter extends FragmentStateAdapter {


    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new RegisterFragment();
        }
        if (position == 1) {
            return new LoginFragment();
        }

        return new RegisterFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}