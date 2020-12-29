package project.ty.aboutnotes;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class NoteListAdapter extends ListAdapter< Note, NoteViewHolder> {
    private final OnItemSelectedListener onItemSelectedListener;
    public NoteListAdapter(@NonNull DiffUtil.ItemCallback<Note> diffCallback, OnItemSelectedListener onItemSelectedListener) {
        super(diffCallback);
        this.onItemSelectedListener = onItemSelectedListener;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note current = getItem(position);
        holder.bind(current.getNote());
        holder.noteItemView.setOnClickListener(v -> {
            onItemSelectedListener.onItemSelected(current);
        });
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return NoteViewHolder.create(parent);
    }

    static class WordDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getNote().equals(newItem.getNote());
        }

    }

    interface OnItemSelectedListener {
        void onItemSelected(Note note);
    }

}
