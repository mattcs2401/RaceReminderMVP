package mcssoft.com.raceremindermvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;
import mcssoft.com.raceremindermvp.model.Race;

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
            Race race = (Race) list.get(position);

            holder.getCityCode().setText(race.getCityCode());
            holder.getRaceCode().setText(race.getRaceCode());
            holder.getRaceNo().setText(race.getRaceNum());
            holder.getRaceSel().setText(race.getRaceSel());
            holder.getRaceTime().setText(race.getTime());
            holder.getRaceDate().setText(race.getDate());
        }
    }

    @Override
    public int getItemCount() {
        if(isEmptyView) {
            return  1; // need to do this so the onCreateViewHolder fires.
        } else {
            if(list != null) {
                return list.size();
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

    public void swapData(List list) {
        if(list != null && list.size() > 0) {
            this.list = list;
            notifyDataSetChanged();
        }
    }

    public void setEmptyView(boolean emptyView) {
        this.isEmptyView = emptyView;
    }

    private List list;
    private int viewType;
    private boolean isEmptyView;
    private IClick.ItemClick icListener;

    private static final int EMPTY_VIEW = 0;
    private static final int RACE_VIEW = 1;
}

