package com.matloob.githubapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matloob.githubapp.api.CommitsApi;
import com.matloob.githubapp.models.CommitResponse;
import com.matloob.githubapp.models.GitRepository;
import com.matloob.githubapp.repository.Repository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Serar Matloob on 2/2/2020.
 */
public class MainViewModel extends ViewModel {
    // Retrofit api service instance
    private CommitsApi commitsApi;
    // Init empty epo
    private GitRepository gitRepository = new GitRepository();
    // Commits live data
    private LiveData<List<CommitResponse>> commits = new MutableLiveData<>();

    // Booleans for status
    private MutableLiveData<Boolean> isError = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRepoSet = new MutableLiveData<>();


    private Repository repository;

    @Inject
    MainViewModel(CommitsApi commitsApi, Repository repository) {
        this.commitsApi = commitsApi;
        this.repository = repository;
        if (repository.getGitRepository() != null) {
            loadCommits();
        }
    }

    public LiveData<List<CommitResponse>> getCommits() {
        return repository.getmAllCommits();
    }

    LiveData<Boolean> getIsError() {
        return isError;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> isRepoSet() {
        return isRepoSet;
    }

    GitRepository getGitRepository() {
        return gitRepository;
    }

    /**
     * sets the gitRepository
     *
     * @param gitRepository a {@link GitRepository} instance
     */
    void setGitRepository(GitRepository gitRepository) {
        this.gitRepository = gitRepository;
        repository.setGitRepository(gitRepository);
        // Notify that gitRepository is set.
        isRepoSet.setValue(true);
    }

    /**
     * Loads the commits from commits API
     */
    void loadCommits() {
        if (repository.getGitRepository() != null) {
            isRepoSet.setValue(true);
            // Is currently loading
            isLoading.setValue(true);
            commits = repository.getmAllCommits();
            commitsApi.getCommits(repository.getGitRepository().getOwner(), repository.getGitRepository().getRepo()).enqueue(new Callback<List<CommitResponse>>() {
                @Override
                public void onResponse(@NotNull Call<List<CommitResponse>> call, @NotNull Response<List<CommitResponse>> response) {
                    if (response.body() != null) {
                        repository.removeAll();
//                        commits.setValue(response.body());
                        for(CommitResponse commitResponse: response.body()){
                            repository.insert(commitResponse);
                        }
                        isError.setValue(false);
                    } else {
                        isError.setValue(true);
                    }
                    // Stopped loading
                    isLoading.setValue(false);
                }

                @Override
                public void onFailure(@NotNull Call<List<CommitResponse>> call, @NotNull Throwable t) {
                    isLoading.setValue(false);
                    isError.setValue(true);
                }
            });
        }

    }

}
