package com.t.marketsimplified.module.repoList.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t.marketsimplified.R;
import com.t.marketsimplified.common.BaseFragment;
import com.t.marketsimplified.module.MainActivity;
import com.t.marketsimplified.module.repoList.adapter.GitRepoListAdapter;
import com.t.marketsimplified.module.repoList.adapter.IRecyclerViewClick;
import com.t.marketsimplified.module.repoList.model.GitRepoListResponseModel;
import com.t.marketsimplified.module.repoList.presenter.GitRepoListPresenter;

import java.util.ArrayList;

public class GitRepoListFragment extends BaseFragment implements IGitRepoListView, IRecyclerViewClick {

    private GitRepoListAdapter repoListAdapter;
    private ArrayList<GitRepoListResponseModel> resultsArrayList = new ArrayList<>();
    private GitRepoListPresenter iRepoPresenter;
    private int pageCount = 1, totalItemCount = 0, recordCount = 30;
    private boolean isLoading;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            iRepoPresenter = new GitRepoListPresenter(this);
            callApi();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list);

        repoListAdapter = new GitRepoListAdapter(resultsArrayList, getActivity(), this);
        final LinearLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(repoListAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = gridLayoutManager.getChildCount();
                int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && totalItemCount >= recordCount && !isLoading) {
                    callApi();
                }
            }
        });

        progressBar = view.findViewById(R.id.bottom_progress_bar);


        return view;
    }

    private void callApi() {
        if (progressBar != null) {
            progressBar.setVisibility(pageCount > 1 ? View.VISIBLE : View.GONE);
        }
        iRepoPresenter.getList(pageCount);
        isLoading = true;
    }

    @Override
    public void clickPosition(int position) {
        ((MainActivity) getActivity()).gitRepoListResponseModel = resultsArrayList.get(position);
        ((MainActivity) getActivity()).openDetailFragment();
        ((MainActivity) getActivity()).bottomNavigationView.getMenu().getItem(1).setChecked(true);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void restaurantListResponse(ArrayList<GitRepoListResponseModel> restaurantArrayList) {
        resultsArrayList.addAll(restaurantArrayList);
        totalItemCount += recordCount;
        pageCount++;
        isLoading = false;
        repoListAdapter.notifyData(resultsArrayList);
    }

    @Override
    public void noRecord() {
        if (resultsArrayList.size() == 0) {
        }
    }


}
