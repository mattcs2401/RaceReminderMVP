package mcssoft.com.raceremindermvp.interfaces.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

@Dao
public interface IMeetingsDAO {

//    @Query("SELECT COUNT(*) FROM MEETINGS WHERE ArchvFlag = :flag")
    @Query("SELECT * FROM MEETINGS WHERE ArchvFlag = :flag LIMIT 1")
    public int getMeetingsCount(String flag);


}
