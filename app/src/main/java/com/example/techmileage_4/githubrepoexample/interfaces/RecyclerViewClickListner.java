package com.example.techmileage_4.githubrepoexample.interfaces;

import android.view.View;


public interface RecyclerViewClickListner {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
    //void onItemClick(View view, int position);
}