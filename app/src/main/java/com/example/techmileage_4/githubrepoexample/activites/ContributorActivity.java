package com.example.techmileage_4.githubrepoexample.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.techmileage_4.githubrepoexample.R;
import com.example.techmileage_4.githubrepoexample.adapters.ContributorListAdapter;
import com.example.techmileage_4.githubrepoexample.adapters.GridViewAdapater;
import com.example.techmileage_4.githubrepoexample.adapters.RepoListAdapter;
import com.example.techmileage_4.githubrepoexample.interfaces.APIClient;
import com.example.techmileage_4.githubrepoexample.interfaces.APIInterface;
import com.example.techmileage_4.githubrepoexample.interfaces.ItemClick;
import com.example.techmileage_4.githubrepoexample.model.Contributors;
import com.example.techmileage_4.githubrepoexample.model.GridItem;
import com.example.techmileage_4.githubrepoexample.model.SingleRepoDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Techmileage-4 on 11/17/2017.
 */

public class ContributorActivity extends AppCompatActivity implements ItemClick{
    ImageView contributorImageView;
    RecyclerView repoListView;
    APIInterface apiInterface;
    ArrayList<SingleRepoDetails> singleRepoDetails;
    ArrayList<String> mRepoUrlsList;
    ContributorListAdapter contributorListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contributor_layout);
        mRepoUrlsList=new ArrayList<>();
        repoListView=findViewById(R.id.repoList);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        contributorImageView=findViewById(R.id.contributorImageView);
        final Intent intent = getIntent();

        Call<ArrayList<SingleRepoDetails>> call = apiInterface.getContributorRepos(intent.getStringExtra("nameRepo"));
        call.enqueue(new Callback<ArrayList<SingleRepoDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<SingleRepoDetails>> call, Response<ArrayList<SingleRepoDetails>> response) {
                singleRepoDetails=response.body();
                Log.d("sarath","response:::"+response.body());
                Picasso.with(ContributorActivity.this).load(intent.getStringExtra("imageUrl")).into(contributorImageView);
                for (int i=0;i<singleRepoDetails.size();i++){
                    mRepoUrlsList.add(singleRepoDetails.get(i).getUrl());
                }
                if(singleRepoDetails !=null){
                    contributorListAdapter = new ContributorListAdapter(ContributorActivity.this, mRepoUrlsList,ContributorActivity.this);
                    LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    repoListView.setLayoutManager(llm);
                    repoListView.setAdapter(contributorListAdapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<SingleRepoDetails>> call, Throwable t) {
                call.cancel();
            }


        });
    }

    @Override
    public void onItemClick(View v, int position) {

    }

    @Override
    public void onItemLongClick(View v, int position) {

    }
}
