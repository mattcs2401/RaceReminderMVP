package mcssoft.com.raceremindermvp.utility;

import android.content.Context;

/**
 * Utility class to get system resources.
 */
public class Resources {

    /**
     * Get (initialise) an instance of Resources.
     * @param context The current context.
     * @return An instance of Resources.
     */
    public static synchronized Resources getInstance(Context context) {
        if(instance == null) {
            instance = new Resources(context);
        }
        return instance;
    }

    /**
     * Get an integer resource.
     * @param resId The resource id.
     * @return The integer resource.
     */
    public int getInteger(int resId) {
        return context.getResources().getInteger(resId);
    }

    /**
     * Get a boolean resource.
     * @param resId The resource id.
     * @return The boolean resource.
     */
    public boolean getBoolean(int resId) {
        return context.getResources().getBoolean(resId);
    }

    /**
     * Get a string resource.
     * @param resId The resource id.
     * @return The string resource.
     */
//    public String getString(int resId) {
//        return context.getResources().getString(resId);
//    }

    /**
     * Get an array resource.
     * @param resId The resource id.
     * @return The array resource.
     */
    public String[] getStringArray(int resId) {
        return context.getResources().getStringArray(resId);
    }

    /**
     * TBA
     */
    public void destroy() {
        context = null;
        instance = null;
    }

    private Resources(Context context) {
        this.context = context;
    }

    private Context context;
    private static volatile Resources instance = null;
}
