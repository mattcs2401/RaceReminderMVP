package mcssoft.com.raceremindermvp.interfaces.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import mcssoft.com.raceremindermvp.model.database.Race;

/**
 * Race DAO for Room (database).
 */
@Dao
public interface IRacesDAO {

    @Insert
    void insert(Race race);

    @Delete
    int deleteAll(Race[] races);

    @Update
    int update(Race race);

    @Query("select * from RACES where ArchvFlag = :archvFlag")
    List<Race> selectAll(String archvFlag);

    @Query("select * from Races where _id = :id")
    Race  selectRace(int id);
}
