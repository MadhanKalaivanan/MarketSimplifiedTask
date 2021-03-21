package com.t.marketsimplified.module.repoList.view;


import com.t.marketsimplified.common.IBaseView;
import com.t.marketsimplified.module.repoList.model.GitRepoListResponseModel;

import java.util.ArrayList;

public interface IGitRepoListView extends IBaseView {
    void restaurantListResponse(ArrayList<GitRepoListResponseModel> restaurantArrayList);

    void noRecord();
}
