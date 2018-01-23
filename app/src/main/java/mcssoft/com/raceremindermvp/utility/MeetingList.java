package mcssoft.com.raceremindermvp.utility;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import mcssoft.com.raceremindermvp.model.database.Meeting;

/**
 * utility class for a list of Meeting objects that can be passed in a Bundle.
 */
public class MeetingList implements Parcelable {

    public MeetingList(Object object) {
        lMeeting = new ArrayList<>();
        for(Meeting meeting : (List<Meeting>) object) {
            lMeeting.add(meeting);
        }
    }

    public ArrayList<Meeting> getMeetingList() {
        return lMeeting;
    }

    private ArrayList<Meeting> lMeeting;

    //<editor-fold defaultstate="collapsed" desc="Region: Parcel">
    // Note: Methods below are generated code. Refer: http://www.parcelabler.com/

    protected MeetingList(Parcel in) {
        if (in.readByte() == 0x01) {
            lMeeting = new ArrayList<Meeting>();
            in.readList(lMeeting, Meeting.class.getClassLoader());
        } else {
            lMeeting = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (lMeeting == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(lMeeting);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MeetingList> CREATOR = new Parcelable.Creator<MeetingList>() {
        @Override
        public MeetingList createFromParcel(Parcel in) {
            return new MeetingList(in);
        }

        @Override
        public MeetingList[] newArray(int size) {
            return new MeetingList[size];
        }
    };
    //</editor-fold>

}
