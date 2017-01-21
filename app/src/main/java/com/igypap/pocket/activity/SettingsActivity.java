package com.igypap.pocket.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.igypap.pocket.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {
    @BindView(R.id.settings_sort)
    RadioGroup mSortGroup;
    @BindView(R.id.settings_sort_ascending)
    RadioButton mSortAscending;
    @BindView(R.id.settings_sort_descending)
    RadioButton mSortDescending;
    @BindView(R.id.show_phones)
    CheckBox mShowPhones;
    @BindView(R.id.show_links)
    CheckBox mShowLinks;
    private SharedPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        mPrefs = getSharedPreferences("settings", Context.MODE_PRIVATE);
        mShowPhones.setChecked(mPrefs.getBoolean("show_phones", true));
        mShowLinks.setChecked(mPrefs.getBoolean("show_links", true));
        selectSort(mPrefs.getBoolean("sort", false));

    }

    private void selectSort(boolean descending) {
        mSortGroup.check(descending ? R.id.settings_sort_descending : R.id.settings_sort_ascending);
    }

    @OnClick(R.id.settings_save)
    void onSaveClick() {
        if (!mShowLinks.isChecked() && !mShowPhones.isChecked()) {
            Toast.makeText(this, "Zaznacz conajmniej jeden typ wyświetlania!", Toast.LENGTH_SHORT).show();
            return;
        }
        mPrefs.edit()
                .putBoolean("show_phones", mShowPhones.isChecked())
                .putBoolean("show_links", mShowLinks.isChecked())
                .putBoolean("sort", mSortGroup.getCheckedRadioButtonId() == R.id.settings_sort_descending)
                .apply();
        finish();
    }
}
