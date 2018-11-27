package com.example.maksy.mobile_development.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.maksy.mobile_development.R;

public class FragmentNavigation {
    FragmentManager fragmentManager;

    public FragmentNavigation(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void swapFragments(final Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_replace, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
