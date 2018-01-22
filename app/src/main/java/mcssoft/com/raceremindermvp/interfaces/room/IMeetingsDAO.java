package mcssoft.com.raceremindermvp.interfaces.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import mcssoft.com.raceremindermvp.model.database.Meeting;

@Dao
public interface IMeetingsDAO {

//    @Query("SELECT COUNT(*) FROM MEETINGS WHERE ArchvFlag = :flag")
    @Query("SELECT COUNT(*) FROM MEETINGS WHERE ArchvFlag = :flag")
    public int getMeetingsCount(String flag);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMeetings(Meeting... meetings);
}
