package com.igypap.pocket.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.igypap.pocket.R;
import com.igypap.pocket.adapter.LinksAdapter;
import com.igypap.pocket.database.LinkDatabase;
import com.igypap.pocket.database.SqliteLinkDatabase;
import com.igypap.pocket.model.Link;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PocketListActivity extends AppCompatActivity
        implements LinksAdapter.ActionListener{
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
        LinksAdapter mAdapter = new LinksAdapter(mDatabase.getLinks(), this);
        mList.setAdapter(mAdapter);

    }

    @Override
    public void onActionClick(Link link) {
       if (link.getType() == Link.TYPE_LINK){
           Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+link.getReference()));
           startActivity(intent);
       }else if (link.getType() == link.TYPE_PHONE){
           Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+link.getReference()));
           startActivity(intent);
       }

    }
}
