package com.matloob.githubapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.matloob.githubapp.R;
import com.matloob.githubapp.databinding.ActivityMainBinding;
import com.matloob.githubapp.models.CommitResponse;
import com.matloob.githubapp.models.GitRepository;
import com.matloob.githubapp.ui.details.DetailsActivity;
import com.matloob.githubapp.util.SharedPreferencesUtil;
import com.matloob.githubapp.util.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainViewListener {

    // Tag
    private static final String TAG = "MainActivity";

    // ViewModel factory
    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    CommitsRecyclerAdapter commitsRecyclerAdapter;

    // ViewModel instance
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        // Init view model
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
        // Init binding
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.setAdapter(commitsRecyclerAdapter);

        // Observe error and show snackBar
        viewModel.getIsError().observe(this, error ->
        {
            if (error) {
                Log.i(TAG, "Error: " + getString(R.string.can_t_fetch_data));
                showSnackBar(R.string.can_t_fetch_data);
            }
        });
    }

    /**
     * Shows a snackBar message
     *
     * @param message a {@link Integer} of String resource
     */
    private void showSnackBar(@StringRes int message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinatorlayout), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Retry", view -> showDialog());
        snackbar.show();
    }

    /**
     * Shows the dialog fragment.
     */
    public void showDialog() {
        // Show dialog with saved owner and repo
        DialogFragment dialogFragment = new SearchDialog(new GitRepository(viewModel.getGitRepository().getOwner(), viewModel.getGitRepository().getRepo()));
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    /**
     * Set repository callback to
     *
     * @param gitRepository {@link GitRepository} the repo
     */
    @Override
    public void onSetNewRepo(GitRepository gitRepository) {
        // Set repo args
        viewModel.setGitRepository(gitRepository);
        // Start loading commits
        viewModel.loadCommits();
    }

    @Override
    public void onItemClick(CommitResponse commitResponse) {
        Intent intent = new Intent(this, DetailsActivity.class);

        Bundle bundle = new Bundle();

        bundle.putSerializable("object", commitResponse);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onSearchBtnClick(View view) {
        showDialog();
    }
}
