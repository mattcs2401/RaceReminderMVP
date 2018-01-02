package mcssoft.com.raceremindermvp.interfaces.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import mcssoft.com.raceremindermvp.model.database.Meeting;

@Dao
public interface IMeetingsDAO {

    @Insert
    void insert(Meeting meeting);

    @Delete
    int deleteAll(Meeting[] meetings);

    @Update
    int update(Meeting meeting);

    @Query("select * from MEETINGS where ArchvFlag = :archvFlag")
    List<Meeting> selectAll(String archvFlag);

    @Query("select * from Meetings where _id = :id")
    Meeting  selectMeeting(int id);
}
