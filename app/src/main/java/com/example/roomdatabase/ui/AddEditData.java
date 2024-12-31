package com.example.roomdatabase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.roomdatabase.databinding.ActivityAddEditDataBinding;

public class AddEditData extends AppCompatActivity {

    ActivityAddEditDataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAddEditDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String type = getIntent().getStringExtra("type");
        if (type.equals("update")){
            binding.title.setText(getIntent().getStringExtra("title"));
            binding.desc.setText(getIntent().getStringExtra("desc"));
            int id = getIntent().getIntExtra("id",0);

            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = binding.title.getText().toString();
                    String desc = binding.desc.getText().toString();
                    Intent intent = new Intent();
                    intent.putExtra("type","new");
                    intent.putExtra("title",title);
                    intent.putExtra("desc",desc);
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });

        } else {

            binding.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = binding.title.getText().toString();
                    String desc = binding.desc.getText().toString();
                    Intent intent = new Intent();
                    intent.putExtra("type", "new");
                    intent.putExtra("title", title);
                    intent.putExtra("desc", desc);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

}