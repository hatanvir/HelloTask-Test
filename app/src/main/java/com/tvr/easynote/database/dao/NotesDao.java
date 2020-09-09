package com.tvr.easynote.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tvr.easynote.database.Notes;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM Notes")
    List<Notes> getAllNote();

    @Insert
    void insertNote(Notes notes);

    @Delete
    void deleteNote(Notes task);

    @Update
    void updateNote(Notes task);
}
