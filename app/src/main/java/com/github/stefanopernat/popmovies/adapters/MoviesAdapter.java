package com.github.stefanopernat.popmovies.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.stefanopernat.popmovies.R;
import com.github.stefanopernat.popmovies.model.Movie;
import com.github.stefanopernat.popmovies.util.Util;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by stefanopernat on 14/02/17.
 * Custom Adapter for recyclerView
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{
    private final String TAG = MoviesAdapter.class.getSimpleName();

    private List<Movie> mMovies;
    private Context mContext;
    private MoviesAdapterOnClickHandler mOnClickHandler;

    public interface MoviesAdapterOnClickHandler {
        public void onClick(Movie movie);
    }

    public MoviesAdapter(MoviesAdapterOnClickHandler clickHandler){ mOnClickHandler = clickHandler; }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mMovieTumbnail;

        public MovieViewHolder(View view){
            super(view);
            mMovieTumbnail = (ImageView) view.findViewById(R.id.movie_tumbnail);
            mMovieTumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    mOnClickHandler.onClick(mMovies.get(position));
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (mMovies == null){
            return 0;
        }

        return mMovies.size();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder moviesAdapterViewHolder, int position) {

        Uri movieTumbnailUri = Uri.parse(Util.BASE_IMAGE_URL).buildUpon()
                                  .appendPath(Util.IMAGE_WIDTH_SEGMENT)
                                  .appendPath(mMovies.get(position).getTumbnail()).build();
        Log.d(TAG, movieTumbnailUri.toString());

        Picasso.with(mContext)
                .load(movieTumbnailUri)
                .into(moviesAdapterViewHolder.mMovieTumbnail);

        moviesAdapterViewHolder.mMovieTumbnail.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }
}
