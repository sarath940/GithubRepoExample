package com.example.techmileage_4.githubrepoexample.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.techmileage_4.githubrepoexample.R;
import com.example.techmileage_4.githubrepoexample.adapters.GridViewAdapater;
import com.example.techmileage_4.githubrepoexample.adapters.RepoListAdapter;
import com.example.techmileage_4.githubrepoexample.interfaces.APIClient;
import com.example.techmileage_4.githubrepoexample.interfaces.APIInterface;
import com.example.techmileage_4.githubrepoexample.model.Contributors;
import com.example.techmileage_4.githubrepoexample.model.GridItem;
import com.example.techmileage_4.githubrepoexample.model.Item;
import com.example.techmileage_4.githubrepoexample.model.RepoModel;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Techmileage-4 on 11/16/2017.
 */

public class FullRepoDetails extends AppCompatActivity {
    ImageView mImageView;
    APIInterface apiInterface;
    TextView repoDetailName, repoProjectDetails, repoDescrpition;
    private GridView mGridView;
    private GridViewAdapater mGridAdapter;
    private ArrayList<GridItem> mGridData;
    GridItem item;
    ArrayList<Contributors> contributor;
    Item itemsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repodetail);
        mImageView = findViewById(R.id.mImageView);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        repoDetailName = findViewById(R.id.repoDetailName);
        repoProjectDetails = findViewById(R.id.repoProjectDetailsLink);
        repoDescrpition = findViewById(R.id.repoDescrpition);
        mGridView = findViewById(R.id.gridView);
        mGridData = new ArrayList<>();
        contributor = new ArrayList<>();
        item = new GridItem();
        Bundle b = getIntent().getExtras();
         itemsList = (Item) b.getSerializable("repoData");
        repoDescrpition.setText(itemsList.getDescription());
        Picasso.with(this).load(itemsList.getOwner().getAvatarUrl()).into(mImageView);
        repoDetailName.setText("Name:"+itemsList.getFullName());
        repoProjectDetails.setText(itemsList.getSvnUrl());
        String CurrentString = itemsList.getFullName();
        String[] separated = CurrentString.split("/");
        Call<ArrayList<Contributors>> call = apiInterface.getContributors(separated[0], separated[1]);
        call.enqueue(new Callback<ArrayList<Contributors>>() {
            @Override
            public void onResponse(Call<ArrayList<Contributors>> call, Response<ArrayList<Contributors>> response) {
                contributor = new ArrayList<>();
                contributor = response.body();
                Log.d("sarath", "resp::" + response.body());

                for (int i = 0; i < contributor.size(); i++) {

                   String imageName=contributor.get(i).getAvatarUrl();
                   String textName=contributor.get(i).getLogin();
                    item = new GridItem();
                    item.setImage(imageName);
                    item.setName(textName);
                    mGridData.add(item);

                }



                mGridAdapter = new GridViewAdapater(FullRepoDetails.this, R.layout.grid_row_item, mGridData);
                mGridAdapter.setGridData(mGridData);
                mGridView.setAdapter(mGridAdapter);

            }


            @Override
            public void onFailure(Call<ArrayList<Contributors>> call, Throwable t) {
                call.cancel();
            }


        });
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent contributorIntent=new Intent(FullRepoDetails.this,ContributorActivity.class);
                contributorIntent.putExtra("nameRepo",contributor.get(position).getLogin());
                contributorIntent.putExtra("imageUrl",contributor.get(position).getAvatarUrl());
                startActivity(contributorIntent);
            }
        });
    }
}
