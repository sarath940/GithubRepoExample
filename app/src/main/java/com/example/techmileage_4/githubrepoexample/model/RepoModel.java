
package com.example.techmileage_4.githubrepoexample.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class RepoModel implements Serializable{

    @SerializedName("incomplete_results")
    private Boolean mIncompleteResults;
    @SerializedName("items")
    private ArrayList<Item> mItems;
    @SerializedName("total_count")
    private Long mTotalCount;

    public Boolean getIncompleteResults() {
        return mIncompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        mIncompleteResults = incompleteResults;
    }

    public ArrayList<Item> getItems() {
        return mItems;
    }

    public void setItems(ArrayList<Item> items) {
        mItems = items;
    }

    public Long getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(Long totalCount) {
        mTotalCount = totalCount;
    }

}
