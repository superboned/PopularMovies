package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.list;

/**
 * Created by user on 11/30/2016.
 */

public class MovieItemAdapter extends ArrayAdapter<MovieItem> {

    public MovieItemAdapter(Context context, int resource) {
        super(context, resource);
    }

    public MovieItemAdapter(Context context, int resource, ArrayList<MovieItem> movieItems) {
        super(context, resource, movieItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_item, null);
        }

        // get the current movie
        MovieItem currentMovie = getItem(position);

        // set title text
        TextView titleText = (TextView) v.findViewById(R.id.MovieTitle);
        String title = currentMovie.getTitle();
        titleText.setText(title);

        // set title text
        TextView overviewText = (TextView) v.findViewById(R.id.MovieOverview);
        String overview = currentMovie.getOverview();
        overviewText.setText(overview);

        // set title text
        TextView ratingText = (TextView) v.findViewById(R.id.MovieRating);
        String rating = currentMovie.getRating();
        ratingText.setText(rating);

        // set title text
        TextView dateText = (TextView) v.findViewById(R.id.MovieDate);
        String date = currentMovie.getDate();
        dateText.setText(date);

        ImageView movieImage = (ImageView) v.findViewById(R.id.MovieImage);
        int imageId = currentMovie.getImageId();
        movieImage.setImageResource(imageId);

        return v;
    }
}
