package mcssoft.com.raceremindermvp.adapter.race;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;

public class RaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public RaceViewHolder(View view) {
        super(view);
        tvEmptyView = (TextView) view.findViewById(R.id.id_tv_empty);
        tvEmptyView.setText("Nothing to show.");
    }

    public RaceViewHolder(View view, IClick.ItemClick icListener) {
        super(view);
        // Set the ViewHolder components.
        tvCityCode = (TextView) view.findViewById(R.id.tv_city_code);
        tvRaceCode = (TextView) view.findViewById(R.id.tv_race_code);
        tvRaceNo = (TextView) view.findViewById(R.id.tv_venue_name);
        tvRaceSel = (TextView) view.findViewById(R.id.tv_race_sel);
        tvRaceTime = (TextView) view.findViewById(R.id.id_tv_race_time);
        tvRaceDate = (TextView) view.findViewById(R.id.id_tv_race_date);
//        tvRaceDay = (TextView) view.findViewById(R.id.tv_race_day);

        // Set the listeners.
        this.icListener = icListener;
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition(); // debug/testing purposes.
        icListener.onItemClick(view, position);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Accessors">
    public TextView getCityCode() {
        return tvCityCode;
    }

    public TextView getRaceCode() {
        return tvRaceCode;
    }

    public TextView getRaceNo() {
        return tvRaceNo;
    }

    public TextView getRaceSel() {
        return tvRaceSel;
    }

    public TextView getRaceTime() {
        return tvRaceTime;
    }

    public TextView getRaceDate() {
        return tvRaceDate;
    }

    public TextView getRaceDay() {
        return tvRaceDay;
    }

    public TextView getEmptyText() { return tvEmptyView; }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Private vars">
    private TextView tvCityCode;
    private TextView tvRaceCode;
    private TextView tvRaceNo;
    private TextView tvRaceSel;
    private TextView tvRaceTime;
    private TextView tvRaceDate;
    private TextView tvRaceDay;
    private TextView tvEmptyView;

    private IClick.ItemClick icListener;
    //</editor-fold>
}
