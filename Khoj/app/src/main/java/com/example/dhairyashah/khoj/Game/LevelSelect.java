package com.example.dhairyashah.khoj.Game;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.dhairyashah.khoj.Game.Download_Pics.ExternalStorage;
import com.example.dhairyashah.khoj.R;

import java.io.File;

public class LevelSelect extends AppCompatActivity {
        ImageButton imageview[] = new ImageButton[6];
        String filenames[] = {"/image1.jpg",
                "/image2.jpg",
                "/image3.jpg",
                "/image4.jpg",
                "/image5.jpg",
                "/image1.jpg"};
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_level_select);
            getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            //String file = "";
            //File file= new File("Directory browsing/Phone Storage/Android/data/com.example.dhairyashah.museumoftreasures/files/unzipped/");
            File outputDir = ExternalStorage.getSDCacheDir(this, "unzipped");

            //filepaths =new File(outputDir,"image1.jpg") ;

            imageview[0]=(ImageButton)findViewById(R.id.i1);
            imageview[1]=(ImageButton)findViewById(R.id.i2);
            imageview[2]=(ImageButton)findViewById(R.id.i3);
            imageview[3]=(ImageButton)findViewById(R.id.i4);
            imageview[4]=(ImageButton)findViewById(R.id.i5);
            imageview[5]=(ImageButton)findViewById(R.id.i6);
            for(int i=0;i<6;i++)
            {
                Bitmap bitmap = BitmapFactory.decodeFile(outputDir.getPath()+filenames[i]);
                //Picasso.with(this).load(outputDir.getPath()+"/image1.jpg").fit().into(imageview);
                imageview[i].setImageBitmap(bitmap);
            }
            imageview[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),CluesActivity.class));
                }
            });

        }
}
