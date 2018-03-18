package com.example.dhairyashah.khoj.Wiki.data;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by Dhairya Shah on 22-02-2018.
 */

public class ColorSuggestion implements SearchSuggestion {

    private String mColorName;
    private boolean mIsHistory = false;

    public ColorSuggestion(String suggestion) {
        this.mColorName = suggestion.toLowerCase();
    }

    public ColorSuggestion(Parcel source) {
        this.mColorName = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    @Override
    public String getBody() {
        return mColorName;
    }

    public static final Creator<ColorSuggestion> CREATOR = new Creator<ColorSuggestion>() {
        @Override
        public ColorSuggestion createFromParcel(Parcel in) {
            return new ColorSuggestion(in);
        }

        @Override
        public ColorSuggestion[] newArray(int size) {
            return new ColorSuggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mColorName);
        dest.writeInt(mIsHistory ? 1 : 0);
    }
}
