package dk.nemprogrammering.android.listeapp;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class List extends AppCompatActivity {

    public static final String EXTRA_EDIT_ELEMENT_IS_NEW = "dk.nemprogrammering.android.EDIT_ELEMENT_IS_NEW";

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

        mAdapter.setOnItemClickListener((View view, int uid) -> {
            Intent intent = new Intent(this, EditListElement.class);
            startActivity(intent);
        });

        mRecyclerView.setAdapter(mAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.add_element:
                this.openNewElementActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openNewElementActivity()
    {
        Intent intent = new Intent(this, EditListElement.class);
        intent.putExtra(EXTRA_EDIT_ELEMENT_IS_NEW, true);
        startActivity(intent);
    }
}
