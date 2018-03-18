package com.example.dhairyashah.khoj.Wiki.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dhairya Shah on 22-02-2018.
 */
public class ColorWrapper implements Parcelable {

    @SerializedName("ID")
    @Expose
    private String ID;
    @SerializedName("Title")
    @Expose
    private String Title;
    /*@SerializedName("rgb")
    @Expose
    private String rgb;*/

    private ColorWrapper(Parcel in) {
        ID = in.readString();
        Title = in.readString();
        //rgb = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(Title);
        //dest.writeString(rgb);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     *
     * @return
     * The hex
     */
    public String getID() {
        return ID;
    }

    /**
     *
     * @param ID
     * The hex
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *
     * @return
     * The name
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     *
     * The name
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @return
     * The rgb
     */
    /*public String getRgb() {
        return rgb;
    }*/

    /**
     *
     * @param rgb
     * The rgb
     */
   /* public void setRgb(String rgb) {
        this.rgb = rgb;
    }*/

    public static final Creator<ColorWrapper> CREATOR = new Creator<ColorWrapper>() {
        @Override
        public ColorWrapper createFromParcel(Parcel in) {
            return new ColorWrapper(in);
        }

        @Override
        public ColorWrapper[] newArray(int size) {
            return new ColorWrapper[size];
        }
    };
}