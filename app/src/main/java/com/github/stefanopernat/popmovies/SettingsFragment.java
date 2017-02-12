package com.github.stefanopernat.popmovies;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by stefanopernat on 12/02/17.
 * Settings fragment, it replace the default fragment with the content of prefs.xml
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
