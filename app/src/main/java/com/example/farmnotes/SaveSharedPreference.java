package com.example.farmnotes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Jitendra on 27/03/2019.
 */
public class SaveSharedPreference
{
    static final String USER_NAME = "user_name";
    static final String SIGNED_IN = "signed_in";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(USER_NAME, userName);
        editor.commit();
    }


    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(USER_NAME, "");
    }


    public static Boolean getSignedIn(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(SIGNED_IN, false);
    }

    public static void setSignedIn(Context ctx, Boolean signedIn) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(SIGNED_IN, signedIn);
        editor.commit();
    }


    public static void clearAll(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear().apply();
    }


}
