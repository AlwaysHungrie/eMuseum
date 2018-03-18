package com.example.dhairyashah.khoj.Wiki;

import android.annotation.SuppressLint;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.dhairyashah.khoj.R;

import java.util.List;

public class wiki_MainActivity extends AppCompatActivity
        implements BaseExampleFragment.BaseExampleFragmentCallbacks {

    private final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

        showFragment(new SlidingSearchResultsExampleFragment());
    }

    @Override
    public void onAttachSearchViewToDrawer(FloatingSearchView searchView) {
        searchView.attachNavigationDrawerToMenuButton(mDrawerLayout);
    }


    @Override
    public void onBackPressed() {

        @SuppressLint("RestrictedApi") List fragments = getSupportFragmentManager().getFragments();
        BaseExampleFragment currentFragment = (BaseExampleFragment) fragments.get(fragments.size() - 1);

        if (!currentFragment.onActivityBackPress()) {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (menuItem.getItemId()) {

            case R.id.scrolling_search_bar_example:
                showFragment(new ScrollingSearchExampleFragment());
                return true;
            default:
                return true;
        }
    }
*/
    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }
}
