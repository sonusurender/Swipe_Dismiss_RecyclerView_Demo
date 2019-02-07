package com.swipetodismisscardview_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CheckBox dismissLeftCheck, dismissRightCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* find check box ids */
        dismissLeftCheck = (CheckBox) findViewById(R.id.can_dismiss_left);
        dismissRightCheck = (CheckBox) findViewById(R.id.can_dismiss_right);

        setUpRecyclerView();
    }


    /*  set up recycler view */
    private void setUpRecyclerView() {

        //find reccycler view id and set layout manager
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //get string array list
        final ArrayList<String> stringArrayList = getListData();

        //set recycler view adapter
        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, stringArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        /*  set swipe touch listener */
        SwipeableRecyclerViewTouchListener swipeTouchListener = new SwipeableRecyclerViewTouchListener(recyclerView, new SwipeableRecyclerViewTouchListener.SwipeListener() {
            @Override
            public boolean canSwipeLeft(int position) {
                //enable/disable left swipe on checkbox base else use true/false
                return dismissLeftCheck.isChecked();
            }

            @Override
            public boolean canSwipeRight(int position) {
                //enable/disable right swipe on checkbox base else use true/false
                return dismissRightCheck.isChecked();
            }

            @Override
            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                //on cardview swipe left dismiss update adapter
                onCardViewDismiss(reverseSortedPositions, stringArrayList, recyclerViewAdapter);
            }

            @Override
            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                //on cardview swipe right dismiss update adapter
                onCardViewDismiss(reverseSortedPositions, stringArrayList, recyclerViewAdapter);
            }
        });

        //add item touch listener to recycler view
        recyclerView.addOnItemTouchListener(swipeTouchListener);

    }

    /**
     * method to update array list and adapter on card view swipe dismiss left or right
     *
     * @param stringArrayList        containing list of data
     * @param recyclerViewAdapter    adapter placed over recycler view
     * @param reverseSortedPositions An array of positions to dismiss, sorted in descending
     *                               order for convenience.
     **/
    private void onCardViewDismiss(int[] reverseSortedPositions, ArrayList<String> stringArrayList, RecyclerViewAdapter recyclerViewAdapter) {
        for (int position : reverseSortedPositions) {
            stringArrayList.remove(position);
            recyclerViewAdapter.notifyItemRemoved(position);
        }
        Toast.makeText(this, getResources().getString(R.string.card_view_dismiss_success), Toast.LENGTH_SHORT).show();
        recyclerViewAdapter.notifyDataSetChanged();
    }

    /**
     * method to return dummy string array list data
     **/
    private ArrayList<String> getListData() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 1; i <= 20; i++)
            stringArrayList.add("Card View List Item : " + i);
        return stringArrayList;
    }
}
