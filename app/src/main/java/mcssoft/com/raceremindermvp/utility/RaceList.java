package mcssoft.com.raceremindermvp.utility;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.database.Race;

/**
 * utility class for a list of Meeting objects that can be passed in a Bundle.
 */
public class RaceList implements Parcelable {

    public RaceList(Object object) {
        lRace = new ArrayList<>();
        for(Race race : (List<Race>) object) {
            lRace.add(race);
        }
    }

    public ArrayList<Race> getRaceList() {
        return lRace;
    }

    private ArrayList<Race> lRace;

    //<editor-fold defaultstate="collapsed" desc="Region: Parcel">
    // Note: Methods below are generated code. Refer: http://www.parcelabler.com/

    protected RaceList(Parcel in) {
        if (in.readByte() == 0x01) {
            lRace = new ArrayList<Race>();
            in.readList(lRace, Race.class.getClassLoader());
        } else {
            lRace = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (lRace == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(lRace);
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<RaceList> CREATOR = new Creator<RaceList>() {
        @Override
        public RaceList createFromParcel(Parcel in) {
            return new RaceList(in);
        }

        @Override
        public RaceList[] newArray(int size) {
            return new RaceList[size];
        }
    };
    //</editor-fold>

}
