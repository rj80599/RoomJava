package com.example.roomdatabase.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    public @PrimaryKey(autoGenerate = true)
    int id;
    public String title;
    public String dsc;

    public Note(String title, String dsc) {
        this.title = title;
        this.dsc = dsc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public String getDsc() {
        return dsc;
    }

}
