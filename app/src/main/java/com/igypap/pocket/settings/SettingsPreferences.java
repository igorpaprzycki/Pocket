package com.igypap.pocket.settings;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by igypap on 21.01.17.
 */

public class SettingsPreferences {
    private static final String PREFS_NAME = "settings";
    private static final String PREF_SHOW_LINKS = "show_links";
    private static final String PREF_SHOW_PHONES = "show_phones";
    private static final String PREF_SORT = "sort";
    private SharedPreferences mPrefs;

    public SettingsPreferences(Context context) {
        mPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public boolean isShowLinks() {
        return mPrefs.getBoolean(PREF_SHOW_LINKS, true);
    }

    public void setShowLinks(boolean showLinks) {
        mPrefs.edit()
                .putBoolean(PREF_SHOW_LINKS, showLinks)
                .apply();
    }

    public boolean isShowPhones() {
        return mPrefs.getBoolean(PREF_SHOW_PHONES, true);
    }

    public void setShowPhones(boolean showPhones) {
        mPrefs.edit()
                .putBoolean(PREF_SHOW_PHONES, showPhones)
                .apply();
    }

    public boolean isSort() {
        return mPrefs.getBoolean(PREF_SORT, false);
    }

    public void setSort(boolean sort) {
        mPrefs.edit()
                .putBoolean(PREF_SORT, sort)
                .apply();
    }
}
