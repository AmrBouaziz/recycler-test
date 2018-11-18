package com.sqz.recyclersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView mRecyclerView;
    private ArrayList<String> mDataset;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView mAddWithRefresh;
    private TextView mAddWithoutRefresh;
    private TextView mSetVisible;
    private TextView mSetGone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mDataset = new ArrayList<>();
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        mAddWithRefresh = findViewById(R.id.add_with_refresh);
        mAddWithRefresh.setOnClickListener(this);

        mAddWithoutRefresh = findViewById(R.id.add_without_refresh);
        mAddWithoutRefresh.setOnClickListener(this);

        mSetVisible = findViewById(R.id.visibility_visible);
        mSetVisible.setOnClickListener(this);

        mSetGone = findViewById(R.id.visibility_gone);
        mSetGone.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_with_refresh:
                mDataset.add(String.valueOf(System.currentTimeMillis()));
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.add_without_refresh:
                mDataset.add(String.valueOf(System.currentTimeMillis()));
                break;
            case R.id.visibility_visible:
                mRecyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.visibility_gone:
                mRecyclerView.setVisibility(View.GONE);
//                mRecyclerView.invalidate();
                break;
        }
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private ArrayList<String> mDataSet;


        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<String> myDataSet) {
            mDataSet = myDataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

            Log.v("test", "onCreateViewHolder");
            // create a new view
            TextView v = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Log.v("test", "onBindViewHolder");
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataSet.get(position));

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataSet.size();
        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;

            public MyViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }
        }
    }
}
