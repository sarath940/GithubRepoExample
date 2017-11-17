package com.example.techmileage_4.githubrepoexample.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.techmileage_4.githubrepoexample.R;
import com.example.techmileage_4.githubrepoexample.adapters.RepoListAdapter;
import com.example.techmileage_4.githubrepoexample.interfaces.APIClient;
import com.example.techmileage_4.githubrepoexample.interfaces.APIInterface;
import com.example.techmileage_4.githubrepoexample.interfaces.ItemClick;
import com.example.techmileage_4.githubrepoexample.model.Item;
import com.example.techmileage_4.githubrepoexample.model.RepoModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemClick {
    APIInterface apiInterface;
    EditText editTextSearch;
    RecyclerView repoListView;
    RepoListAdapter repoListAdapter;
    RepoModel resource;
ArrayList<Item> arrayListItems=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextSearch = findViewById(R.id.editTextSearch);
        repoListView = findViewById(R.id.recyclerView);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    Map<String, String> data = new HashMap<>();
                    data.put("q", s.toString());
                    data.put("sort", "stars");
                    data.put("order", "desc");
                    if (s != null && s.toString().length() >= 1) {
                        Call<RepoModel> call = apiInterface.doGetListResources(data);
                        call.enqueue(new Callback<RepoModel>() {
                            @Override
                            public void onResponse(Call<RepoModel> call, Response<RepoModel> response) {
                                if(response.body()!=null){
                                    resource = response.body();
                                    for (int i=0;i<resource.getItems().size();i++){
                                        if(arrayListItems.size() < 10) {
                                            // playerList.add
                                            arrayListItems.add(resource.getItems().get(i));
                                        }
                                    }


                                    repoListAdapter = new RepoListAdapter(getApplicationContext(), arrayListItems, MainActivity.this);
                                    LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                                    repoListView.setLayoutManager(llm);
                                    repoListView.setAdapter(repoListAdapter);
                                }



                            }

                            @Override
                            public void onFailure(Call<RepoModel> call, Throwable t) {
                                call.cancel();
                            }


                        });
                    } else {
                        arrayListItems.clear();
                        repoListAdapter.notifyDataSetChanged();
                    }
            }catch (Exception e){
                e.printStackTrace();
            }


            }


        });


    }

    @Override
    public void onItemClick(View v, int position) {
        Intent mIntent = new Intent(MainActivity.this, FullRepoDetails.class);
        Bundle b = new Bundle();
        b.putSerializable("repoData", resource.getItems().get(position));
        mIntent.putExtras(b);
        startActivity(mIntent);
    }

    @Override
    public void onItemLongClick(View v, int position) {

    }
}
