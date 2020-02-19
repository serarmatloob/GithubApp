package com.matloob.githubapp.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.matloob.githubapp.R;
import com.matloob.githubapp.models.GitRepository;

import javax.inject.Inject;

import dagger.android.support.DaggerDialogFragment;

/**
 * Created by Serar Matloob on 2/2/2020.
 */
public class SearchDialog extends DaggerDialogFragment {
    @Inject
    MainViewListener mainViewListener;

    private GitRepository gitRepository;

    public SearchDialog() {
        // Required empty constructor
    }

    SearchDialog(GitRepository gitRepository) {
        this.gitRepository = gitRepository;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.set_repo_dialog, null);
        EditText ownerEditText = dialogView.findViewById(R.id.owner);
        EditText repoEditText = dialogView.findViewById(R.id.repo);
        // Get text from whatever last saved in view model and set it to edit text.
        ownerEditText.setText(gitRepository.getOwner());
        repoEditText.setText(gitRepository.getRepo());
        // Return alert dialog to get input from user.
        return new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .setTitle(R.string.search)
                .setPositiveButton("OK", (dialogInterface, i) ->
                        mainViewListener.onSetNewRepo(new GitRepository(ownerEditText.getText().toString(), repoEditText.getText().toString())))
                .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss()).create();
    }
}
