package mcssoft.com.raceremindermvp.utility;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import mcssoft.com.raceremindermvp.model.database.Meeting;

public class MeetingList implements Parcelable {

//    public MeetingList(Object object) {
////        this.object = object;
//        createListing(object);
//    }

    //<editor-fold defaultstate="collapsed" desc="Region: Parcel">
    protected MeetingList(Parcel in) {
        meetingList = new ArrayList<>();
        in.readTypedList(meetingList, Meeting.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        meetingList = (List<Meeting>) object;
        dest.writeTypedList(meetingList);
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

    public ArrayList<Meeting> getMeetingList() {
        return lMeeting;
    }

    private void createListing(Object object) {
        lMeeting = new ArrayList<>();
        for(Meeting meeting : (List<Meeting>) object) {
            lMeeting.add(meeting);
        }
        String bp = "";
    }

    private ArrayList<Meeting> lMeeting;
    private Object object;
    private List<Meeting> meetingList;
}
