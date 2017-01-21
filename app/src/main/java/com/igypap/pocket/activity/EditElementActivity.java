package com.igypap.pocket.activity;

import android.os.Bundle;

import com.igypap.pocket.model.Link;

public class EditElementActivity extends CreateElementActivity {
    public static final String EXTRA_ID = "id";
    private int mEditId;

    @Override
    protected void saveElement(String title, String reference) {
        Link link = new Link();
        link.setId(mEditId);
        link.setName(title);
        link.setReference(reference);
        link.setType(getSelectedType());
        mDatabase.update(link);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditId = getIntent().getIntExtra(EXTRA_ID, -1);
        Link link = mDatabase.getLink(mEditId);
        if (link == null) {
            finish();
            return;
        }
        mFormTitle.setText(link.getName());
        mFormReference.setText(link.getReference());
        mFormType.setSelection(getTypeIndex(link.getType()));
    }

    private int getTypeIndex(int type) {
        for (int i = 0; i < mTypesMapping.length; i++) {
            if (mTypesMapping[i] == type) {
                return i;
            }
        }
        return Link.TYPE_LINK;
    }
}
