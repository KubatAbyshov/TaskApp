package com.geektech.taskapp.ui.slideshow;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.taskapp.R;
import com.geektech.taskapp.ui.home.TaskAdapter;

import java.util.List;

public class SFRAdapter extends RecyclerView.Adapter<ViewHolder>{

    private List<ImageView> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView = (android.widget.ImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fs_viewholder, parent, false);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
