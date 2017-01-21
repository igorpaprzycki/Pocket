package com.igypap.pocket.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.igypap.pocket.R;
import com.igypap.pocket.settings.SettingsPreferences;

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
    private SettingsPreferences mPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        mPrefs = new SettingsPreferences(this);
        mShowPhones.setChecked(mPrefs.isShowPhones());
        mShowLinks.setChecked(mPrefs.isShowLinks());
        selectSort(mPrefs.isSort());

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
        mPrefs.setShowLinks(mShowLinks.isChecked());
        mPrefs.setShowPhones(mShowPhones.isChecked());
        mPrefs.setSort(mSortGroup.getCheckedRadioButtonId() == R.id.settings_sort_descending);
        finish();
    }
}
