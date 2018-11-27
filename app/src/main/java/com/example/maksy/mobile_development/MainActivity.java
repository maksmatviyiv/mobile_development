package com.example.maksy.mobile_development;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.maksy.mobile_development.favourite.FavouriteFragment;
import com.example.maksy.mobile_development.list.ListFragment;
import com.example.maksy.mobile_development.navigation.FragmentNavigation;

public class MainActivity extends AppCompatActivity {
    FragmentNavigation fragmentNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentNavigation = new FragmentNavigation(getSupportFragmentManager());
        fragmentNavigation.swapFragments(ListFragment.newInstance());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {

            case R.id.home:
                fragment = ListFragment.newInstance();
                break;
            case R.id.favorites:
                fragment = FavouriteFragment.newInstance();
                break;
        }
        fragmentNavigation.swapFragments(fragment);
        return false;
    }

}
