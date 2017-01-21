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
import butterknife.OnClick;

/**
 * Created by igypap on 08.01.17.
 */

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.PocketViewHolder> {
    private List<Link> mLinks;
    private ActionListener mActionListener;

    public LinksAdapter(List<Link> mLinks, ActionListener mActionListener) {
        this.mLinks = mLinks;
        this.mActionListener = mActionListener;
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
        holder.mCurrentLink = item;

        holder.mLinkReference.setText(item.getReference());
        holder.mLinkTitle.setText(item.getName());
        if (item.getType() == Link.TYPE_PHONE) {
            holder.mLinkSymbol.setImageResource(android.R.drawable.ic_menu_call);
        } else if (item.getType() == Link.TYPE_LINK) {
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

        Link mCurrentLink;

        public PocketViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.link_symbol)
        void onSymbolClick(View clicked) {
            if (mActionListener != null) {
                mActionListener.onActionClick(clicked, mCurrentLink);
            }
        }
    }

    public interface ActionListener {
        void onActionClick(View anchor, Link link);
    }
}

// TODO: 08.01.17 po kliknieciu na ikonki otworzyc link albo wywolac telefon/sms
//wykonanie poprzez intenty niejawne
