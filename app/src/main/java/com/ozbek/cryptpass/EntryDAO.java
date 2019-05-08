package com.ozbek.cryptpass;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EntryDAO {

    @Insert
    void insert(Entries entries);

    @Update
    void update(Entries entries);

    @Delete
    void delete(Entries entries);

    @Query("DELETE FROM entries_table")
    void deleteAllEntries();

    @Query("SELECT * FROM entries_table")
    LiveData<List<Entries>> getAllEntries();
}
