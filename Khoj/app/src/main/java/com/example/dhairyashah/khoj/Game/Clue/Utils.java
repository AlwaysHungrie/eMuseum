package com.example.dhairyashah.khoj.Game.Clue;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dhairyashah.khoj.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Dhairya Shah on 28-02-2018.
 */

public class Utils {

    public static void setupItem(final View view, final LibraryObject libraryObject,String filename) {
        final TextView txt = (TextView) view.findViewById(R.id.txt_item);
        final ImageView img = (ImageView)view.findViewById(R.id.cardviewimage);
        if(filename==null || filename.isEmpty())txt.setText(libraryObject.getTitle());
        else {
            Picasso.with(view.getContext()).load("http://dhairyashah.000webhostapp.com/" + filename).into(img);
            img.setVisibility(View.VISIBLE);
        }

    }

    public static class LibraryObject {

        private String mTitle;
        private int mRes;

        public LibraryObject( final String title) {

            mTitle = title;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(final String title) {
            mTitle = title;
        }

        public int getRes() {
            return mRes;
        }

        public void setRes(final int res) {
            mRes = res;
        }
    }
}