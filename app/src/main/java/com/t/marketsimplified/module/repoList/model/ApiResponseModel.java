package com.t.marketsimplified.module.repoList.model;

import java.util.ArrayList;

public class ApiResponseModel {

    private ArrayList<GitRepoListResponseModel> items;

    public ArrayList<GitRepoListResponseModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<GitRepoListResponseModel> items) {
        this.items = items;
    }
}
