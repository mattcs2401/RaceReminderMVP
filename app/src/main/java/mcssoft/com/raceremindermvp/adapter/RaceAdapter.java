package mcssoft.com.raceremindermvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.IClick;

public class RaceAdapter extends RecyclerView.Adapter<RaceViewHolder> {

    public RaceAdapter() {
        // TBA
    }

    @Override
    public RaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch(viewType) {
            case EMPTY_VIEW:
                view = inflater.inflate(R.layout.row_empty, parent, false);
                return new RaceViewHolder(view);
            case GENERAL_VIEW:
//                view = inflater.inflate(R.layout.general_row, parent, false);
                return new RaceViewHolder(view, icListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RaceViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setOnItemClickListener(IClick.ItemClick listener) {
        this.icListener = listener;
    }

    private boolean isEmptyView;            // flag.
    private IClick.ItemClick icListener;

    private static final int EMPTY_VIEW = 0;
    private static final int GENERAL_VIEW = 1;
}

