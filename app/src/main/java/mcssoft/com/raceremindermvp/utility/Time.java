package mcssoft.com.raceremindermvp.utility;


import android.content.Context;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import mcssoft.com.raceremindermvp.R;

/**
 * utility class for general manipulation of time values (primarily for display).
 */
public class Time {

    /**
     * Get (initialise) an instance of MeetingTime.
     * @param context The current context.
     * @return An instance of MeetingTime.
     */
    public static synchronized Time getInstance(Context context) {
        if(instance == null) {
            instance = new Time(context);
        }
        return instance;
    }

    /**
     * Get the Locale default calendar.
     * @return The calendar.
     */
    public Calendar getCalendar() {
        return Calendar.getInstance(Locale.getDefault());
    }
    /**
     * Get the time as a string formatted HH:MM.
     * @param timeInMillis The time value in mSec, or a value < 1.
     * @return The time based on the parameter timeInMillis value, or the current time.
     */
    public String getFormattedTimeFromMillis(long timeInMillis) {
        if(timeInMillis < 1) {
            timeInMillis = getTimeInMillis();
        }
        Calendar calendar = getCalendar();
        calendar.setTime(new Date(timeInMillis));

        SimpleDateFormat sdFormat = new SimpleDateFormat(Resources.getInstance(context)
                    .getString(R.string.time_format_24hr), Locale.getDefault());
        return sdFormat.format(calendar.getTime());
    }

    /**
     * Get the date as a string formatted dd/MM/yyyy.
     * @param timeInMillis The time value in mSec, or a value < 1.
     * @return The date based on the parameter timeInMillis value, or the current date.
     */
    public String getFormattedDateFromMillis(long timeInMillis) {
        if(timeInMillis < 1) {
            timeInMillis = getTimeInMillis();
        }
        Calendar calendar = getCalendar();
        calendar.setTime(new Date(timeInMillis));
        SimpleDateFormat sdFormat = new SimpleDateFormat(Resources.getInstance(context)
                .getString(R.string.date_format), Locale.getDefault());
        return sdFormat.format(calendar.getTime());
    }

    /**
     * Get the time in milli seconds based on the given hour and minute values.
     * @param time int[2] where; [0] == hour, [1] ==  minute (can be null).
     * @return The time in milli seconds, or null.
     */
    public long getMillisFromTimeComponent(int [] time) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(Calendar.HOUR_OF_DAY, time[0]);
        calendar.set(Calendar.MINUTE, time[1]);
        return calendar.getTimeInMillis();
    }

    /**
     * Get the current time in milli seconds.
     * @return The time in milli seconds.
     */
    public long getTimeInMillis() {
        return getCalendar().getTimeInMillis();
    }

//    /**
//     * Get the HH (hour) and MM (minute) time components of the given time.
//     * @param timeInMillis The time value in mSec.
//     * @param component An identifier for the time component.
//     * @return The time components; int[0] == hour or minute, and int[1] == minute or -1, or null
//     *         if the parameter is unrecognised.
//     */
//    public int [] getTimeComponent(long timeInMillis, int component) {
//
//        int [] time = new int[2];
//
//        Calendar calendar = Calendar.getInstance(Locale.getDefault());
//        calendar.setTime(new Date(timeInMillis));
//
//        switch(component) {
//            case R.integer.time_component_hour_minute:
//                time[0] = calendar.get(Calendar.HOUR_OF_DAY);
//                time[1] = calendar.get(Calendar.MINUTE);
//                break;
//            case R.integer.time_component_hour:
//                time[0] = calendar.get(Calendar.HOUR_OF_DAY);
//                time[1] = R.integer.init_default;
//                break;
//            case R.integer.time_component_minute:
//                time[0] = calendar.get(Calendar.MINUTE);
//                time[1] = R.integer.init_default;
//                break;
//            default:
//                time = null;
//        }
//        return time;
//    }

//    public String getTimeFormatPrefKey() {
//        String timeFormatPrefKey = null;
//        if (timeFormat.equals(MeetingResources.getInstance()
//                .getString(R.string.time_format_pref_12hr))) {
//            timeFormatPrefKey = MeetingResources.getInstance()
//                    .getString(R.string.time_format_pref_12hr_key);
//        } else if (timeFormat.equals(MeetingResources.getInstance()
//                .getString(R.string.time_format_pref_24hr))) {
//            timeFormatPrefKey = MeetingResources.getInstance()
//                    .getString(R.string.time_format_pref_24hr_key);
//        }
//        return timeFormatPrefKey;
//    }

//    /**
//     * Get a time value in millis that is the current time minus (parameter).
//     * @param reminderTime A time value in minutes.
//     * @param raceTime A time value in milli seconds.
//     * @return A time value that is the race time minus the reminder time.
//     */
//    public long getTimeMinus(int reminderTime, long raceTime) {
//        Calendar calendar = Calendar.getInstance(Locale.getDefault());
//        calendar.setTime(new Date(raceTime));
//        calendar.add(Calendar.MINUTE, -reminderTime);
//        return calendar.getTimeInMillis();
//    }

//    /**
//     * Ensure instance values are made NULL.
//     */
//    public void destroy() {
//        context = null;
//        instance = null;
//    }

//    /**
//     * Check if the current time is after the time given.
//     * @param timeInMillis The given time.
//     * @return True if current time is after the given time, else false.
//     */
//    public boolean isTimeAfter(long timeInMillis) {
//        Calendar calendar = Calendar.getInstance(Locale.getDefault());
//        calendar.setTime(new Date(timeInMillis));
//        return Calendar.getInstance().after(calendar);
//    }

//    /**
//     * Get a value that represents midnight (00:00 hours of the current day).
//     * @return Time in mSec representing mignight.
//     */
//    public long getMidnight() {
//        Calendar calendar = Calendar.getInstance(Locale.getDefault());
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        return calendar.getTimeInMillis();
//    }

//    /**
//     * Check if the given time is a time from today.
//     * @param timeInMillis The given time.
//     * @return True if the given time is a time from today, else false.
//     * Note: A return value of false means a time on a previous day.
//     */
//    public boolean isTimeToday(long timeInMillis) {
//        Calendar today = Calendar.getInstance(Locale.getDefault());
//        Calendar toCheck = Calendar.getInstance(Locale.getDefault());
//        toCheck.setTime(new Date(timeInMillis));
//
//        if(toCheck.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
//            return true;
//        }
//
//        return false;
//    }

    private Time(Context context) {
        this.context = context;
    }

    private String timeFormat;    // the current time format (12HR/24HR).
    private Context context;      // app context for shared preferences.

    private static volatile Time instance = null;
}

