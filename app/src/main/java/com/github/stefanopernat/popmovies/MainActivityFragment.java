package com.github.stefanopernat.popmovies;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.stefanopernat.popmovies.model.Movie;
import com.github.stefanopernat.popmovies.runnable.MoviesRunnable;
import com.github.stefanopernat.popmovies.util.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


public class MainActivityFragment extends Fragment {
    private final String TAG = MainActivityFragment.class.getSimpleName();

    @Subscribe(threadMode = ThreadMode.MAIN) public void onEvent(ArrayList<Movie> moviesList){
        Log.d(TAG, "movies: "+moviesList.size());
    }

    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) viewRoot.findViewById(R.id.test);
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
        });

        return viewRoot;
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
