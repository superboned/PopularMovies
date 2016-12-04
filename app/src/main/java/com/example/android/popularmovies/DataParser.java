package com.example.android.popularmovies;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 12/1/2016.
 */

public class DataParser {

    private String mImagePath;
    private String mTitle;
    private String mOverview;
    private String mRating;
    private String mDate;
    public static ArrayList<MovieItem> movieList  = new ArrayList<MovieItem>();

    public void DataParser() {}

    public void readJSON(String response)  {

        try {
            //create the json object
            JSONObject obj = new JSONObject(response);
            JSONArray results = obj.getJSONArray("results");


            //For every movie in the response do....
            for (int i = 0; i < results.length(); i++ ) {

                JSONObject movie = results.getJSONObject(i);
                mImagePath = movie.getString("poster_path");
                mTitle = movie.getString("title");
                mOverview = movie.getString("overview");
                mDate = movie.getString("release_date");
                mRating = movie.getString("vote_average");

                movieList.add(new MovieItem(mTitle, mOverview, mRating, mDate, R.drawable.manatee2));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
