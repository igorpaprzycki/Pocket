package com.igypap.pocket.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.igypap.pocket.R;
import com.igypap.pocket.api.LinksApiFactory;
import com.igypap.pocket.model.Link;

import java.util.regex.Pattern;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateElementActivity extends AppCompatActivity {
    @BindView(R.id.form_title)
    EditText mFormTitle;
    @BindView(R.id.form_type)
    Spinner mFormType;
    @BindView(R.id.form_reference)
    EditText mFormReference;
    @BindArray(R.array.link_types_mapping)
    int[] mTypesMapping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_element);
        ButterKnife.bind(this);

        handleSharing();

        //get the mapping array without ButterKnife
//        getResources().getIntArray(R.array.link_types_mapping);

    }


    private void handleSharing() {
        if (getIntent().hasExtra(Intent.EXTRA_TEXT)) {
            String url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            if (url.contains("\n")) {
                String[] parts = url.split("\n");
                url = parts[1];
                mFormTitle.setText(parts[0]);
            } else {
                String name = url.split("\\.")[1];
                mFormTitle.setText(name.toUpperCase());
            }
            mFormReference.setText(url);
            mFormType.setSelection(getTypeIndex((Link.TYPE_LINK)));
        }
    }

    @OnItemSelected(R.id.form_type)
    void onTypeChange() {
        int linkType = getSelectedType();

        if (linkType == Link.TYPE_PHONE) {
            mFormReference.setInputType(InputType.TYPE_CLASS_PHONE);
        } else if (linkType == Link.TYPE_LINK) {
            mFormReference.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
        }
    }

    protected int getSelectedType() {
        return mTypesMapping[mFormType.getSelectedItemPosition()];
    }

    @OnClick(R.id.form_save)
    void onSaveClick() {
        //1. Check if input name is not empty string
        String title = mFormTitle.getText().toString();
        if (title.length() < 3) {
            Toast.makeText(this, "Wpisz co najmniej 3 znakowy tytuł !", Toast.LENGTH_SHORT).show();
            return;
        }
        //2. Check:
        //2a. telephone number - if not to short and only (+) and numbers
        //2b. link - check if Uri.parse does not throw exception for input

        String reference = mFormReference.getText().toString().trim();
        if (getSelectedType() == Link.TYPE_PHONE) {
            if (!Pattern.compile("^\\+?\\d{3,}$").matcher(reference).matches()) {
                Toast.makeText(this, "Niepoprawny format numeru !", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (getSelectedType() == Link.TYPE_LINK) {
            try {
                Uri parsedUri = Uri.parse(reference);
                if (parsedUri.getScheme() == null || parsedUri.getScheme().isEmpty()) {
                    reference = "http://" + reference;
                }

            } catch (Throwable ex) {
                Toast.makeText(this, "Niepoprawny format adresu !", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        saveElement(title, reference);
    }

    protected void saveElement(String title, String reference) {
        LinksApiFactory.get().createLink(title, getSelectedType(), reference)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Toast.makeText(CreateElementActivity.this, "Dodano do bazy.",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            onFailure(call, null);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(CreateElementActivity.this, "Błąd dodawania do bazy !",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    protected int getTypeIndex(int type) {
        for (int i = 0; i < mTypesMapping.length; i++) {
            if (mTypesMapping[i] == type) {
                return i;
            }
        }
        return Link.TYPE_LINK;
    }
}
