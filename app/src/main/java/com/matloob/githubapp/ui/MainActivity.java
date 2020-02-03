package com.matloob.githubapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.matloob.githubapp.R;
import com.matloob.githubapp.databinding.ActivityMainBinding;
import com.matloob.githubapp.util.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements SetRepoListener {
    private static final String TAG = "MainActivity";
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    CommitsRecyclerAdapter commitsRecyclerAdapter;
    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        // Init view model
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
        // Init binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        binding.setRecyclerAdapter(commitsRecyclerAdapter);

        // Observe commits list liveData
        viewModel.getCommits().observe(this, commitResponses -> {
            if (commitResponses != null) {
                commitsRecyclerAdapter.setCommits(commitResponses);
            }
        });

        // Observe changes in Git repository
        viewModel.isRepoSet().observe(this, isRepoSet -> binding.setRepoSet(isRepoSet));

        // Observe error
        viewModel.getIsError().observe(this, error ->
        {
            if (error) {
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
        DialogFragment dialogFragment = new SearchDialog(viewModel.getRepository().getOwner(), viewModel.getRepository().getRepo());
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    /**
     * Called when search button is clicked
     */
    public void showDialog(View view) {
        showDialog();
    }

    /**
     * Set repository callback to
     *
     * @param owner {@link String} the owner of repo
     * @param repo  {@link String} repo name
     */
    @Override
    public void onSetNewRepo(String owner, String repo) {
        viewModel.setRepository(new MainViewModel.Repository(owner, repo));
        // Start loading commits
        viewModel.loadCommits();
    }
}
