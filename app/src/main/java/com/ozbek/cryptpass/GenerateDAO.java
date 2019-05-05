package com.ozbek.cryptpass;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GenerateDAO {

    @Insert
    void insert(Generate generate);

    @Update
    void update(Generate generate);

    @Delete
    void delete(Generate generate);

    @Query("DELETE FROM generate_table")
    void deleteAllEntries();

    @Query("SELECT * FROM generate_table")
    LiveData<List<Generate>> getAllEntries();
}
