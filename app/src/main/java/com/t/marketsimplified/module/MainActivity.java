package com.t.marketsimplified.module;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.t.marketsimplified.R;
import com.t.marketsimplified.common.BaseActivity;
import com.t.marketsimplified.common.ToastMessage;
import com.t.marketsimplified.module.repoList.model.GitRepoListResponseModel;
import com.t.marketsimplified.module.repoList.view.GitRepoDetailFragment;
import com.t.marketsimplified.module.repoList.view.GitRepoListFragment;

public class MainActivity extends BaseActivity {

    private String fragmentName = "ListFragment";
    private FragmentManager fm;
    private FragmentTransaction ft;
    public GitRepoListResponseModel gitRepoListResponseModel;
    public BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            String title = item.getTitle().toString();
            if (title.equals(getString(R.string.home))) {
                openListFragment();
            } else if (title.equals(getString(R.string.detail))) {
                openDetailFragment();
            }
            return true;
        });

        openListFragment();
    }

    private void openListFragment() {
        fragmentName = "ListFragment";
        fragmentTransaction(new GitRepoListFragment());
    }

    public void openDetailFragment() {
        if (gitRepoListResponseModel == null) {
            ToastMessage.toast("Select any repository");
        } else {
            fragmentName = "DetailFragment";
            fragmentTransaction(new GitRepoDetailFragment());
        }
    }

    private void fragmentTransaction(Fragment fragment) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        Fragment oldFragment = isPresent();
        if (oldFragment == null) {
            ft.replace(R.id.frame_layout, fragment, fragmentName);
            ft.addToBackStack(fragmentName);
        } else {
            ft.replace(R.id.frame_layout, oldFragment);
        }
        ft.commit();
    }

    private Fragment isPresent() {
        Fragment old;
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
            if (fm.getBackStackEntryAt(i).getName().equals(fragmentName)) {
                old = fm.findFragmentByTag(fragmentName);
                return old;
            }
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        if (fragmentName.equals("DetailFragment")) {
            openListFragment();
            bottomNavigationView.getMenu().getItem(0).setChecked(true);
        } else {
            finish();
        }
    }
}