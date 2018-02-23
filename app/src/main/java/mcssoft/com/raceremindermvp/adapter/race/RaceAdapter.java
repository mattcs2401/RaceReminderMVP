package mcssoft.com.raceremindermvp.adapter.race;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;
import mcssoft.com.raceremindermvp.model.database.Race;

public class RaceAdapter extends RecyclerView.Adapter<RaceViewHolder> {

    public RaceAdapter() {
        // TBA
    }

    @Override
    public RaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
//        this.viewType = viewType;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch(viewType) {
            case EMPTY_VIEW:
                view = inflater.inflate(R.layout.row_empty, parent, false);
                return new RaceViewHolder(view);
            case RACE_VIEW:
                view = inflater.inflate(R.layout.row_race, parent, false);
                return new RaceViewHolder(view, icListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RaceViewHolder holder, int position) {
        if(!isEmptyView) {
            Race race = (Race) lRace.get(position);
            String bp = "";

//            holder.getRaceNo().setText(race.getRaceNum());
//            holder.getRaceSel().setText(race.getRaceSel());
//            holder.getRaceTime().setText(race.getTime());
//            holder.getRaceDate().setText(race.getDate());

//             "RaceNumber") private String raceNumber;      // e.g. "1"
//             "RaceTime") private String raceTime;          // e.g. "12:55"
//             "RaceName") private String raceName;          // e.g. "BM 60 HANDICAP"
//             "RaceDist") private String raceDistance;      // e.g. "1905"
        }
    }

    @Override
    public int getItemCount() {
        if(isEmptyView) {
            return  1; // need to do this so the onCreateViewHolder fires.
        } else {
            if(lRace != null) {
                return lRace.size();
            } else {
                return 0;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(isEmptyView) {
            return EMPTY_VIEW;
        }
        return RACE_VIEW;
    }

    public void setClickListener(IClick.ItemClick icListener) {
        this.icListener = icListener;
    }

    public void swapData(List lRace) {
        this.lRace = lRace;
        if(lRace == null || lRace.size() < 1) {
            setEmptyView(true);
        } else {
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
        if(lRace != null && lPos > -1) {
            return lRace.get(lPos);
        }
        return null;
    }

    public void setEmptyView(boolean emptyView) {
        this.isEmptyView = emptyView;
    }

    private int viewType;
    private List<Race> lRace;
    private boolean isEmptyView;
    private IClick.ItemClick icListener;

    private static final int EMPTY_VIEW = 0;
    private static final int RACE_VIEW = 1;
}

