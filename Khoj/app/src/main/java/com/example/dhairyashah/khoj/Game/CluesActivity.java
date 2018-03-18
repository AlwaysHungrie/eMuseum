package com.example.dhairyashah.khoj.Game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.dhairyashah.khoj.Common.ScanActivity;
import com.example.dhairyashah.khoj.Game.Clue.HorizontalPagerAdapter;
import com.example.dhairyashah.khoj.R;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

/**
 * Created by Dhairya Shah on 16-03-2018.
 */

public class CluesActivity extends AppCompatActivity{
    HorizontalPagerAdapter horizontalPagerAdapter;
    private static String[] files = new String[17];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clues);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager
                =(HorizontalInfiniteCycleViewPager)findViewById(R.id.hicvp);
        if(horizontalPagerAdapter==null)
            horizontalPagerAdapter = new HorizontalPagerAdapter(getApplicationContext(),false,files);
        horizontalInfiniteCycleViewPager.setAdapter(horizontalPagerAdapter);

        Button b = (Button)findViewById(R.id.scan_code);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ScanActivity.class);
                Log.d("dha",String.valueOf(horizontalInfiniteCycleViewPager.getRealItem()));
                intent.putExtra("idq",horizontalInfiniteCycleViewPager.getRealItem());
                startActivity(intent);
            }
        });

        int idq = getIntent().getIntExtra("idq",-1);
        int ida = getIntent().getIntExtra("ida",-2);
        String filename = getIntent().getStringExtra("filename");
        Log.d("dha",idq+" "+ida);
        boolean flag = false;
        if(idq == 0 && ida==1
                || idq ==1 && ida==2
                || idq ==2 && ida==3
                || idq ==3 && ida==4
                || idq ==4 && ida==13
                || idq ==5 && ida==15
                || idq ==6 && ida==44
                || idq ==7 && ida==49
                || idq ==8 && ida==73
                || idq ==9 && ida==77){
            flag=true;
            files[idq]=filename;
        }
        if(flag==true)horizontalPagerAdapter.setCompleted(idq,filename);
    }

    private void transparentToolbar() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    public void saveScore(int score){
        SharedPreferences.Editor editor = getSharedPreferences("score", MODE_PRIVATE).edit();
        editor.putString("level", ""+score);
        editor.putInt("level_unlocked", score);
        editor.commit();
    }
    public void getScore(){
        SharedPreferences prefs = getSharedPreferences("score", MODE_PRIVATE);
        String text = prefs.getString("level", "level_no");
        int level_unlocked = prefs.getInt("level_unlocked", 0);
        Toast.makeText(CluesActivity.this, ""+level_unlocked, Toast.LENGTH_SHORT).show();
    }
}

