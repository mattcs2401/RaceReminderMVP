package mcssoft.com.raceremindermvp.adapter.race;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.adapter.base.ParentViewHolder;

public class RaceViewHolderHeader extends ParentViewHolder {

    public RaceViewHolderHeader(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Accessors">
    public TextView getMeetingCode() { return meetingCode; }

    public TextView getVenueName() { return venueName; }

    public TextView getTrackRating() { return trackRating; }

    public TextView getWeatherDesc() { return weatherDesc; }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Private vars">
    @BindView(R.id.id_tv_meeting_code) TextView meetingCode;
    @BindView(R.id.id_tv_venue_name) TextView venueName;
    @BindView(R.id.id_tv_track_rating) TextView trackRating;
    @BindView(R.id.id_tv_weather_desc) TextView weatherDesc;
    //</editor-fold>
}
