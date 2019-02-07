package com.swipetodismisscardview_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sonu on 19/09/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {

    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();


    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ItemViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.card_view_label);
        }
    }


    private ArrayList<String> stringArrayList;

    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
        this.context = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_item_row_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder viewHolder, int i) {
        viewHolder.textView.setText(stringArrayList.get(i));
    }


    @Override
    public int getItemCount() {
        return (null != stringArrayList ? stringArrayList.size() : 0);
    }


}