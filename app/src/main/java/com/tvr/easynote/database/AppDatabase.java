package com.tvr.easynote.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tvr.easynote.database.dao.FavoriteNotesDao;
import com.tvr.easynote.database.dao.NotesDao;

@Database(entities = {Notes.class,FavoriteNotes.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
    public abstract FavoriteNotesDao favoriteNotesDao();
}
