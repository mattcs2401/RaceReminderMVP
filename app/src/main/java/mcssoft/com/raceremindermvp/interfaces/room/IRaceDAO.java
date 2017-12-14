package mcssoft.com.raceremindermvp.interfaces.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

import mcssoft.com.raceremindermvp.model.Race;

/**
 * Race DAO for Room (database).
 */
@Dao
public interface IRaceDAO {

    @Insert
    void insert(Race race);

    @Delete
    int deleteAll(Race[] races);

    @Update
    int update(Race race);

    @Query("select * from Race")
    List<Race> selectAll();

    @Query("select * from Race where _id = :id")
    Race  selectRace(int id);
}