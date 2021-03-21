package com.t.marketsimplified.module.repoList.adapter;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.t.marketsimplified.R;
import com.t.marketsimplified.module.repoList.model.GitRepoListResponseModel;

import java.util.ArrayList;

public class GitRepoListAdapter extends RecyclerView.Adapter<GitRepoListAdapter.ViewHolder> {

    private ArrayList<GitRepoListResponseModel> gitRepoListResponseModelArrayList;
    private Context context;
    private IRecyclerViewClick iRecyclerViewClick;

    public GitRepoListAdapter(ArrayList<GitRepoListResponseModel> gitRepoListResponseModelArrayList, Context context, IRecyclerViewClick iRecyclerViewClick) {
        this.gitRepoListResponseModelArrayList = gitRepoListResponseModelArrayList;
        this.context = context;
        this.iRecyclerViewClick = iRecyclerViewClick;
    }

    public void notifyData(ArrayList<GitRepoListResponseModel> gitRepoListResponseModelArrayList) {
        this.gitRepoListResponseModelArrayList = gitRepoListResponseModelArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return gitRepoListResponseModelArrayList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_list_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        GitRepoListResponseModel results = gitRepoListResponseModelArrayList.get(holder.getAdapterPosition());
        holder.repoName.setText(results.getName());
        holder.repoDesc.setText(results.getDescription());
        holder.command.setText(results.getCommand());
        if (results.getOwner() != null) {
            Glide.with(context).setDefaultRequestOptions(new RequestOptions().error(R.drawable.ic_no_image)).load(results.getOwner().getAvatar_url()).into(holder.repoImage);
        } else {
            Glide.with(context).load(R.drawable.ic_no_image).into(holder.repoImage);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView repoName, repoDesc;
        private EditText command;
        private ImageView repoImage;

        ViewHolder(View v) {
            super(v);
            repoName = v.findViewById(R.id.name);
            repoDesc = v.findViewById(R.id.desc);
            command = v.findViewById(R.id.command);
            repoImage = v.findViewById(R.id.image);

            command.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    gitRepoListResponseModelArrayList.get(getAdapterPosition()).setCommand(editable.toString());
                }
            });

            v.findViewById(R.id.parent_layout).setOnClickListener(view -> iRecyclerViewClick.clickPosition(getAdapterPosition()));
        }
    }

}