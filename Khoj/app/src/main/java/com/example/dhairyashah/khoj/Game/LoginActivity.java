package com.example.dhairyashah.khoj.Game;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dhairyashah.khoj.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class LoginActivity extends AppCompatActivity {
    /*EditText name;
    Button login;
    TextInputLayout textInputLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        login=findViewById(R.id.btn_signup);
        name=findViewById(R.id.input_name);
        textInputLayout=findViewById(R.id.textinput);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = name.getText().toString();
                if(!str.isEmpty())
                    startActivity(new Intent(LoginActivity.this,LevelSelect.class));
                else
                    textInputLayout.setError("Kamal karte ho");
            }
        });
    }*/

    private EditText e;
    Button btn;
    ImageView img,bg;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        img=(ImageView)findViewById(R.id.i1);
        bg =(ImageView)findViewById(R.id.login_bg);
        //relativeLayout=(RelativeLayout)findViewById(R.id.start_page);
        /*Picasso.with(this)
                .load(R.drawable.reducing)
                .fit()
                .centerCrop()
                .into(bg);*/
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.listener(new Picasso.Listener()
        {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                Log.d("dha","dhairya");

                exception.printStackTrace();
            }
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        builder.build().load(R.drawable.reducing).centerCrop().fit().into(bg);
        /*final ImageView im = new ImageView(this);
        Picasso.with(this)
                .load("https://static.pexels.com/photos/177809/pexels-photo-177809.jpeg")
                .fit()
                .centerCrop()
                .into(im, new Callback() {
                    @Override
                    public void onSuccess() {

                        relativeLayout.setBackgroundDrawable(im.getDrawable());
                    }

                    @Override
                    public void onError() {

                    }
                });*/
        //Animation shake= AnimationUtils.loadAnimation(this,R.anim.shaking);
        //img.startAnimation(shake);
        addListenerOnPlay();
    }

    public void addListenerOnPlay()
    {
        e=(EditText)findViewById(R.id.editText);
        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e.getText().toString().replaceAll(" ","").length()==0)
                {
                    Toast.makeText(LoginActivity.this,"Please enter name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent=new Intent(LoginActivity.this,CluesActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
