package com.geektech.taskapp.ui.slideshow;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.taskapp.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private List<String> urls;
    private RecyclerView recyclerView;
    private ImageAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);


        initList();
        return root;
    }

    private void initList(){

        urls = new ArrayList<>();
        urls.add("https://images.freeimages.com/images/small-previews/05e/on-the-road-6-1384796.jpg");
        urls.add("https://www.bensound.com/bensound-img/november.jpg");
        urls.add("https://mirpozitiva.ru/uploads/posts/2016-08/medium/1472042903_31.jpg");
        urls.add("https://static8.depositphotos.com/1008939/939/i/450/depositphotos_9398452-stock-photo-lonesome-road.jpg");
        urls.add("https://millionstatusov.ru/pic/statpic/all/58e61335ec518.jpg");
        urls.add("https://millionstatusov.ru/pic/statpic/all/58e8e3cdc8fed.jpg");
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new ImageAdapter(urls);
        recyclerView.setAdapter(adapter);

    }
}