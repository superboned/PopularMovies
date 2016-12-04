package com.example.android.popularmovies;

import android.util.Log;

/**
 * Created by user on 11/30/2016.
 */

public class MovieItem {
    private String mTitle;
    private String mOverview;
    private String mRating;
    private String mDate;
    private int mImageId;

    public MovieItem(String Title, String Overview, String Rating, String Date, int ImageId) {
        mTitle = Title;
        mOverview = Overview;
        mRating = Rating;
        mDate = Date;
        mImageId = ImageId;
    }

    public String getTitle() { return mTitle; }
    public String getOverview() { return mOverview; }
    public String getRating() { return mRating; }
    public String getDate() { return mDate; }
    public int getImageId() { return mImageId; }
}

