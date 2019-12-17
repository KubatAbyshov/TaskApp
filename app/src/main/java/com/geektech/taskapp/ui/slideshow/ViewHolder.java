package com.geektech.taskapp.ui.slideshow;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.taskapp.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = imageView.findViewById(R.id.fs_imageview);
    }

    public void bind(ImageView imageView){
        imageView.setImageDrawable(imageView.getDrawable());
    }
}
