package com.matloob.githubapp.models;

public class GitRepository {
    private String owner;
    private String repo;

    public GitRepository(String owner, String repo) {
        this.owner = owner;
        this.repo = repo;
    }

    public GitRepository() {

    }

    public String getOwner() {
        return owner;
    }

    public String getRepo() {
        return repo;
    }
}