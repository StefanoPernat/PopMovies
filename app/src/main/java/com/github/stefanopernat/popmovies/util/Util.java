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

    // API ----------------------------------------------------------------------------------------
    private static final String BASE_MOVIE_URL = "https://api.themoviedb.org/3/movie/";

    private static final String API_QUERY = "api_key";
    private static final String LANGUAGE_QUERY = "language";
    private static final String PAGE_QUERY = "page";

    private static final String LANGUAGE_PARAM = "en-US";

    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/";

    public static final String IMAGE_WIDTH_SEGMENT = "w185";

    // Preferences --------------------------------------------------------------------------------
    public static final String SORT_ORDER_PREF_KEY = "movie.settings.sort";
    public static final String SORT_ORDER_PREF_DEFAULT = "popular";

    public static final String PAGES_PREF_KEY = "movie.settings.pages";
    public static final int PAGES_PREF_DEFAULT = 1;

    // JSON ---------------------------------------------------------------------------------------
    public static final String JSON_MOVIES_ARRAY = "results";
    public static final String JSON_MOVIE_ID = "id";
    public static final String JSON_MOVIE_TITLE = "original_title";
    public static final String JSON_MOVIE_RELEASE_DATE = "release_date";
    public static final String JSON_MOVIE_TUMBNAIL = "poster_path";
    public static final String JSON_MOVIE_BACKDROP = "backdrop_path";
    public static final String JSON_MOVIE_VOTE_AVERAGE = "vote_average";
    public static final String JSON_MOVIE_PLOT = "overview";

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
