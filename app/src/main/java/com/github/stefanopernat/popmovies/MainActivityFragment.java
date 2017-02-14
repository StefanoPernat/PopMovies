package com.github.stefanopernat.popmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.stefanopernat.popmovies.adapters.MoviesAdapter;
import com.github.stefanopernat.popmovies.model.Movie;
import com.github.stefanopernat.popmovies.runnable.MoviesRunnable;
import com.github.stefanopernat.popmovies.util.Util;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


public class MainActivityFragment extends Fragment implements MoviesAdapter.MoviesAdapterOnClickHandler {
    private final String TAG = MainActivityFragment.class.getSimpleName();

    private RecyclerView mMoviesRecyclerView;
    private MoviesAdapter mMoviesAdapter;

    @Subscribe(threadMode = ThreadMode.MAIN) public void onEvent(ArrayList<Movie> moviesList){
        Log.d(TAG, "movies: "+moviesList.size());

        mMoviesAdapter = new MoviesAdapter(this);
        mMoviesAdapter.setContext(getContext());
        mMoviesAdapter.setMovies(moviesList);

        mMoviesRecyclerView.setAdapter(mMoviesAdapter);
    }

    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_main, container, false);

        mMoviesRecyclerView = (RecyclerView) viewRoot.findViewById(R.id.rv_movie_grid);
        mMoviesRecyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mMoviesRecyclerView.setLayoutManager(layoutManager);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String sortOrder = prefs.getString(Util.SORT_ORDER_PREF_KEY, Util.SORT_ORDER_PREF_DEFAULT);
        Integer pages = Integer.valueOf(prefs.getString(Util.PAGES_PREF_KEY, ""+Util.PAGES_PREF_DEFAULT));

        Runnable runnable = new MoviesRunnable(
                getActivity(),
                pages,
                sortOrder
        );
        new Thread(runnable).start();

        /*Button button = (Button) viewRoot.findViewById(R.id.test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

                String sortOrder = prefs.getString(Util.SORT_ORDER_PREF_KEY, Util.SORT_ORDER_PREF_DEFAULT);
                Integer pages = Integer.valueOf(prefs.getString(Util.PAGES_PREF_KEY, ""+Util.PAGES_PREF_DEFAULT));

                Runnable runnable = new MoviesRunnable(
                        getActivity(),
                        pages,
                        sortOrder
                );
                new Thread(runnable).start();
            }
        });*/

        return viewRoot;
    }

    @Override
    public void onClick(Movie movie) {
        Intent detailIntent = new Intent(getContext(), DetailActivity.class);
        startActivity(detailIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
