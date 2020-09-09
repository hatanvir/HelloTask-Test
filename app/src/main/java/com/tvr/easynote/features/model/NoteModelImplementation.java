package com.tvr.easynote.features.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.tvr.easynote.database.AppDatabase;
import com.tvr.easynote.database.DatabaseClient;
import com.tvr.easynote.database.FavoriteNotes;
import com.tvr.easynote.database.Notes;
import com.tvr.easynote.features.common.RequestCompleteListener;

import java.util.List;

public class NoteModelImplementation implements NoteModel {
    Context context;

    public NoteModelImplementation(Context context) {
        this.context = context;
    }

    @Override
    public void addNote(Notes notes, AppDatabase appDatabase, RequestCompleteListener requestCompleteListener) {
       InsertNoteTask insertNoteTask = new InsertNoteTask(appDatabase,notes,requestCompleteListener);
       insertNoteTask.execute();
    }

    @Override
    public void getNotes(RequestCompleteListener requestCompleteListener) {
        class GetNoteTasks extends AsyncTask<Void, Void, List<Notes>> {

            @Override
            protected List<Notes> doInBackground(Void... voids) {
                List<Notes> noteTaskList = DatabaseClient
                        .getInstance(context)
                        .getAppDatabase()
                        .notesDao()
                        .getAllNote();
                return noteTaskList;
            }

            @Override
            protected void onPostExecute(List<Notes> notes) {
                super.onPostExecute(notes);
                requestCompleteListener.OnSuccessListener(notes);
            }
        }
        GetNoteTasks tasks = new GetNoteTasks();
        tasks.execute();
    }

    @Override
    public void deleteNote(Notes note, RequestCompleteListener requestCompleteListener) {
        class DeleteNoteTasks extends AsyncTask<Void, Void, List<Notes>> {

            @Override
            protected List<Notes> doInBackground(Void... voids) {
                DatabaseClient
                        .getInstance(context)
                        .getAppDatabase()
                        .notesDao()
                        .deleteNote(note);
                return null;
            }

            @Override
            protected void onPostExecute(List<Notes> notes) {
                super.onPostExecute(notes);
                requestCompleteListener.OnSuccessListener(notes);
            }
        }
        DeleteNoteTasks tasks = new DeleteNoteTasks();
        tasks.execute();
    }

    @Override
    public void updateNote(Notes notes,AppDatabase appDatabase, RequestCompleteListener requestCompleteListener) {
        class UpdateNoteTasks extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
               appDatabase
                        .notesDao()
                        .updateNote(notes);
                return null;
            }

            @Override
            protected void onPostExecute(Void notes) {
                super.onPostExecute(notes);
                Log.d("ttttt","succ3s");
                requestCompleteListener.OnSuccessListener("Success");
            }
        }
        UpdateNoteTasks tasks = new UpdateNoteTasks();
        tasks.execute();
    }

    @Override
    public void addFavorite(FavoriteNotes notes, RequestCompleteListener requestCompleteListener) {
        class AddFavNoteTasks extends AsyncTask<Void, Void, List<FavoriteNotes>> {

            @Override
            protected List<FavoriteNotes> doInBackground(Void... voids) {
                DatabaseClient
                        .getInstance(context)
                        .getAppDatabase()
                        .favoriteNotesDao()
                        .insertNote(notes);
                return null;
            }

            @Override
            protected void onPostExecute(List<FavoriteNotes> notes) {
                super.onPostExecute(notes);
                requestCompleteListener.OnSuccessListener(notes);
            }
        }
        AddFavNoteTasks tasks = new AddFavNoteTasks();
        tasks.execute();
    }

    @Override
    public void getFavNotes(RequestCompleteListener<List<FavoriteNotes>> requestCompleteListener) {
        class GetFavNoteTasks extends AsyncTask<Void, Void, List<FavoriteNotes>> {

            @Override
            protected List<FavoriteNotes> doInBackground(Void... voids) {
                List<FavoriteNotes> noteTaskList = DatabaseClient
                        .getInstance(context)
                        .getAppDatabase()
                        .favoriteNotesDao()
                        .getAllNoteFromFav();
                return noteTaskList;
            }

            @Override
            protected void onPostExecute(List<FavoriteNotes> notes) {
                super.onPostExecute(notes);
                requestCompleteListener.OnSuccessListener(notes);
            }
        }
        GetFavNoteTasks tasks = new GetFavNoteTasks();
        tasks.execute();
    }

    class InsertNoteTask extends AsyncTask<Void, Void, Void> {
        AppDatabase appDatabase;
        Notes notes;
        RequestCompleteListener requestCompleteListener;

        public  InsertNoteTask(AppDatabase appDatabase, Notes notes,RequestCompleteListener requestCompleteListener) {
            this.appDatabase = appDatabase;
            this.notes = notes;
            this.requestCompleteListener = requestCompleteListener;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDatabase.notesDao().insertNote(notes);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            requestCompleteListener.OnSuccessListener("Success");
        }
    }
}
