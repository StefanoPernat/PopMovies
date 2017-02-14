package com.github.stefanopernat.popmovies.runnable;

import android.content.Context;
import android.util.Log;

import com.github.stefanopernat.popmovies.R;
import com.github.stefanopernat.popmovies.model.Movie;
import com.github.stefanopernat.popmovies.util.Util;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * This is the class that perform the call to the MovieDbAPI
 * Created by stefanopernat on 12/02/17.
 */

public class MoviesRunnable implements Runnable {

    private final String TAG = MoviesRunnable.class.getSimpleName();

    private int mStartPage = 1;
    private int mLastPage;
    private String mSortOrder;
    private Context mContext;

    public MoviesRunnable(Context context, int lastPage, String sortOrder){
        mContext = context;
        mLastPage = lastPage;
        mSortOrder = sortOrder;
    }

    @Override
    public void run() {
        ArrayList<Movie> movies = new ArrayList<>();
        int currentPage = mStartPage;

        do {
            Log.d(TAG, "building the URL for page "+currentPage);

            //build the url
            URL movieDbApiUrl = Util.buildMovieUrl(mContext, R.string.api_key, mSortOrder,
                    currentPage);

            String json = getMoviesJson(movieDbApiUrl);
            if (json != null && !json.equals("")){
                movies.addAll(parseJsonResult(json));
            }
            currentPage++;
        } while (currentPage <= mLastPage);

        EventBus.getDefault().post(movies);
    }

    private String getMoviesJson(URL url){
        try {
            //creation of HTTP connection object
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream is = conn.getInputStream();
            StringBuilder builder = new StringBuilder();

            if (is == null){
                return null;
            }

            // read the results
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
                builder.append("\n");
            }

            // nothing to read
            if (builder.length() == 0){
                return null;
            }

            return builder.toString();

        } catch (IOException ex){
            Log.e(TAG, ex.getMessage(), ex);
            return null;
        }

    }

    private ArrayList<Movie> parseJsonResult(String jsonResult){
        ArrayList<Movie> movies = new ArrayList<>();
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(jsonResult);
            JSONArray jsonMovies = jsonObject.getJSONArray(Util.JSON_MOVIES_ARRAY);

            for (int i=0; i < jsonMovies.length(); i++){
                int id;
                String title, releaseDate, tumbnail, backdrop, plot;
                double voteAverage;

                JSONObject jsonMovie = jsonMovies.getJSONObject(i);

                id = jsonMovie.getInt(Util.JSON_MOVIE_ID);
                title = jsonMovie.getString(Util.JSON_MOVIE_TITLE);
                releaseDate = jsonMovie.getString(Util.JSON_MOVIE_RELEASE_DATE);
                tumbnail = jsonMovie.getString(Util.JSON_MOVIE_TUMBNAIL).replace("/", "");
                backdrop = jsonMovie.getString(Util.JSON_MOVIE_BACKDROP).replace("/", "");
                voteAverage = jsonMovie.getDouble(Util.JSON_MOVIE_VOTE_AVERAGE);
                plot = jsonMovie.getString(Util.JSON_MOVIE_PLOT);

                Movie movie = new Movie(
                        id,
                        title,
                        releaseDate,
                        tumbnail,
                        backdrop,
                        voteAverage,
                        plot
                );

                movies.add(movie);

            }

        } catch (JSONException ex){
            Log.d(TAG, ex.getMessage(), ex);
        }

        return movies;
    }
}
