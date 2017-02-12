package com.github.stefanopernat.popmovies.model;

/**
 * Class that represent a single Movie
 * Created by stefanopernat on 12/02/17.
 */

public class Movie {
    private String mTitle;
    private String mReleaseDate;
    private String mTumbnail;
    private String mBackdrop;
    private double mVoteAverage;
    private String mPlot;

    public Movie(){}

    public Movie(String title, String releaseDate, String tumbnail,
                            String backdrop, double voteAverage, String plot){

        mTitle = title;
        mReleaseDate = releaseDate;
        mTumbnail = tumbnail;
        mBackdrop = backdrop;
        mVoteAverage = voteAverage;
        mPlot = plot;
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
}
