package com.example.roomdatabase.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomdatabase.model.Note;
import com.example.roomdatabase.model.NoteDao;
import com.example.roomdatabase.model.NoteDataBase;

import java.util.List;

public class NoteRepo {
    NoteDao noteDao;
    LiveData<List<Note>> listData;

    public NoteRepo(Application application) {
        NoteDataBase noteDataBase = NoteDataBase.getInstance(application);
        noteDao = noteDataBase.noteDao();
        listData = noteDao.getAllData();
    }

    public LiveData<List<Note>> getAllData() {
        return listData;
    }

    public void insertData(Note note) {
        new InsertTask(noteDao).execute(note);
    }

    private static class InsertTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;

        public InsertTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    // delete
    public void deleteData(Note note) {
        new DeleteTask(noteDao).execute(note);
    }

    private static class DeleteTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;

        public DeleteTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    // update
    public void updateData(Note note) {
        new UpdateTask(noteDao).execute(note);
    }

    private static class UpdateTask extends AsyncTask<Note, Void, Void> {
        NoteDao noteDao;

        public UpdateTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
}
