package com.github.stefanopernat.popmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class that represent a single Movie
 * Created by stefanopernat on 12/02/17.
 */

public class Movie implements Parcelable{
    private int mId;
    private String mTitle;
    private String mReleaseDate;
    private String mTumbnail;
    private String mBackdrop;
    private double mVoteAverage;
    private String mPlot;

    public Movie(){}

    public Movie(int id, String title, String releaseDate, String tumbnail,
                            String backdrop, double voteAverage, String plot){

        mId = id;
        mTitle = title;
        mReleaseDate = releaseDate;
        mTumbnail = tumbnail;
        mBackdrop = backdrop;
        mVoteAverage = voteAverage;
        mPlot = plot;
    }

    private Movie(Parcel parcel){
        mId = parcel.readInt();
        mTitle = parcel.readString();
        mReleaseDate = parcel.readString();
        mTumbnail = parcel.readString();
        mBackdrop = parcel.readString();
        mVoteAverage = parcel.readDouble();
        mPlot = parcel.readString();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getTumbnail() {
        return mTumbnail;
    }

    public void setTumbnail(String tumbnail) {
        mTumbnail = tumbnail;
    }

    public String getBackdrop() {
        return mBackdrop;
    }

    public void setBackdrop(String backdrop) {
        mBackdrop = backdrop;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getPlot() {
        return mPlot;
    }

    public void setPlot(String plot) {
        mPlot = plot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeString(mReleaseDate);
        dest.writeString(mTumbnail);
        dest.writeString(mBackdrop);
        dest.writeDouble(mVoteAverage);
        dest.writeString(mPlot);
    }

    public final static Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    }
}
