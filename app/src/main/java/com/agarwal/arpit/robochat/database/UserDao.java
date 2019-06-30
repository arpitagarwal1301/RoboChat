package com.agarwal.arpit.robochat.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static com.agarwal.arpit.robochat.Utils.AppConstants.USER_TABLE;

@Dao
public interface UserDao {

    @Query("SELECT * FROM "+USER_TABLE )
    List<UserEntity> getAll();

    @Insert
    void insertAll(UserEntity... users);

}
