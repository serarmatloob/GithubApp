package com.matloob.githubapp.util;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.matloob.githubapp.ui.CommitsRecyclerAdapter;

/**
 * Created by Serar Matloob on 2/2/2020.
 */
public class BindingAdapters {

    @BindingAdapter("setVisibility")
    public static void setVisibility(View view, boolean visible) {
        if(visible){
            view.setVisibility(View.VISIBLE);
        }
        else {
            view.setVisibility(View.INVISIBLE);
        }
    }

    @BindingAdapter("setAdapter")
    public static void setAdapter(RecyclerView recyclerView, CommitsRecyclerAdapter commitsRecyclerAdapter){
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(commitsRecyclerAdapter);
    }
}
