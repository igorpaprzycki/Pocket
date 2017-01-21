package com.igypap.pocket.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.igypap.pocket.R;
import com.igypap.pocket.adapter.LinksAdapter;
import com.igypap.pocket.database.LinkDatabase;
import com.igypap.pocket.database.SqliteLinkDatabase;
import com.igypap.pocket.model.Link;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PocketListActivity extends AppCompatActivity
        implements LinksAdapter.ActionListener, PopupMenu.OnMenuItemClickListener {
    @BindView(R.id.activity_pocket_list)
    RecyclerView mList;
    private Link mLink;

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
    public void onActionClick(View anchor, Link link) {
        mLink = link;
        if (link.getType() == Link.TYPE_LINK) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + link.getReference()));
            startActivity(intent);
        } else if (link.getType() == link.TYPE_PHONE) {
            PopupMenu menu = new PopupMenu(this, anchor);
            menu.inflate(R.menu.action_menu);
            menu.setOnMenuItemClickListener(this);
            menu.show();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_call) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mLink.getReference()));
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_sms) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + mLink.getReference()));
            intent.putExtra("sms_body", "Cześć " + mLink.getName());
            startActivity(intent);
        }
        return true;
    }
}
