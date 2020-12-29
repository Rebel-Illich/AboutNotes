package project.ty.aboutnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final int MAIN_ACTIVITY_NOTE_REQUEST_CODE = 1;

    private NoteViewModel mNoteViewModel;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_input_counter);

        NodeViewModelFactory.createFactory(getApplication());
        NodeViewModelFactory viewModelFactory = NodeViewModelFactory.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final NoteListAdapter adapter = new NoteListAdapter(new NoteListAdapter.WordDiff(), note -> {
            mNoteViewModel.delete(note);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         // Get a new or existing ViewModel from the ViewModelProvider.
        mNoteViewModel = new ViewModelProvider(this, viewModelFactory).get(NoteViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mNoteViewModel.getAllWords().observe(this, notes -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(notes);
            textView.setText("Recorded " + mNoteViewModel.getRecordsCount() + " times; Deleted " + mNoteViewModel.getDeletions() + " times");

        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivityNote.class);
            startActivityForResult(intent, MAIN_ACTIVITY_NOTE_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MAIN_ACTIVITY_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            Note note = new Note(data.getStringExtra(MainActivityNote.EXTRA_REPLY));
            mNoteViewModel.insert(note);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}