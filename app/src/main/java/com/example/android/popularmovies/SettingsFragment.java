package com.example.android.popularmovies;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by user on 12/1/2016.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load the preferences
        addPreferencesFromResource(R.xml.preferences);
    }

}
