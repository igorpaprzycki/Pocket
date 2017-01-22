package com.igypap.pocket.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.igypap.pocket.database.LinksApiFactory;
import com.igypap.pocket.model.Link;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditElementActivity extends CreateElementActivity {
    public static final String EXTRA_LINK = "link";
    private int mEditId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Link link = (Link) getIntent().getSerializableExtra(EXTRA_LINK);
        if (link == null) {
            finish();
            return;
        }

        mEditId = link.getId();
        mFormTitle.setText(link.getName());
        mFormReference.setText(link.getReference());
        mFormType.setSelection(getTypeIndex(link.getType()));
    }

    @Override
    protected void saveElement(String title, String reference) {
        LinksApiFactory.get().updateLink(mEditId, title, getSelectedType(), reference)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(EditElementActivity.this, "Edycja zakończona prawidłowo",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(EditElementActivity.this, "Błąd edycji !",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
