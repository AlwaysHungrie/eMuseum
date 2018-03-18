package com.example.dhairyashah.khoj.Wiki;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.arlib.floatingsearchview.util.Util;
import com.example.dhairyashah.khoj.Common.DetailActivity;
import com.example.dhairyashah.khoj.Common.MySingleton;
import com.example.dhairyashah.khoj.R;
import com.example.dhairyashah.khoj.Wiki.data.ColorWrapper;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhairya Shah on 22-02-2018.
 */

public class SearchResultsListAdapter extends RecyclerView.Adapter<SearchResultsListAdapter.ViewHolder> {

    private List<ColorWrapper> mDataSet = new ArrayList<>();
    private Context context;
    private int mLastAnimatedItemPosition = -1;
    private int id;
    public interface OnItemClickListener{
        void onClick(ColorWrapper colorWrapper);
    }

    private OnItemClickListener mItemsOnClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mColorName;
        public final TextView mColorValue;
        public final View mTextContainer;
        public final Context mContext;
        public final ImageView sheona;
        private int id;
        public ViewHolder(View view, final Context context) {
            super(view);
            mColorName = (TextView) view.findViewById(R.id.color_name);
            mColorValue = (TextView) view.findViewById(R.id.color_value);
            mTextContainer = view.findViewById(R.id.text_container);
            sheona = (ImageView)view.findViewById(R.id.sheona);

            mContext = context;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("dha",mColorName.getText().toString());
                    context.startActivity(new Intent(context,DetailActivity.class).putExtra("code","http://dhairyashah.000webhostapp.com/banana.php?id="+mColorValue.getText().toString()));
                }
            });
        }

    }

    public void swapData(List<ColorWrapper> mNewDataSet) {
        mDataSet = mNewDataSet;
        notifyDataSetChanged();
    }

    public void setItemsOnClickListener(OnItemClickListener onClickListener){
        this.mItemsOnClickListener = onClickListener;
    }
    public SearchResultsListAdapter(Context c){this.context = c;}

    @Override
    public SearchResultsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_results_list_item, parent, false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(final SearchResultsListAdapter.ViewHolder holder, final int position) {

        ColorWrapper colorSuggestion = mDataSet.get(position);
        holder.mColorName.setText(colorSuggestion.getTitle());
        holder.mColorValue.setText(colorSuggestion.getID());
        id = (int)(Float.parseFloat(colorSuggestion.getID()));

        Picasso.with(holder.mContext).load("http://dhairyashah.000webhostapp.com/"+id).into(holder.sheona);

        //int color = Color.parseColor(colorSuggestion.getHex());
        //holder.mColorName.setTextColor(color);
        //holder.mColorValue.setTextColor(color);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                "http://dhairyashah.000webhostapp.com/banana.php?id="+id,
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
                                String filename = student.getString("Filename");
                                Picasso.with(holder.mContext).load("http://dhairyashah.000webhostapp.com/"+filename).into(holder.sheona);

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
                        Toast.makeText(holder.mContext,error.toString(),Toast.LENGTH_LONG).show();

                    }
                }
        );
        MySingleton.getInstance(holder.mContext).addToRequestQueue(jsonArrayRequest);



/*
        //if(mItemsOnClickListener != null){
            //Log.d("dha",id+"");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d("dha",mDataSet.get(position).toString());
                            mItemsOnClickListener.onClick(mDataSet.get(position));
                            //context.startActivity(new Intent(context,DetailActivity.class).putExtra("code",id));
                        }
                    });
                }
            });
        //}*/
        if(mLastAnimatedItemPosition < position){
            animateItem(holder.itemView);
            mLastAnimatedItemPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private void animateItem(View view) {
        view.setTranslationY(Util.getScreenHeight((Activity) view.getContext()));
        view.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .start();
    }
}