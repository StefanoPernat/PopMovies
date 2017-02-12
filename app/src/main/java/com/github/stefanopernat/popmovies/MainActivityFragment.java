package com.github.stefanopernat.popmovies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.stefanopernat.popmovies.util.Util;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private final String TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, Util.buildMovieUrl(getContext(), R.string.api_key, "popular", 1).toString());
        return inflater.inflate(R.layout.fragment_main, container, false);

    }
}
