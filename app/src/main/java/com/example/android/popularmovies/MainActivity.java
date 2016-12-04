package com.example.android.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private ArrayList<MovieItem> movieAdapterData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test build uri TEST TEST TEST TEST TEST
        UriBuilder testbuild = new UriBuilder();
        String uri  = testbuild.buildTheURI("2014-09-15", "2014-10-22", "pop");

        // Run ASYNCTASK to open URL connection and get JSON response from uri
        DownloadFilesTask getJSON = new DownloadFilesTask();
        getJSON.execute(uri);



        //create fake arraylist for testing
        movieAdapterData = new ArrayList<MovieItem>();
        movieAdapterData.add(new MovieItem("Whiplash", "whiplash bout some white kid who grew up in the ghetto and got nothin to do but play drums. Kid got talent but no brains and ends up getting burned.", "Rating: 5/5", "Jan 1st, 1111", R.drawable.manatee2));

        // dispaly the data in listview
        // Use ArrayList in dataparser
        ListView listView = (ListView) findViewById(R.id.movielist);

        MovieItemAdapter movieAdapter = new MovieItemAdapter(this, R.layout.list_item, movieAdapterData);

        listView.setAdapter(movieAdapter);

        // shared preferences
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get the preferences that were selected
        String sortPrefrence = mSharedPreferences.getString(getString(R.string.sort_key), getString(R.string.sort_default_value));


    }

    // Creating the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Handling onclick events on the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_setting:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_refresh:
                finish();
                startActivity(getIntent());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //ASYNC TASK TO CONNECT TO THE INTERNET AND GET URI DATA
    private class DownloadFilesTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {

            StringBuilder result = new StringBuilder();
            HttpURLConnection  myURLConnection = null;
            BufferedReader reader = null;

            try {
                URL myURL = new URL(urls[0]);
                myURLConnection = (HttpURLConnection) myURL.openConnection();
                myURLConnection.connect();

                InputStream inputStream = new BufferedInputStream(myURLConnection.getInputStream());
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            }
            catch (MalformedURLException e) {
                // new URL() failed
                result = null;
            }
            catch (IOException e) {
                // openConnection() failed
                result = null;
            }
            finally {

                if (myURLConnection != null) {
                    myURLConnection.disconnect();
                }

                if (reader != null) {
                    try {
                      reader.close();
                    } catch (final IOException e) {
                        Log.e("tag", "Error closing stream", e);
                    }
                }
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            // parse the JSON RESPONSE for data
            movieAdapterData.clear();
            DataParser tess = new DataParser();
            tess.readJSON(result);

            movieAdapterData = DataParser.movieList;

       }
    }

}
