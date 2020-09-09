package com.tvr.easynote.features.model;

import com.tvr.easynote.database.AppDatabase;
import com.tvr.easynote.database.FavoriteNotes;
import com.tvr.easynote.database.Notes;
import com.tvr.easynote.features.common.RequestCompleteListener;

import java.util.List;

public interface NoteModel {
    void addNote(Notes notes, AppDatabase appDatabase, RequestCompleteListener requestCompleteListener);
    void getNotes(RequestCompleteListener<List<Notes>> requestCompleteListener);
    void deleteNote(Notes notes,RequestCompleteListener requestCompleteListener);
    void updateNote(Notes notes,AppDatabase appDatabase,RequestCompleteListener requestCompleteListener);

    void addFavorite(FavoriteNotes notes, RequestCompleteListener requestCompleteListener);
    void getFavNotes(RequestCompleteListener<List<FavoriteNotes>> requestCompleteListener);
}
