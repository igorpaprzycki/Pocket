package com.igypap.pocket.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.igypap.pocket.R;
import com.igypap.pocket.model.Link;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by igypap on 08.01.17.
 */

public class PocketLinkAdapter extends RecyclerView.Adapter<PocketLinkAdapter.PocketViewHolder> {
    private List<Link> mLinks;

    public PocketLinkAdapter(List<Link> mLinks) {
        this.mLinks = mLinks;
    }

    @Override
    public PocketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rowView = inflater.inflate(R.layout.row_pocket_item, parent, false);
        return new PocketViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(PocketViewHolder holder, int position) {
        Link item = mLinks.get(position);

        holder.mLinkReference.setText(item.getReference());
        holder.mLinkTitle.setText(item.getName());
        if (item.getType() == Link.TYPE_PHONE) {
            holder.mLinkSymbol.setImageResource(android.R.drawable.ic_menu_call);
        } else {
            holder.mLinkSymbol.setImageResource(android.R.drawable.ic_menu_share);
        }
    }

    @Override
    public int getItemCount() {
        return mLinks.size();
    }

    public class PocketViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.link_title)
        TextView mLinkTitle;
        @BindView(R.id.link_reference)
        TextView mLinkReference;
        @BindView(R.id.link_symbol)
        ImageView mLinkSymbol;

        public PocketViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

// TODO: 08.01.17 po kliknieciu na ikonki otworzyc link albo wywolac telefon/sms
//wykonanie poprzez intenty niejawne
