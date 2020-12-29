package project.ty.aboutnotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class NoteViewHolder extends RecyclerView.ViewHolder {
    final TextView noteItemView;


    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        noteItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        noteItemView.setText(text);
    }

    static NoteViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new NoteViewHolder(view);
    }
}
