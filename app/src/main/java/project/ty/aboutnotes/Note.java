package project.ty.aboutnotes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "notes_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "notes")
    private String mNote;

    public Note(@NonNull String note){
        this.mNote = note;
    }

    public String getNote(){
        return this.mNote;
    }
}
