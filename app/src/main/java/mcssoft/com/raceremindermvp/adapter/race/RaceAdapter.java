package mcssoft.com.raceremindermvp.adapter.race;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.database.Race;

public class RaceAdapter extends RecyclerView.Adapter {

    public RaceAdapter(Context context, Bundle meetingDetails) {
        ButterKnife.bind(this, new View(context));
        // so we don't get null on a getItemCount.
        lRaces = new ArrayList<>();
        // header info is derived from this.
        meeting = (Meeting) meetingDetails.getParcelable(bundle_data_key);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        this.viewType = viewType;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch(viewType) {
            case EMPTY_VIEW:
                view = inflater.inflate(R.layout.row_empty, parent, false);
                return new RaceViewHolder(view, nothingToShow);
            case HEADER_VIEW:
                view = inflater.inflate(R.layout.row_race_header, parent, false);
                return new RaceViewHolderHeader(view);
            case RACE_VIEW:
                view = inflater.inflate(R.layout.row_race, parent, false);
                return new RaceViewHolder(view, icListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(viewType) {
            case HEADER_VIEW:
                RaceViewHolderHeader rvhHolder = ((RaceViewHolderHeader) holder);
                rvhHolder.getMeetingCode().setText(meeting.getMeetingCode());
                rvhHolder.getVenueName().setText(meeting.getVenueName());
                String track = meeting.getTrackDesc() + " " + meeting.getTrackRating();
                rvhHolder.getTrackRating().setText(track);
                rvhHolder.getWeatherDesc().setText(meeting.getWeatherDesc());
                break;
            case RACE_VIEW:
                RaceViewHolder rvHolder = ((RaceViewHolder) holder);
                Race race = (Race) lRaces.get(position);
                rvHolder.getRaceNum().setText(race.getRaceNumber());
                rvHolder.getRaceName().setText(race.getRaceName());
                rvHolder.getRaceTime().setText(race.getRaceTime());
                rvHolder.getRaceDist().setText(race.getRaceDistance());
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(isEmptyView) {
            // need to do this so the onCreateViewHolder fires.
            return  1;
        } else {
            return lRaces.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(isEmptyView) {
            return EMPTY_VIEW;
        } else
          if(isHeaderPosition(position)) {
            return HEADER_VIEW;
        } else {
            return RACE_VIEW;
        }
    }

    public void setClickListener(IClick.ItemClick icListener) {
        this.icListener = icListener;
    }

    public void swapData(List lRace) {
        if(lRace.size() < 1) {
            setEmptyView(true);
        } else {
            lRace.add(0, new Meeting());   // blank "header" row.
            this.lRaces = lRace;
            setEmptyView(false);
        }
        notifyDataSetChanged();
    }

    /**
     * Return the Race object at the adapter position.
     * @param lPos The adapter position (0 based).
     * @return The Race object, or NULL.
     */
    public Race getRace(int lPos) {
        if(lPos > -1) {
            return lRaces.get(lPos);
        }
        return null;
    }

    public void setEmptyView(boolean emptyView) {
        this.isEmptyView = emptyView;
    }

    private boolean isHeaderPosition(int position) {
        return position == HEADER_POS;
    }

    private int viewType;                   // the view type; empty, header or row.
    private Meeting meeting;                // subset of Mmeeting details for header view.
    private List<Race> lRaces;              // list of Race object for row view.
    private boolean isEmptyView;            // flag show empty view.
    private IClick.ItemClick icListener;    // on click listewner for row views.

    private static final int EMPTY_VIEW = 0;
    private static final int HEADER_VIEW = 1;
    private static final int RACE_VIEW = 2;

    private static final int HEADER_POS = 0;

    @BindString(R.string.nothing_to_show) String nothingToShow;
    @BindString(R.string.bundle_data_key) String bundle_data_key;
}

