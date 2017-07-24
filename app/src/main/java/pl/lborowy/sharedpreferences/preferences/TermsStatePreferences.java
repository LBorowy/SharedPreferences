package pl.lborowy.sharedpreferences.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by RENT on 2017-07-24.
 */

public class TermsStatePreferences {

    public static final String TERMS_ARE_ACCEPTED = "terms_are_accepted";

    public static void setTermsState(Context context, boolean state) {
        PreferenceManager
                .getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(TERMS_ARE_ACCEPTED, state)
                .apply();
    }
    public static boolean getTermsState(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("terms_are_accepted", false);
    }


}
