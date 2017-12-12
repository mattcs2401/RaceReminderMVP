package mcssoft.com.raceremindermvp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import mcssoft.com.raceremindermvp.database.Race;

@Dao
public interface RaceDAO {

    @Insert
    void insert(Race race);

    @Delete
    int deleteAll(Race[] races);

    @Update
    int update(Race race);

    @Query("select * from Race")
    List<Race> selectAll();

    @Query("select * from Race where _id = :id")
    Race selectRace(int id);
}
