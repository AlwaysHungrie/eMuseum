package com.onlylemi.mapview.Feed;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.design.widget.BottomNavigationView;
import com.onlylemi.mapview.MarkLayerTestActivity;

import com.onlylemi.mapview.R;

public class BottomNav extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        //toolbar
                toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //sets toolbar as the action bar, see last method for further info
        setSupportActionBar(toolbar);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = ItemTwoFragment.newInstance();
                                toolbar.setTitle("Feed");
                                break;
                            case R.id.action_item2:
                                selectedFragment = ItemThreeFragment.newInstance();
                                toolbar.setTitle("CSMVS");
                                break;
                            case R.id.action_item3:
                                selectedFragment = ItemFourFragment.newInstance();
                                toolbar.setTitle("BDL");
                                break;
                            //case R.id.action_item4:
                              //  Intent intent = new Intent("com.onlylemi.mapview.MarkLayerTestActivity");
                                //toolbar.setTitle("Map");
                                //break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ItemTwoFragment.newInstance());
        transaction.commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }
}
