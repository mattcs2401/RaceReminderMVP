package mcssoft.com.raceremindermvp.utility;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import mcssoft.com.raceremindermvp.model.database.Meeting;

public class MeetingList implements Parcelable {

    public MeetingList(Object object) {
        this.object = object;
//        createListing(object);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Parcel">
    protected MeetingList(Parcel in) {
        in.readList(meetingList, Meeting.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        meetingList = (List<Meeting>) object;
        dest.writeList(meetingList);
    }

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

//    private void createListing(Object object) {
//        String bp = "";
//    }

    private Object object;
    private List<Meeting> meetingList;
}
