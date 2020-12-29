package project.ty.aboutnotes;

import android.app.Application;

import java.util.Collections;
import java.util.List;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NoteViewModel extends ViewModel {

    private NoteRepository mRepository;

    private final LiveData<List<Note>> mAllNotes;

    public NoteViewModel(Application application) {
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }


    LiveData<List<Note>> getAllWords() { return mAllNotes; }

    public void insert(Note note) { mRepository.insert(note); }

    public void delete(Note note) { mRepository.delete(note); }

    public int getRecordsCount() {
        return mRepository.getRecordsCount();
    }

    public int getDeletions() { return mRepository.getDeletions(); }
    }
