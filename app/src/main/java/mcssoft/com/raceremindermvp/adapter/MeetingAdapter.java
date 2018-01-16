package mcssoft.com.raceremindermvp.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.database.DatabaseConstants;
import mcssoft.com.raceremindermvp.interfaces.click.IClick;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    public MeetingAdapter() {
        // TBA
    }

    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
//        this.viewType = viewType;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch(viewType) {
            case EMPTY_VIEW:
                view = inflater.inflate(R.layout.row_empty, parent, false);
                return new MeetingViewHolder(view);
            case MEETING_VIEW:
                view = inflater.inflate(R.layout.row_meeting, parent, false);
                return new MeetingViewHolder(view, icListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MeetingViewHolder holder, int position) {
        Log.d(getClass().getSimpleName(), "onBindViewHolder");
        cursor.moveToPosition(position);
        holder.getTvMeetingCode().setText(cursor.getString(meetingCodeNdx));
        holder.getTvMeetingDate().setText(cursor.getString(meetingDateNdx));
        holder.getTvVenueName().setText(cursor.getString(meetingVenueNdx));
    }

    @Override
    public int getItemCount() {
        if(isEmptyView) {
            return  1; // need to do this so the onCreateViewHolder fires.
        } else {
            if(cursor != null) {
                return cursor.getCount();
            } else {
                return 0;
            }
        }
    }

    @Override
    public long getItemId(int position) {
        Log.d(LOG_TAG, "getItemId");
        cursor.moveToPosition(position);
        return cursor.getLong(meetingIdColNdx);
    }

    @Override
    public int getItemViewType(int position) {
        if(isEmptyView) {
            return EMPTY_VIEW;
        }
        return MEETING_VIEW;
    }

    public void setClickListener(IClick.ItemClick icListener) {
        this.icListener = icListener;
    }

    public void swapCursor(Cursor cursor) {
        Log.d(LOG_TAG, "swapCursor");
        if(!isEmptyView && (cursor != null) && (cursor.getCount() > 0)) {
            this.cursor = cursor;
            cursor.moveToFirst();

            meetingIdColNdx = cursor.getColumnIndex(DatabaseConstants.MEETING_ROWID);
            meetingCodeNdx = cursor.getColumnIndex(DatabaseConstants.MEETING_CODE);
            meetingVenueNdx = cursor.getColumnIndex(DatabaseConstants.MEETING_VENUE);
            meetingDateNdx = cursor.getColumnIndex(DatabaseConstants.MEETING_DATE);
//
//            meetingWeatherDescNdx = cursor.getColumnIndex(SchemaConstants.MEETING_WEATHER_DESC);
//            meetingTrackDescNdx = cursor.getColumnIndex(SchemaConstants.MEETING_TRACK_DESC);
//            meetingTrackRatingNdx = cursor.getColumnIndex(SchemaConstants.MEETING_TRACK_RATING);

            notifyDataSetChanged();
        }
    }

    public void setEmptyView(boolean isEmptyView) {
        this.isEmptyView = isEmptyView;
    }

    public Cursor getCursor() {
        return cursor;
    }

    private int meetingIdColNdx;
    private int meetingCodeNdx;
    private int meetingVenueNdx;
    private int meetingDateNdx;

    private Cursor cursor;
    private int viewType;
    private boolean isEmptyView;
    private IClick.ItemClick icListener;

    private static final int EMPTY_VIEW = 0;
    private static final int MEETING_VIEW = 1;

    private String LOG_TAG = this.getClass().getCanonicalName();
}

