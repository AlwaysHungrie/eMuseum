package com.onlylemi.mapview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.onlylemi.mapview.library.MapView;
import com.onlylemi.mapview.library.MapViewListener;
import com.onlylemi.mapview.library.layer.MarkLayer;

import java.io.IOException;
import java.util.List;

public class MarkLayerTestActivity extends AppCompatActivity {

    private MapView mapView;
    private MarkLayer markLayer;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_layer_test);
        onclicklistener();


        mapView = (MapView) findViewById(R.id.mapview);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getAssets().open("map.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapView.loadMap(bitmap);
        mapView.setMapViewListener(new MapViewListener() {
            @Override
            public void onMapLoadSuccess() {
                List<PointF> marks = TestData.getMarks();
                final List<String> marksName = TestData.getMarksName();
                markLayer = new MarkLayer(mapView, marks, marksName);
                final int[] mArray = new int[13];
                mArray[0] = R.drawable.reception;
                mArray[1] = R.drawable.key_gallery;
                mArray[2] = R.drawable.coomaraswamy_hall;
                mArray[3] = R.drawable.office;
                mArray[4] = R.drawable.pre_and_proto_history_gallery;
                mArray[5] = R.drawable.sculpture_gallery;
                mArray[6] = R.drawable.chinese_and_japanese;
                mArray[7] = R.drawable.chinese_and_japanese;
                mArray[8] = R.drawable.nh_collection;
                mArray[9] = R.drawable.office;
                mArray[10] = R.drawable.nh_collection;
                mArray[11] = R.drawable.pre_and_proto_history_gallery;
                mArray[12] = R.drawable.office;

                markLayer.setMarkIsClickListener(new MarkLayer.MarkIsClickListener() {
                    @Override
                    public void markIsClick(int num) {
                        Toast.makeText(getApplicationContext(), marksName.get(num) + " is " +
                                "selected", Toast.LENGTH_SHORT).show();

                        AlertDialog.Builder alertadd = new AlertDialog.Builder(MarkLayerTestActivity.this);
                         LayoutInflater factory = LayoutInflater.from(MarkLayerTestActivity.this);
                         final View view = factory.inflate(R.layout.dialog_main,null);

                         ImageView image = (ImageView)view.findViewById(R.id.imageView);
                         image.setImageResource(mArray[num]);
                         alertadd.setView(view);
                         alertadd.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                             }
                         });
                         alertadd.show();
                    }
                });
                mapView.addLayer(markLayer);
                mapView.refresh();
            }

            @Override
            public void onMapLoadFail() {

            }

        });
    }
    public void onclicklistener()
    {
        btn =(Button)findViewById(R.id.Button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MarkLayerTestActivity.this,FirstFloor.class);
                startActivity(i);
            }
        });
    }

}
