package mcssoft.com.raceremindermvp.adapter.race;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;

public class RaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public RaceViewHolder(View view, String message) {
        super(view);
        ((TextView) view.findViewById(R.id.id_tv_empty)).setText(message);
    }

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

    //<editor-fold defaultstate="collapsed" desc="Region: Accessors">
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

    //<editor-fold defaultstate="collapsed" desc="Region: Private vars">
    private IClick.ItemClick icListener;

    @BindView(R.id.id_tv_race_num) TextView tvRaceNum;
    @BindView(R.id.id_tv_race_name) TextView tvRaceName;
    @BindView(R.id.id_tv_race_time) TextView tvRaceTime;
    @BindView(R.id.id_tv_race_dist) TextView tvRaceDist;
    //</editor-fold>
}
