package mcssoft.com.raceremindermvp.adapter.meeting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;

import static butterknife.ButterKnife.bind;

public class MeetingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public MeetingViewHolder(View view, String message) {
        super(view);
        TextView tvEmptyView = view.findViewById(R.id.id_tv_empty);
        tvEmptyView.setText(message);

    }

    public MeetingViewHolder(View view, IClick.ItemClick icListener) {
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
    public TextView getMeetingCode() {
        return tvMeetingCode;
    }

    public TextView getVenueName() {
        return tvVenueName;
    }

    public TextView getMeetingDate() {
        return tvMeetingDate;
    }

    public IClick.ItemClick getIcListener() {
        return icListener;
    }
    //</editor-fold>

    public void setIcListener(IClick.ItemClick icListener) {
        this.icListener = icListener;
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Private vars">
    private IClick.ItemClick icListener;

    @BindView(R.id.id_tv_venue_name) TextView tvVenueName;
    @BindView(R.id.id_tv_meeting_code) TextView tvMeetingCode;
    @BindView(R.id.id_tv_meeting_date) TextView tvMeetingDate;
    //</editor-fold>
}
