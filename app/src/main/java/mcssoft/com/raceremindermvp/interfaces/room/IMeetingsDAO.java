package mcssoft.com.raceremindermvp.interfaces.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import mcssoft.com.raceremindermvp.model.database.Meeting;

@Dao
public interface IMeetingsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Meeting meeting);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Meeting> meetings);

    @Delete
    int delete(List<Meeting> meetings);

    @Delete
    int delete(Meeting meeting);

    @Update // onConflict defaults to ABORT
    int update(Meeting meeting);

    @Query("select count(*) from MEETINGS where ArchvFlag = :archvFlag")
    int getCount(String archvFlag);

    @Query("select * from MEETINGS where ArchvFlag = :archvFlag")
    List<Meeting> selectAll(String archvFlag);

    @Query("select * from Meetings where _id = :id")
    Meeting  selectMeeting(int id);
}
