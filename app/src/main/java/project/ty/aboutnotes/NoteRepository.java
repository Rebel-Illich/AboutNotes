package project.ty.aboutnotes;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import androidx.lifecycle.LiveData;

class NoteRepository {
     private NoteDao mNoteDao;
     private LiveData<List<Note>> mAllNotes;
    private final SharedPreferences preferences;
    private final SharedPreferences pref;

    NoteRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNoteDao = db.noteDao();
        mAllNotes = mNoteDao.getAlphabetizedWords();
        preferences = application.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        pref = application.getSharedPreferences(DELETIONS_FILE_NAME, Context.MODE_PRIVATE);
    }

    private static final String PREFERENCES_FILE_NAME = "notes_records";
    private static final String DELETIONS_FILE_NAME = "notes_deletions";
    private static final String RECORDS_COUNT = "RECORDS_COUNT";
    private static final String DELETIONS_COUNT = "DELETIONS_COUNT";


    void insert(Note note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.insert(note);
            int records = preferences.getInt(RECORDS_COUNT, 0);
            preferences.edit().putInt(RECORDS_COUNT, records + 1).apply();
        });
    }

    void delete(Note note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mNoteDao.delete(note);
            int delete = pref.getInt(DELETIONS_COUNT, 0);
            pref.edit().putInt(DELETIONS_COUNT, delete + 1).apply();
        });
    }

    int getRecordsCount() {
        return preferences.getInt(RECORDS_COUNT, 0);
    }

    int getDeletions() {
        return pref.getInt(DELETIONS_COUNT, 0);
    }

    public LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }
}
