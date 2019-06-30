package com.agarwal.arpit.robochat.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static com.agarwal.arpit.robochat.Utils.AppConstants.MESSAGE_TABLE;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM "+MESSAGE_TABLE +" WHERE tag = :tag")
    public abstract List<MessageEntity> getAll(String tag);

    @Insert
    void insertAll(MessageEntity... users);

}
