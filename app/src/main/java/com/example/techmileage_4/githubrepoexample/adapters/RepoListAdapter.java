package com.example.techmileage_4.githubrepoexample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.techmileage_4.githubrepoexample.R;
import com.example.techmileage_4.githubrepoexample.interfaces.ItemClick;
import com.example.techmileage_4.githubrepoexample.model.Item;
import com.example.techmileage_4.githubrepoexample.model.RepoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Callback;

/**
 * Created by Techmileage-4 on 11/16/2017.
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    ItemClick curItem;
    Context mCxt;
    ArrayList<Item> itemArrayList;

    public RepoListAdapter(Context context, ArrayList<Item> itemList, ItemClick curItem) {
        this.curItem = curItem;
        mCxt = context;
        itemArrayList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_rowitem, null);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Item mItem = itemArrayList.get(position);
        holder.mWatcherCount.setText("Watcher Count: "+mItem.getWatchersCount().toString());
        holder.mCommitCount.setText("Commite Count: "+mItem.getForks().toString());
        holder.mFullName.setText("Full Name: "+mItem.getFullName());
        holder.mName.setText("Name: "+mItem.getName());

        Picasso.with(mCxt).load(mItem.getOwner().getAvatarUrl()).into(holder.avatarImage);
holder.mRowLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        curItem.onItemClick(view,position);
    }
});

    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarImage;
        TextView mFullName;
        TextView mName;
        TextView mWatcherCount;
        TextView mCommitCount;
        LinearLayout mRowLayout;

        public ViewHolder(View itemView) {

            super(itemView);
            mFullName = itemView.findViewById(R.id.mFullName);
            mName = itemView.findViewById(R.id.mName);
            mWatcherCount = itemView.findViewById(R.id.mWatchCount);
            mCommitCount = itemView.findViewById(R.id.mCommitCount);
            avatarImage = itemView.findViewById(R.id.mAvatarImage);
            mRowLayout = itemView.findViewById(R.id.mRowLayout);
        }
    }
}
