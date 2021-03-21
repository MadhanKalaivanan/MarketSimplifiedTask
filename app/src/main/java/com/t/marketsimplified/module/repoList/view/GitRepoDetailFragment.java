package com.t.marketsimplified.module.repoList.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.t.marketsimplified.R;
import com.t.marketsimplified.common.BaseFragment;
import com.t.marketsimplified.module.MainActivity;

public class GitRepoDetailFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_repository_detail, container, false);

        TextView name = view.findViewById(R.id.name);
        TextView description = view.findViewById(R.id.description);
        TextView type = view.findViewById(R.id.type);
        TextView command = view.findViewById(R.id.command);

        ImageView image = view.findViewById(R.id.image);

        name.setText(((MainActivity) getActivity()).gitRepoListResponseModel.getName());
        description.setText(((MainActivity) getActivity()).gitRepoListResponseModel.getDescription());
        type.setText(((MainActivity) getActivity()).gitRepoListResponseModel.getPrivate() ? "Private" : "Public");
        command.setText(((MainActivity) getActivity()).gitRepoListResponseModel.getCommand());

        if (((MainActivity) getActivity()).gitRepoListResponseModel.getOwner() != null) {
            Glide.with(getActivity()).setDefaultRequestOptions(new RequestOptions().error(R.drawable.ic_no_image)).load(((MainActivity) getActivity()).gitRepoListResponseModel.getOwner().getAvatar_url()).into(image);
        } else {
            Glide.with(getActivity()).load(R.drawable.ic_no_image).into(image);
        }

        return view;
    }


}
