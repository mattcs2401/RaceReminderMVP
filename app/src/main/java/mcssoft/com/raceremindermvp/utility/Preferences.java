package mcssoft.com.raceremindermvp.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;

public class Preferences {

    static synchronized public Preferences getInstance(Context context) {
        if(instance == null) {
            instance = new Preferences(context);
        }
        return instance;
    }

    private Preferences(Context context) {
        ButterKnife.bind(this, new View(context));
        if(sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(preference_file, 0);
        }
    }

    private SharedPreferences sharedPreferences;
    private static volatile Preferences instance = null;

    @BindString(R.string.preference_file) String preference_file;
}
