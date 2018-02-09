package mcssoft.com.raceremindermvp.utility;


import android.content.Context;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;

import static java.util.Locale.getDefault;

/**
 * utility class for general manipulation of time values (primarily for display).
 */
public class DateTime {

    public DateTime(Context context) {
        this.context = context;
        ButterKnife.bind(this, new View(context));
    }

    /**
     * Get the Locale default calendar.
     * @return The calendar.
     */
    public Calendar getCalendar() {
        return Calendar.getInstance(getDefault());
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

        SimpleDateFormat sdFormat = new SimpleDateFormat(time_format_24hr, Locale.getDefault());
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
        SimpleDateFormat sdFormat = new SimpleDateFormat(date_format, Locale.getDefault());
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

    /**
     * Generate the YYYY, MM, DD elements of a meeting date based on the current date.
     * @return The date as: [0]-YYYY, [1]-M(M), [2]-D(D)
     */
    public String[] getCurrentDateComponents() {
        String[] date = new String[3];
        Calendar calendar = getCalendar();
        calendar.setTime(new Date(getTimeInMillis()));
        date[0] = Integer.toString(calendar.get(Calendar.YEAR));
        date[1] = Integer.toString(calendar.get(Calendar.MONTH) + 1); // month starts at 0.
        date[2] = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        return date;
    }

    /**
     * Extract the time from the Xml time value.
     * @param time formatted as "YYYY-MM-DDTHH:MM:SS" (from Xml).
     * @return time as HH:MM format.
     */
    public String getTimeComponent(String time) {
        String[] array = (time.split("T")[1]).split(":");
        return (array[0] + ":" + array[1]);
    }

    public String createRaceUrl(String meetingDate) {

        String bp = "";
        return null;
    }

    private String timeFormat;    // the current time format (12HR/24HR).
    private Context context;      // app context for shared preferences.

    @BindString(R.string.date_format) String date_format;
    @BindString(R.string.time_format_24hr) String time_format_24hr;
}

