package com.tvr.easynote.features.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tvr.easynote.database.AppDatabase;
import com.tvr.easynote.database.FavoriteNotes;
import com.tvr.easynote.database.Notes;
import com.tvr.easynote.features.common.RequestCompleteListener;
import com.tvr.easynote.features.model.NoteModel;

import java.util.List;

public class NotesViewModel extends ViewModel {
    public MutableLiveData addNoteSuccess = new MutableLiveData<String>();
    public MutableLiveData addNoteFailed = new MutableLiveData<String>();

    public void addNote(Notes notes, AppDatabase appDatabase, NoteModel model){
        model.addNote(notes, appDatabase, new RequestCompleteListener() {
            @Override
            public void OnSuccessListener(Object data) {
                addNoteSuccess.postValue(data);
            }

            @Override
            public void OnFailedListener(String error) {
                addNoteFailed.postValue(error);
            }
        });
    }

    public MutableLiveData getNoteSuccess = new MutableLiveData<List<Notes>>();
    public MutableLiveData getNoteFailed = new MutableLiveData<String>();

    public void getNotes(NoteModel model){
        model.getNotes(new RequestCompleteListener<List<Notes>>() {
            @Override
            public void OnSuccessListener(List<Notes> data) {
                getNoteSuccess.postValue(data);
            }

            @Override
            public void OnFailedListener(String error) {
                getNoteFailed.postValue(error);
            }
        });
    }

    public MutableLiveData updateNoteSuccess = new MutableLiveData<List<Notes>>();
    public MutableLiveData updateNoteFailed = new MutableLiveData<String>();

    public void updateNote(Notes notes,AppDatabase appDatabase,NoteModel model){
        model.updateNote(notes, appDatabase,new RequestCompleteListener() {
            @Override
            public void OnSuccessListener(Object data) {
                updateNoteSuccess.postValue("Success");
            }

            @Override
            public void OnFailedListener(String error) {
                updateNoteFailed.postValue("Failed");
            }
        });
    }

    public MutableLiveData deleteNoteSuccess = new MutableLiveData<List<Notes>>();
    public MutableLiveData deleteNoteFailed = new MutableLiveData<String>();

    public void deleteNote(Notes notes,NoteModel model){
        model.deleteNote(notes, new RequestCompleteListener() {
            @Override
            public void OnSuccessListener(Object data) {
                deleteNoteSuccess.postValue("Success");
            }

            @Override
            public void OnFailedListener(String error) {
                deleteNoteFailed.postValue("Failed");
            }
        });
    }

    public MutableLiveData addToFavSuccess = new MutableLiveData<List<Notes>>();
    public MutableLiveData addToFavFailed = new MutableLiveData<String>();
    public void addFavorite(FavoriteNotes notes, NoteModel model){
        model.addFavorite(notes, new RequestCompleteListener() {
            @Override
            public void OnSuccessListener(Object data) {
                addToFavSuccess.postValue("Success");
            }

            @Override
            public void OnFailedListener(String error) {
                addToFavFailed.postValue("Failed");
            }
        });
    }
}
