package com.t.marketsimplified.module.repoList.model;

import androidx.annotation.NonNull;

import com.t.marketsimplified.common.NetworkUtils;
import com.t.marketsimplified.module.ApiClient;
import com.t.marketsimplified.module.repoList.presenter.IGitRepoPresenter;
import com.t.marketsimplified.module.webClientHandler.GetRepoListAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitRepoListRepository implements IGitRepoPresenter.model {

    @Override
    public void getRepoList(int page, final OnFinishedListener onFinishedListener) {
        if (NetworkUtils.checkNetworkConnection()) {
            GetRepoListAPI getRestaurantListAPI = ApiClient.getClient().create(GetRepoListAPI.class);
            Call<ApiResponseModel> call = getRestaurantListAPI.getRepoList(page, 30, "language:assembly");
            call.enqueue(new Callback<ApiResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<ApiResponseModel> call, @NonNull Response<ApiResponseModel> response) {
                    if (response.isSuccessful() && response.body() != null && response.body().getItems() != null &&
                            response.body().getItems().size() > 0) {
                        onFinishedListener.onFinished(response.body().getItems());
                    } else {
                        onFinishedListener.onFailure(new Throwable());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ApiResponseModel> call, @NonNull Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        }
    }
}


