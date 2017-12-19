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
//        this.context = context; // TBA
        if(sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(Resources.getInstance(context).getString(R.string.preference_file), 0);
        }
    }

//    private Context context;
    private SharedPreferences sharedPreferences;
    private static volatile Preferences instance = null;
}
