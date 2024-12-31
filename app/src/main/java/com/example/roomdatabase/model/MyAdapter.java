package com.example.roomdatabase.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.databinding.ItemDataBinding;

public class MyAdapter extends ListAdapter<Note,MyAdapter.ViewHolder> {

    Context context;
    public MyAdapter(Context context) {
        super(CALLBACK);
        this.context =context;
    }

    private static final DiffUtil.ItemCallback<Note> CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.dsc.equals(newItem.dsc) && oldItem.title.equals(newItem.title) ;
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_data,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = getData(position);
        System.out.println("w12 > "+note.title);
        holder.binding.desc.setText(note.dsc);
        holder.binding.title.setText(note.title);

    }

    public Note getData(int position){
        return getItem(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemDataBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemDataBinding.bind(itemView);
        }
    }
}
