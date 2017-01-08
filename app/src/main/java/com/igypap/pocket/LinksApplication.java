package com.igypap.pocket;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by igypap on 08.01.17.
 */

public class LinksApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this);
        }

    }
}
