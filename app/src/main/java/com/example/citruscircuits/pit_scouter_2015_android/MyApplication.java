package com.example.citruscircuits.pit_scouter_2015_android;

/**
 * Created by citruscircuits on 11/6/14.
 */

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.dropbox.sync.android.DbxAccountManager;


import com.dropbox.sync.android.DbxAccountManager;

import io.realm.Realm;

public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
