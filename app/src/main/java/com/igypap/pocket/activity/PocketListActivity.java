package com.igypap.pocket.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.igypap.pocket.R;
import com.igypap.pocket.adapter.PocketLinkAdapter;
import com.igypap.pocket.database.LinkDatabase;
import com.igypap.pocket.database.SqliteLinkDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PocketListActivity extends AppCompatActivity {
    @BindView(R.id.activity_pocket_list)
    RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocket_list);
        ButterKnife.bind(this);

        //show list items horizontally one after another
        mList.setLayoutManager(new LinearLayoutManager(this));
        LinkDatabase database = new SqliteLinkDatabase(this);
        database.getLinks();

        LinkDatabase mDatabase = new SqliteLinkDatabase(this);
        PocketLinkAdapter mAdapter = new PocketLinkAdapter(mDatabase.getLinks());
        mList.setAdapter(mAdapter);

    }
}
