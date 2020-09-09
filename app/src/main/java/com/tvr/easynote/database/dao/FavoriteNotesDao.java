package com.tvr.easynote.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tvr.easynote.database.FavoriteNotes;
import com.tvr.easynote.database.Notes;

import java.util.List;

@Dao
public interface FavoriteNotesDao {
    @Query("SELECT * FROM FavoriteNotes")
    List<FavoriteNotes> getAllNoteFromFav();

    @Insert
    void insertNote(FavoriteNotes notes);

    /*@Delete
    void deleteNote(Notes task);

    @Update
    void updateNote(Notes task);*/
}
