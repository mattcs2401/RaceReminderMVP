package mcssoft.com.raceremindermvp.utility;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;

/**
 * Utility class to create download Urls.
 */
public class Url {

    public Url(Context context) {
        this.context = context;
        ButterKnife.bind(this, new View(context));
    }

    /**
     * Create the RaceDay Url
     * https://tatts.com/pagedata/racing/YYYY/M(M)/D(D)/RaceDay.xml
     * @param raceDate Optional date value. If not set then current date used.
     * @return The Url.
     */
    public String createRaceDayUrl(@Nullable String[] raceDate) {
        if(raceDate == null) {
            DateTime dt = new DateTime(context);
            raceDate = dt.getCurrentDateComponents();
        }
        Uri.Builder builder = new Uri.Builder();
        builder.encodedPath(base_path)
               .appendPath(raceDate[0]);

        // Remove leading zeros if exist.
        if(raceDate[1].charAt(0) == '0') {
            builder.appendPath(raceDate[1].substring(1));
        } else {
            builder.appendPath(raceDate[1]);
        }
        if(raceDate[2].charAt(0) == '0') {
            builder.appendPath(raceDate[2].substring(1));
        } else {
            builder.appendPath(raceDate[2]);
        }

        builder.appendPath(race_day_listing);
        builder.build();
        return builder.toString();
    }

    /**
     * Create the Meeting Url
     * https://tatts.com/pagedata/racing/YYYY/M(M)/D(D)/code.xml
     * @param date The meeting date value as YYYY-MM-DD.
     * @param code The meeting code value as, e.g. BR.
     * @return The Url.
     */
    public String createMeetingUrl(@NonNull String date, @NonNull String code) {
        Uri.Builder builder = new Uri.Builder();
        String[] meetingDate = date.split("-");
        builder.encodedPath(base_path)
               .appendPath(meetingDate[0]);

        // Remove leading zeros if exist..
        if(meetingDate[1].charAt(0) == '0') {
            builder.appendPath(meetingDate[1].substring(1));
        } else {
            builder.appendPath(meetingDate[1]);
        }
        if(meetingDate[2].charAt(0) == '0') {
            builder.appendPath(meetingDate[2].substring(1));
        } else {
            builder.appendPath(meetingDate[2]);
        }

        builder.appendPath(code + xml_extn);
        builder.build();
        return builder.toString();
    }

    /**
     * Create the Race Url.
     * https://tatts.com/pagedata/racing/YYYY/M(M)/D(D)/code+number.xml
     * @param date The meeting date value.
     * @param code The meeting code value.
     * @param raceNo The race number.
     * @return The Url
     */
    public String createRaceUrl(@NonNull String[] date, @NonNull String code, @NonNull String raceNo) {
        Uri.Builder builder = new Uri.Builder();
        builder.encodedPath(base_path)
                .appendPath(date[0])
                .appendPath(date[1])
                .appendPath(date[2])
                .appendPath(code + raceNo + xml_extn);
        builder.build();
        return builder.toString();
    }

    private Context context;

    // ButterKnife.
    @BindString(R.string.xml_extn) String xml_extn;
    @BindString(R.string.base_path) String base_path;
    @BindString(R.string.race_day_listing) String race_day_listing;
}
