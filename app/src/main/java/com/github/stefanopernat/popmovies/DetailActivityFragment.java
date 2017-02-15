package com.github.stefanopernat.popmovies;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.stefanopernat.popmovies.model.Movie;
import com.github.stefanopernat.popmovies.util.Util;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private static final String TAG = DetailActivityFragment.class.getSimpleName();

    TextView mReleaseDateTextView;
    TextView mPlotTextView;
    TextView mVoteAverage;
    ImageView mPosterImage;

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

            //build poster image URI
            Uri posterImageUri = Uri.parse(Util.BASE_IMAGE_URL).buildUpon()
                                    .appendPath(Util.IMAGE_WIDTH_SEGMENT)
                                    .appendPath(movie.getBackdrop()).build();

            //Format the relase date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date releaseDate = null;
            try {
                 releaseDate = sdf.parse(movie.getReleaseDate());
            } catch (ParseException ex){
                Log.e(TAG, ex.getMessage(), ex);
            }

            mReleaseDateTextView = (TextView) view.findViewById(R.id.tv_release_date);
            mReleaseDateTextView.setText(
                    mReleaseDateTextView.getText()+
                            " "+(releaseDate == null ? "" : new SimpleDateFormat("E M Y").format(releaseDate))
            );

            mPosterImage = (ImageView) view.findViewById(R.id.poster_image);
            mPlotTextView = (TextView) view.findViewById(R.id.tv_plot);
            mVoteAverage = (TextView) view.findViewById(R.id.tv_vote_average);

            Picasso.with(getContext())
                    .load(posterImageUri)
                    .into(mPosterImage);

            mPlotTextView.setText(movie.getPlot());
            mVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        }

        return view;
    }
}
