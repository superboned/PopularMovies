package com.example.android.popularmovies;

import android.net.Uri;
import android.util.Log;

/**
 * Created by user on 12/1/2016.
 */

public class UriBuilder {
    Uri uri;
    String sortBy;
    // ALERT!!!! APIKEY MUST REMAIN SECRET!!! WOW!!! SUCH SECRET!!! VERY MYSTERY!!!! MUCH HIDDEN!!! MANY OBSCURE!!! SO ENIGMA!!!
    String apiKey = "015ac4ead30e7a0feabdd7d0db9dca9c";

    public UriBuilder() {}

    public String buildTheURI(String startDate, String endDate, String sortPref) {


        //get the sorting preference to build URI
          if (sortPref.equals("pop") || sortPref.equals(null)) {
            sortBy = "popularity.desc";
        } else if (sortPref.equals("rat")){
            sortBy = "rating.desc";
        } else {
            Log.v("tag", "sortPref is not correct. sortPref is: " + sortPref);
        }

        // build the URI
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("api_key", apiKey)
                .appendQueryParameter("language", "en-US")
                .appendQueryParameter("sort_by", sortBy)
                .appendQueryParameter("page", "1")
                .appendQueryParameter("primary_release_date.gte", startDate)
                .appendQueryParameter("primary_release_date.lte", endDate);

        String resultingUrl = builder.build().toString();
        return resultingUrl;
    }
}
