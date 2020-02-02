package com.matloob.githubapp.api;

import com.matloob.githubapp.models.CommitResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Serar Matloob on 2/1/2020.
 */
public interface CommitsApi {
    @GET("repos/{owner}/{repo}/commits")
    Call<List<CommitResponse>> getCommits(@Path("owner") String owner, @Path("repo") String repo);
}
