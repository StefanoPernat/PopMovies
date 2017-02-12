package com.github.stefanopernat.popmovies.util;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by stefanopernat on 12/02/17.
 * This is an utility class that contains constants or methods useful for the entire project
 */

public class Util {

    /*
        CONSTANTS  --------------------------------------------------------------------------------
    */

    private static final String BASE_MOVIE_URL = "https://api.themoviedb.org/3/movie/";

    private static final String API_QUERY = "api_key";
    private static final String LANGUAGE_QUERY = "language";
    private static final String PAGE_QUERY = "page";

    private static final String LANGUAGE_PARAM = "en-US";



    /*
        METHODS   ---------------------------------------------------------------------------------
    */

    public static URL buildMovieUrl(Context context, int apiKey, String sortOrder, int page){
        Uri movieUri =
            Uri.parse(BASE_MOVIE_URL).buildUpon()
                    .appendPath(sortOrder)
                    .appendQueryParameter(API_QUERY, context.getString(apiKey))
                    .appendQueryParameter(LANGUAGE_QUERY, LANGUAGE_PARAM)
                    .appendQueryParameter(PAGE_QUERY, String.valueOf(page)).build();

        URL movieUrl = null;
        try {
            movieUrl = new URL(movieUri.toString());
        } catch (MalformedURLException ex) {
            Log.e("buildMovieUrl", "Error building movieUrl", ex);
        }

        return movieUrl;
    }

    private Util(){}
}
