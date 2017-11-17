package com.example.techmileage_4.githubrepoexample.adapters;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.techmileage_4.githubrepoexample.R;
import com.example.techmileage_4.githubrepoexample.model.GridItem;
import com.squareup.picasso.Picasso;

public class GridViewAdapater extends ArrayAdapter<GridItem> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<GridItem> mGridData = new ArrayList<GridItem>();

    public GridViewAdapater(Context mContext, int layoutResourceId, ArrayList<GridItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }



    /**
     * Updates grid data and refresh grid items.
     * @param mGridData
     */
    public void setGridData(ArrayList<GridItem> mGridData) {
        this.mGridData = mGridData;
        Log.d("sarath","size:::"+ mGridData.size());
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageView =  row.findViewById(R.id.imageView);
            holder.textView=row.findViewById(R.id.textName);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        GridItem item = mGridData.get(position);
        Picasso.with(mContext).load(item.getImage()).placeholder(R.drawable.placeholder).error(R.drawable.ic_launcher_background).into(holder.imageView);
        holder.textView.setText(item.getName());
        return row;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
