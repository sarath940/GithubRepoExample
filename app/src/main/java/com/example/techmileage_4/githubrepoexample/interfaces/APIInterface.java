package com.example.techmileage_4.githubrepoexample.interfaces;



import com.example.techmileage_4.githubrepoexample.model.Contributors;
import com.example.techmileage_4.githubrepoexample.model.RepoModel;
import com.example.techmileage_4.githubrepoexample.model.SingleRepoDetails;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface APIInterface {

    @GET("search/repositories")
    Call<RepoModel> doGetListResources(@QueryMap Map<String, String> options);

    @GET("repos/{owner}/{repo}/contributors")
    Call<ArrayList<Contributors>> getContributors(@Path("owner") String fullname,@Path("repo") String repo);
    @GET("users/{users}/repos")
    Call<ArrayList<SingleRepoDetails>> getContributorRepos(@Path("users") String fullname);


}
