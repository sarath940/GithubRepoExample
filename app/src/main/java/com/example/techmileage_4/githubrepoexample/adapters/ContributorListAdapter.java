package com.example.techmileage_4.githubrepoexample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.techmileage_4.githubrepoexample.R;
import com.example.techmileage_4.githubrepoexample.interfaces.ItemClick;
import com.example.techmileage_4.githubrepoexample.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Techmileage-4 on 11/16/2017.
 */

public class ContributorListAdapter extends RecyclerView.Adapter<ContributorListAdapter.ViewHolder> {

    ItemClick curItem;
    Context mCxt;
    ArrayList<String> repoArrayList;

    public ContributorListAdapter(Context context, ArrayList<String> repoArrayListArray, ItemClick curItem) {
        this.curItem = curItem;
        mCxt = context;
        repoArrayList = repoArrayListArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.repourl_row_item, null);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.repoName.setText(repoArrayList.get(position));
        holder.repoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curItem.onItemClick(view, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return repoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView repoName;
        LinearLayout repoLayout;

        public ViewHolder(View itemView) {

            super(itemView);
            repoName = itemView.findViewById(R.id.repoTextview);
            repoLayout = itemView.findViewById(R.id.repoLayout);
        }
    }
}
