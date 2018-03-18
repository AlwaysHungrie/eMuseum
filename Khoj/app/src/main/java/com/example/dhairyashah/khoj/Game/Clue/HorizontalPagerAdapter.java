package com.example.dhairyashah.khoj.Game.Clue;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhairyashah.khoj.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.example.dhairyashah.khoj.Game.Clue.Utils.setupItem;

/**
 * Created by Dhairya Shah on 28-02-2018.
 */
public class HorizontalPagerAdapter extends PagerAdapter {

    private static final String COLORS_FILE_NAME = "Colors.json";
    private static List<ClueWrapper> sClueList = new ArrayList<>();
    private static int[] completed = new int[17];
    private static String[] files = new String[17];

    public Utils.LibraryObject[] LIBRARIES = new Utils.LibraryObject[]{
            /*new Utils.LibraryObject(

                    "Clue 1"
            ),
            new Utils.LibraryObject(

                    "Clue 2"
            ),
            new Utils.LibraryObject(

                    "Clue 3"
            ),
            new Utils.LibraryObject(
                    "Clue 4"
            ),
            new Utils.LibraryObject(
                    "Clue 5"
            ),
            new Utils.LibraryObject(
                    "Clue 6"
            )*/
    };

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private boolean mIsTwoWay;

    public HorizontalPagerAdapter(final Context context, final boolean isTwoWay,String[] file) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIsTwoWay = isTwoWay;
        files = file;
        initLib(initClueList(mContext));
    }

    @Override
    public int getCount() {
        return mIsTwoWay ? 6 : LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        {
            view = mLayoutInflater.inflate(R.layout.item, container, false);
            setupItem(view, LIBRARIES[position],files[position]);


        }
        //if(completed[position]==1)
        //view.setAlpha(0.6f);
        container.addView(view);
        return view;
    }



    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }

    private  List<Utils.LibraryObject> initClueList(Context context) {

        if (sClueList.isEmpty()) {
            String jsonString = loadJson(context);
            sClueList = deserializeColors(jsonString);
        }
        List<Utils.LibraryObject> list = new LinkedList<>( Arrays.asList(LIBRARIES));
        for (ClueWrapper clue : sClueList) {
            list.add(new Utils.LibraryObject(clue.getTitle().toString()));
        }
        return list;
    }
    private void initLib(List<Utils.LibraryObject> list){
        LIBRARIES = new Utils.LibraryObject[list.size()];

        // ArrayList to Array Conversion
        for (int i =0; i < list.size(); i++)
            LIBRARIES[i] = list.get(i);

    }

    private static String loadJson(Context context) {

        String jsonString;

        try {
            InputStream is = context.getAssets().open(COLORS_FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonString;
    }

    private static List<ClueWrapper> deserializeColors(String jsonString) {

        Gson gson = new Gson();

        Type collectionType = new TypeToken<List<ClueWrapper>>() {
        }.getType();
        return gson.fromJson(jsonString, collectionType);
    }
    public void setCompleted(int complete,String filename){
        completed[complete]=1;
        files[complete]=filename;
    }
}
