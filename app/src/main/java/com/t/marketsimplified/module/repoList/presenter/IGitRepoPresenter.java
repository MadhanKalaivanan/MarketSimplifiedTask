package com.t.marketsimplified.module.repoList.presenter;


import com.t.marketsimplified.module.repoList.model.GitRepoListResponseModel;

import java.util.ArrayList;

public interface IGitRepoPresenter {
    interface presenter {
        void getList(int itemPosition);
    }

    interface model {
        void getRepoList(int itemPosition, IGitRepoPresenter.model.OnFinishedListener onFinishedListener);

        interface OnFinishedListener {
            void onFinished(ArrayList<GitRepoListResponseModel> gitRepoListResponseModelArrayList);

            void onFailure(Throwable t);
        }
    }

}
