package com.github.stefanopernat.popmovies.runnable;

import android.content.Context;

import com.github.stefanopernat.popmovies.util.Util;

import java.net.URL;

/**
 * This is the class that perform the call to the MovieDbAPI
 * Created by stefanopernat on 12/02/17.
 */

public class MoviesRunnable implements Runnable {

    private int mStartPage = 1;
    private int mLastPage;
    private Context mContext;

    public MoviesRunnable(Context context, int lastPage){
        mContext = context;
        mLastPage = lastPage;
    }

    @Override
    public void run() {

    }
}
