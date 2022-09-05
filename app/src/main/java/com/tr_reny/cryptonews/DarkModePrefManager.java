package com.tr_reny.cryptonews;

/**
 * This a DarkMode Manager class
 * Created on 22nd Aug 2022 by Reny Kipkoech.
 */

import android.content.Context;
import android.content.SharedPreferences;


public class DarkModePrefManager {
    // Shared preferences file name
    private static final String PREF_NAME = "CrptoNews-dark-mode";
    private static final String IS_NIGHT_MODE = "IsNightMode";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // shared pref mode
    int PRIVATE_MODE = 0;


    public DarkModePrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setDarkMode(boolean isFirstTime) {
        editor.putBoolean(IS_NIGHT_MODE, isFirstTime);
        editor.commit();
    }

    public boolean isNightMode() {
        return pref.getBoolean(IS_NIGHT_MODE, true);
    }

}