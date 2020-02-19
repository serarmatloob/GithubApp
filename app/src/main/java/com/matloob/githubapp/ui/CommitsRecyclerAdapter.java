package com.matloob.githubapp.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matloob.githubapp.databinding.CommitItemBinding;
import com.matloob.githubapp.models.CommitResponse;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Serar Matloob on 2/2/2020.
 */
public class CommitsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<CommitResponse> commits = new ArrayList<>();

    private MainViewListener mainViewListener;

    public CommitsRecyclerAdapter(MainViewListener mainViewListener) {
        this.mainViewListener = mainViewListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        CommitItemBinding binding = CommitItemBinding.inflate(inflater, parent, false);

        return new CommitsViewHolder(binding, mainViewListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CommitsViewHolder) holder).bindItems(commits.get(position));
    }

    @Override
    public int getItemCount() {
        return commits.size();
    }

    public void setCommits(List<CommitResponse> commits) {
        this.commits = commits;
        notifyDataSetChanged();
    }

    public class CommitsViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "CommitsViewHolder";
        CommitItemBinding binding;
        MainViewListener mainViewListener;

        CommitsViewHolder(CommitItemBinding binding, MainViewListener mainViewListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.mainViewListener = mainViewListener;
            Log.i(TAG, "CommitsViewHolder: " + mainViewListener);
        }

        void bindItems(CommitResponse commitResponse) {
            this.binding.setViewModel(commitResponse);
            this.binding.setClickListener(mainViewListener);
            this.binding.executePendingBindings();
        }
    }

}