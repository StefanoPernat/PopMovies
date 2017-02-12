package com.github.stefanopernat.popmovies.collections;

import com.github.stefanopernat.popmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a set or list of movies, this will be a result of the MovieDb API call
 * Created by stefanopernat on 12/02/17.
 */

public class MovieCollection {
    private List<Movie> mMovieList;

    public MovieCollection() {}

    public MovieCollection(List<Movie> movies){
        mMovieList = new ArrayList<>();
        mMovieList.addAll(movies);
    }

    public List<Movie> getMovieList() {
        List<Movie> movies;

        if (mMovieList == null){
            movies = new ArrayList<>();
        } else {
            movies = new ArrayList<>();
            movies.addAll(mMovieList);
        }

        return movies;
    }

    public void setMovieList(List<Movie> movieList) {
       if (movieList != null){
           mMovieList.addAll(movieList);
       }
    }
}
