package com.example.roomdatabase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.roomdatabase.databinding.ActivityMainBinding;
import com.example.roomdatabase.model.MyAdapter;
import com.example.roomdatabase.model.Note;
import com.example.roomdatabase.viewmodel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NoteViewModel noteViewModel;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory)
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(NoteViewModel.class);

        binding.actionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditData.class);
                intent.putExtra("type", "new");
                startActivityForResult(intent, 1);
            }
        });

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        binding.recycler.setAdapter(myAdapter);

        noteViewModel.getAllData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                myAdapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT) {
                    noteViewModel.delete(myAdapter.getData(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "removed", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, AddEditData.class);
                    intent.putExtra("type", "update");
                    intent.putExtra("id", myAdapter.getData(viewHolder.getAdapterPosition()).id);
                    intent.putExtra("title", myAdapter.getData(viewHolder.getAdapterPosition()).title);
                    intent.putExtra("desc", myAdapter.getData(viewHolder.getAdapterPosition()).dsc);
                    startActivityForResult(intent, 2);
                }
            }
        }).attachToRecyclerView(binding.recycler);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null) {
            String title = data.getStringExtra("title");
            String desc = data.getStringExtra("desc");
            Note note = new Note(title, desc);
            noteViewModel.insert(note);
        } else if (requestCode == 2 && data != null) {
            String title = data.getStringExtra("title");
            String desc = data.getStringExtra("desc");
            int id = data.getIntExtra("id",0);
            Note note = new Note(title, desc);

            Log.d("MainActivity", "Updating note with ID: " + id);
            note.setId(id);
            Log.d("MainActivity", "Updating note with title : " + note.title);
            noteViewModel.update(note);

            Toast.makeText(this, "upadted", Toast.LENGTH_SHORT).show();
        }
    }
}