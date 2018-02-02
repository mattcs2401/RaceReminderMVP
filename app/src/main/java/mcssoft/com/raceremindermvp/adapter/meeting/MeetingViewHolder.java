package mcssoft.com.raceremindermvp.adapter.meeting;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;

public class MeetingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public MeetingViewHolder(View view) {
        super(view);
        tvEmptyView = (TextView) view.findViewById(R.id.id_tv_empty);
        tvEmptyView.setText("Nothing to show.");
    }

    public MeetingViewHolder(View view, IClick.ItemClick icListener) {
        super(view);
        // Set the ViewHolder components.
        tvMeetingCode = (TextView) view.findViewById(R.id.id_tv_meeting_code);
        tvMeetingDate = (TextView) view.findViewById(R.id.id_tv_meeting_date);
        tvVenueName = (TextView) view.findViewById(R.id.id_tv_venue_name);
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
    public TextView getTvMeetingCode() {
        return tvMeetingCode;
    }

    public TextView getTvVenueName() {
        return tvVenueName;
    }

    public TextView getTvMeetingDate() {
        return tvMeetingDate;
    }

    public TextView getTvEmptyView() {
        return tvEmptyView;
    }

    public IClick.ItemClick getIcListener() {
        return icListener;
    }
    //</editor-fold>

    public void setIcListener(IClick.ItemClick icListener) {
        this.icListener = icListener;
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Private vars">
    private TextView tvMeetingCode;
    private TextView tvVenueName;
    private TextView tvMeetingDate;
    private TextView tvEmptyView;

    private IClick.ItemClick icListener;
    //</editor-fold>
}
