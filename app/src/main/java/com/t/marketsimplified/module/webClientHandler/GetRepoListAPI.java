package com.t.marketsimplified.module.webClientHandler;


import com.t.marketsimplified.common.Constants;
import com.t.marketsimplified.module.repoList.model.ApiResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GetRepoListAPI {

    @Headers("user-key: 4feaa2167c4dc6beadf629319423bd4b")
    @GET(Constants.REPOSITORY)
    Call<ApiResponseModel> getRepoList(@Query("page") int page, @Query("per_page") int per_page, @Query("q") String q);

}
