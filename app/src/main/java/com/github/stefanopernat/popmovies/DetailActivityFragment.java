package com.github.stefanopernat.popmovies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.stefanopernat.popmovies.model.Movie;
import com.github.stefanopernat.popmovies.util.Util;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent fromIntent = getActivity().getIntent();

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        if (fromIntent != null && fromIntent.hasExtra(Util.MOVIE_IDENTIFIER)){
            Movie movie = fromIntent.getParcelableExtra(Util.MOVIE_IDENTIFIER);

            getActivity().setTitle(movie.getTitle());
        }

        return view;
    }
}
