package mcssoft.com.raceremindermvp.interfaces.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import mcssoft.com.raceremindermvp.model.database.Race;

@Dao
public interface IRacesDAO {

    @Query("SELECT COUNT(*) FROM RACES WHERE ArchvFlag = :flag")
    public int getRacesCount(String flag);

    @Query("SELECT * FROM RACES WHERE _id = :rowId")
    public Race getRace(int rowId);

    @Query("SELECT * FROM RACES WHERE ArchvFlag = :flag")
    public List<Race> getRaces(String flag);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public List<Long> insertRaces(List<Race> lRaces);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertRace(Race race);

    @Query("DELETE FROM RACES")
    public int deleteRaces();

    @Delete
    public int deleteRaces(List<Race> lRace);
}
