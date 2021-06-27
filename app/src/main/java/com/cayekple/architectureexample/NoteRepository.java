package com.cayekple.architectureexample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao mNoteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        mNoteDao = database.mNoteDao();
        allNotes = mNoteDao.getAllNotes();
    }

    public void insert(Note note){
        new InsertNoteAsyncTask(mNoteDao).execute(note);
    }
    public void update(Note note){
        new UpdateNoteAsyncTask(mNoteDao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(mNoteDao).execute(note);
    }
    public void deleteAllNote(){
        new DeleteAllNoteAsyncTask(mNoteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao mNoteDao;

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.mNoteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao mNoteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.mNoteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao mNoteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.mNoteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDao mNoteDao;

        private DeleteAllNoteAsyncTask(NoteDao noteDao){
            this.mNoteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.deleteAllNotes();
            return null;
        }
    }

}
