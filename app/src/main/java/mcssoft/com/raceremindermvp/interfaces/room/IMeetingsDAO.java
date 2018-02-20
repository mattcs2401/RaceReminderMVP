package mcssoft.com.raceremindermvp.interfaces.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import mcssoft.com.raceremindermvp.model.database.Meeting;

@Dao
public interface IMeetingsDAO {

    @Query("SELECT COUNT(*) FROM MEETINGS WHERE ArchvFlag = :flag")
    public int getMeetingsCount(String flag);

    @Query("SELECT * FROM MEETINGS WHERE _id = :rowId")
    public Meeting getMeeting(int rowId);

    @Query("SELECT * FROM MEETINGS WHERE ArchvFlag = :flag")
    public List<Meeting> getMeetings(String flag);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public List<Long> insertMeetings(List<Meeting> lMeetings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertMeeting(Meeting meeting);

    @Query("DELETE FROM MEETINGS")
    public int deleteMeetings();

    @Delete
    public int deleteMeetings(List<Meeting> lMeeting);
}
