package mcssoft.com.raceremindermvp.utility;

import android.content.Context;
import android.content.SharedPreferences;

import mcssoft.com.raceremindermvp.R;

public class Preferences {

    static synchronized public Preferences getInstance(Context context) {
        if(instance == null) {
            instance = new Preferences(context);
        }
        return instance;
    }

    private Preferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Resources.getInstance(context).getString(R.string.preference_file), 0);
    }

    private Context context;
    private SharedPreferences sharedPreferences;
    private static volatile Preferences instance = null;

//    public void put(String key, String value) {
//        sharedPreferences.edit().putString(key, value).apply();
//    }
//
//    public void put(String key, int value) {
//        sharedPreferences.edit().putInt(key, value).apply();
//    }
//
//    public void put(String key, float value) {
//        sharedPreferences.edit().putFloat(key, value).apply();
//    }
//
//    public void put(String key, boolean value) {
//        sharedPreferences.edit().putBoolean(key, value).apply();
//    }
//
//    public String get(String key, String defaultValue) {
//        return sharedPreferences.getString(key, defaultValue);
//    }
//
//    public Integer get(String key, int defaultValue) {
//        return sharedPreferences.getInt(key, defaultValue);
//    }
//
//    public Float get(String key, float defaultValue) {
//        return sharedPreferences.getFloat(key, defaultValue);
//    }
//
//    public Boolean get(String key, boolean defaultValue) {
//        return sharedPreferences.getBoolean(key, defaultValue);
//    }
//
//    public void deleteSavedData(String key) {
//        sharedPreferences.edit().remove(key).apply();
//    }

}
