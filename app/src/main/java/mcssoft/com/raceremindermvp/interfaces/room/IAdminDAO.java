package mcssoft.com.raceremindermvp.interfaces.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import mcssoft.com.raceremindermvp.model.database.Admin;

@Dao
public interface IAdminDAO {

    @Insert
    void insert(Admin admin);

    @Update
    void update(Admin admin);

    @Query("select * from Admin where _id = :id")
    Admin  selectAdmin(int id);
}
