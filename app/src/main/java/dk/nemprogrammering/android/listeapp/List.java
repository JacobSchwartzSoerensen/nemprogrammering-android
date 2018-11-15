package dk.nemprogrammering.android.listeapp;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class List extends AppCompatActivity {

    private static final String TAG = "List";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.ListView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ListViewModel viewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        MyRecyclerViewAdapter mAdapter = new MyRecyclerViewAdapter();

        viewModel.getAllListElements().observe(this, entities -> mAdapter.setmDataset(entities));

        mRecyclerView.setAdapter(mAdapter);
    }
}
