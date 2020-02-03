package com.matloob.githubapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.matloob.githubapp.api.CommitsApi;
import com.matloob.githubapp.models.CommitResponse;

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
    private CommitsApi commitsApi;
    private Repository repository = new Repository();
    private MutableLiveData<List<CommitResponse>> commits = new MutableLiveData<>();

    private MutableLiveData<Boolean> isError = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRepoSet = new MutableLiveData<>();

    @Inject
    public MainViewModel(CommitsApi commitsApi) {
        this.commitsApi = commitsApi;
    }

    public LiveData<List<CommitResponse>> getCommits() {
        return commits;
    }

    public LiveData<Boolean> getIsError() {
        return isError;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> isRepoSet() {
        return isRepoSet;
    }

    public Repository getRepository() {
        return repository;
    }

    /**
     * sets the repository
     *
     * @param repository a {@link Repository} instance
     */
    public void setRepository(Repository repository) {
        this.repository = repository;
        // Publish that repository is set
        isRepoSet.setValue(true);
    }

    /**
     * Loads the commits from commits API
     */
    public void loadCommits() {
        // Is currently loading
        isLoading.setValue(true);
        commitsApi.getCommits(repository.getOwner(), repository.getRepo()).enqueue(new Callback<List<CommitResponse>>() {
            @Override
            public void onResponse(@NotNull Call<List<CommitResponse>> call, @NotNull Response<List<CommitResponse>> response) {
                if (response.body() != null) {
                    commits.setValue(response.body());
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

    /**
     * Repository class model for Github repo owner and name
     */
    static class Repository {
        private String owner;
        private String repo;

        Repository(String owner, String repo) {
            this.owner = owner;
            this.repo = repo;
        }

        public Repository() {

        }

        String getOwner() {
            return owner;
        }

        String getRepo() {
            return repo;
        }
    }
}
