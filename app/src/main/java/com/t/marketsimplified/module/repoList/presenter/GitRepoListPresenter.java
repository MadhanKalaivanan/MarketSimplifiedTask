package com.t.marketsimplified.module.repoList.presenter;

import com.t.marketsimplified.module.repoList.model.GitRepoListRepository;
import com.t.marketsimplified.module.repoList.model.GitRepoListResponseModel;
import com.t.marketsimplified.module.repoList.view.IGitRepoListView;

import java.util.ArrayList;

public class GitRepoListPresenter implements IGitRepoPresenter.presenter, IGitRepoPresenter.model.OnFinishedListener {

    private IGitRepoListView iRestaurantListView;
    private IGitRepoPresenter.model model;

    public GitRepoListPresenter(IGitRepoListView iRestaurantListView) {
        this.iRestaurantListView = iRestaurantListView;
        model = new GitRepoListRepository();
    }

    @Override
    public void getList(int page) {
        if (page == 1) {
            iRestaurantListView.showProgress();
        }
        model.getRepoList(page, this);
    }

    @Override
    public void onFinished(ArrayList<GitRepoListResponseModel> response) {
        iRestaurantListView.hideProgress();
        if (response != null && response.size() > 0) {
            iRestaurantListView.restaurantListResponse(response);
        } else {
            iRestaurantListView.noRecord();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        iRestaurantListView.noRecord();
        iRestaurantListView.hideProgress();
    }

}
