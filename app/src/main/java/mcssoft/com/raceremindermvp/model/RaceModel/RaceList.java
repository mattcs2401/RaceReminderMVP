package mcssoft.com.raceremindermvp.model.RaceModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to model a clollection of Race objects.
 */
public class RaceList implements Parcelable {

    //<editor-fold defaultstate="collapsed" desc="Region: Parcelable">
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(lRaces);
    }

    public RaceList(Parcel in) {
        this.lRaces = in.createTypedArrayList(Race.CREATOR);
    }

    public static final Parcelable.Creator<RaceList> CREATOR = new Parcelable.Creator<RaceList>() {
        @Override
        public RaceList createFromParcel(Parcel source) {
            return new RaceList(source);
        }

        @Override
        public RaceList[] newArray(int size) {
            return new RaceList[size];
        }
    };
    //</editor-fold>

    public RaceList() {
        lRaces = new ArrayList<>();
    }

    /**
     * Get a single Race from the list.
     * @param index The list index.
     * @return The Race object (may be empty if index value is incorrect).
     */
    public Race getRace(int index) {
        Race race = new Race();
        if(index > -1 && index < lRaces.size()) {
            race = lRaces.get(index);
        }
        return race;
    }

    /**
     * Get the current Race list.
     * @return The Race list.
     */
    public List<Race> getRaces() {
        if(lRaces == null) {
            lRaces = new ArrayList<>();
        }
        return lRaces;
    }

    /**
     * Add a Race to the current Race list.
     * @param race The Race to add (if not null).
     */
    public void addRace(Race race) {
        if(race != null) {
            lRaces.add(race);
        }
    }

    /**
     * Replace the current Race list with that given.
     * @param lRaces The Race list replacement (if not null).
     */
    public void setRaces(List<Race> lRaces) {
        if(lRaces != null) {
            this.lRaces = lRaces;
        }
    }

    private List<Race> lRaces;
}
