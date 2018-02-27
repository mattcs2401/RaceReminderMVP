package mcssoft.com.raceremindermvp.adapter.meeting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;
import mcssoft.com.raceremindermvp.model.database.Meeting;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    public MeetingAdapter(Context context) {
        ButterKnife.bind(this, new View(context));
    }

    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        this.viewType = viewType;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case EMPTY_VIEW:
                view = inflater.inflate(R.layout.row_empty, parent, false);
                return new MeetingViewHolder(view, nothingToShow);
            case MEETING_VIEW:
                view = inflater.inflate(R.layout.row_meeting, parent, false);
                return new MeetingViewHolder(view, icListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MeetingViewHolder holder, int position) {
//        Log.d(getClass().getSimpleName(), "onBindViewHolder");
        if (!isEmptyView) {
            Meeting meeting = lMeeting.get(position);
            holder.getMeetingCode().setText(meeting.getMeetingCode());
            holder.getMeetingDate().setText(meeting.getMeetingDate());
            holder.getVenueName().setText(meeting.getVenueName());
        }
    }

    @Override
    public int getItemCount() {
        if (isEmptyView) {
            return 1; // need to do this so the onCreateViewHolder fires.
        } else {
            if (lMeeting != null) {
                return lMeeting.size();
            } else {
                return 0;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isEmptyView) {
            return EMPTY_VIEW;
        }
        return MEETING_VIEW;
    }

    public void setClickListener(IClick.ItemClick icListener) {
        this.icListener = icListener;
    }

    public void swapData(List<Meeting> lMeeting) {
        this.lMeeting = lMeeting;
        if (lMeeting == null || lMeeting.size() < 1) {
            setEmptyView(true);
        } else {
            setEmptyView(false);
        }
        notifyDataSetChanged();
    }

    /**
     * Return the Meeting object at the adapter position.
     *
     * @param lPos The adapter position (0 based).
     * @return The Meeting object, or NULL.
     */
    public Meeting getMeeting(int lPos) {
        if (lMeeting != null && lPos > -1) {
            return lMeeting.get(lPos);
        }
        return null;
    }


    public void setEmptyView(boolean isEmptyView) {
        this.isEmptyView = isEmptyView;
    }

    private int viewType;
    private boolean isEmptyView;
    private List<Meeting> lMeeting;
    private IClick.ItemClick icListener;

    private static final int EMPTY_VIEW = 0;
    private static final int MEETING_VIEW = 1;

    @BindString(R.string.nothing_to_show) String nothingToShow;

    private String LOG_TAG = this.getClass().getCanonicalName();
}


