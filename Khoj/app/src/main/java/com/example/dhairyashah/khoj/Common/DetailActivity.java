package com.example.dhairyashah.khoj.Common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.dhairyashah.khoj.Game.CluesActivity;
import com.example.dhairyashah.khoj.MainActivity;
import com.example.dhairyashah.khoj.R;
import com.example.dhairyashah.khoj.Wiki.wiki_MainActivity;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dhairya Shah on 17-03-2018.
 */

public class DetailActivity extends AppCompatActivity {

    KenBurnsView museum_image;
    TextView museum_title,museum_summary;
    int idq,ida;
    String f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);

        museum_image = (KenBurnsView)findViewById(R.id.museum_image);
        museum_title = (TextView)findViewById(R.id.museum_title);
        museum_summary = (TextView)findViewById(R.id.museum_summary);

        idq = getIntent().getIntExtra("id",-1);

        if(getIntent()!=null){
            String museum_url = getIntent().getStringExtra("code");
            if(!museum_url.isEmpty())
                loadMuseumDetail(museum_url);
        }
    }

    private void loadMuseumDetail(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject student = response.getJSONObject(i);

                                // Get the artifact information, the fields that I was telling you about go inside this
// Right now our server will give id, title,period,religion,dynasty,type ,short description,long description
                                String id = student.getString("ID");//Note: parameter of getString is case sensitive
                                String title = student.getString("Title");
                                String info = student.getString("Long_Description");
                                
                                if(info.equals("null"))info = student.getString("Short_Description");
                                
                                String filename = student.getString("Filename");
                                ida = (int)Float.parseFloat(id);
                                f = filename;
                                // Display the formatted json data in text view;
                                museum_title.setText(title);
                                museum_summary.setText(info);
                                Picasso.with(getBaseContext()).load("http://dhairyashah.000webhostapp.com/"+filename).into(museum_image);

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Toast.makeText(DetailActivity.this,error.toString(),Toast.LENGTH_LONG).show();

                    }
                }
        );
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);
    }
    public void onBackPressed()
    {
        //do whatever you want the 'Back' button to do
        //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
        Log.d("dha",idq+"");
        if(idq!=-1)
        this.startActivity(new Intent(DetailActivity.this,CluesActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtra("idq",idq).putExtra("ida",ida).putExtra("filename",f));
        else
            this.startActivity(new Intent(DetailActivity.this,wiki_MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        return;
    }

}
