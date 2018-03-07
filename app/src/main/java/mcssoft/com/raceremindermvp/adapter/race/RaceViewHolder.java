package mcssoft.com.raceremindermvp.adapter.race;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;

public class RaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // this is essentially the header view.
    public RaceViewHolder(View view) {
        super(view);
        meetingCode = (TextView) view.findViewById(R.id.id_tv_meeting_code);
        venueName = (TextView) view.findViewById(R.id.id_tv_venue_name);
        trackRating = (TextView) view.findViewById(R.id.id_tv_track_rating);
        weatherDesc = (TextView) view.findViewById(R.id.id_tv_weather_desc);
//        ButterKnife.bind(this, view);
    }

    // this is essentially the empty view.
    public RaceViewHolder(View view, String message) {
        super(view);
        ((TextView) view.findViewById(R.id.id_tv_empty)).setText(message);
    }

    // this is essentially the race view.
    public RaceViewHolder(View view, IClick.ItemClick icListener) {
        super(view);
        // bind views.
        ButterKnife.bind(this, view);
        // Set the listener.
        this.icListener = icListener;
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        icListener.onItemClick(view, getAdapterPosition());
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Accessors (race view)">
    public TextView getRaceNum() {
        return tvRaceNum;
    }

    public TextView getRaceTime() {
        return tvRaceTime;
    }

    public TextView getRaceName() {
        return tvRaceName;
    }

    public TextView getRaceDist() {
        return tvRaceDist;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Accessors (header view)">
    public TextView getMeetingCode() { return meetingCode; }

    public TextView getVenueName() { return venueName; }

    public TextView getTrackRating() { return trackRating; }

    public TextView getWeatherDesc() { return weatherDesc; }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Private vars">
    private IClick.ItemClick icListener;

    // race view components.
    @Nullable @BindView(R.id.id_tv_race_num) TextView tvRaceNum;
    @Nullable @BindView(R.id.id_tv_race_name) TextView tvRaceName;
    @Nullable @BindView(R.id.id_tv_race_time) TextView tvRaceTime;
    @Nullable @BindView(R.id.id_tv_race_dist) TextView tvRaceDist;

    // header view components.
    private TextView meetingCode;
    private TextView venueName;
    private TextView trackRating;
    private TextView weatherDesc;
    //</editor-fold>
}
