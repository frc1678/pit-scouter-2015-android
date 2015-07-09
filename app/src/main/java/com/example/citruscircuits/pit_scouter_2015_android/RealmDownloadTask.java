package com.example.citruscircuits.pit_scouter_2015_android;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxFile;
import com.dropbox.sync.android.DbxFileSystem;
import com.example.citruscircuits.pit_scouter_2015_android.realm.Team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by citruscircuits on 2/15/15.
 */
public class RealmDownloadTask extends AsyncTask {

    MainActivity mainActivity;
    DbxFile realmFile;
    FileInputStream fis;
    private static ArrayList<RealmDownloadTask> threads = new ArrayList<RealmDownloadTask>();

    public static ArrayList<RealmDownloadTask> getActiveThreads() {
        return threads;
    }

    public static int getActiveThreadCount() {
        return threads.size();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        threads.add(this);

        if (Looper.myLooper() == null) {
            Looper.prepare();
        }

        DbxFileSystem dbxFs = (DbxFileSystem)params[0];
        mainActivity = (MainActivity)params[1];

        try {
            dbxFs.setMaxFileCacheSize(0);
            dbxFs.setMaxFileCacheSize(Constants.DEFAULT_CACHE_SIZE);

            Log.e("test", "The database path is " + Utils.getRealmPath().toString());
            realmFile = dbxFs.open(Utils.getRealmPath());

            try {
                Log.e("test", "Time to get readStream");
                fis = realmFile.getReadStream();
                Log.e("test", "Finished getting readStream");
            } catch (IOException ioe) {
                Toaster.makeErrorToast(ioe.getMessage(), Toast.LENGTH_LONG);
            }
        } catch (DbxException e) {
            Toaster.makeErrorToast(e.getMessage(), Toast.LENGTH_LONG);
            Log.e("test", "The error message is " + e.getMessage());
        }

        return fis;
    }

    @Override
    protected void onPostExecute(Object o) {
        threads.remove(this);
        super.onPostExecute(o);

        InputStream realmStream = (InputStream)o;
        try {
            if (mainActivity.realm != null) {
                mainActivity.realm.close();
            }
            if (fis != null) {
                Realm.deleteRealmFile(mainActivity, "realm.realm");
                OutputStream outputStream = mainActivity.openFileOutput("realm.realm", Context.MODE_PRIVATE);

                int b = 0;

                Log.e("test", "Time to start reading and writing!");
                while ((b = realmStream.read()) != -1) {
//                Log.e("test", "read! " + b);
                    outputStream.write(b);
                }

                for (String file : mainActivity.fileList()) {
                    Log.e("test", "The file is " + file);
                }

                Log.e("test", "File size is " + mainActivity.openFileInput("realm.realm").available());


                outputStream.close();
            } else {
                Log.e("test", "Nope. fis is null.");
            }
        } catch (IOException ioe) {
            Log.e("Test", "ERROR " + ioe.getMessage());
        } catch (NullPointerException npe) {
            Log.e("test", "File empty");
        }

        try {
            realmStream.close();
            realmFile.close();
        } catch (IOException ioe) {
            realmFile.close();
        } catch (NullPointerException npe) {
            //Do nothing
        }

        Log.e("test", "Setting new Realm");
        Team team = null;
        try {
            mainActivity.realm = Realm.getInstance(MyApplication.getAppContext(), "realm.realm");
            RealmQuery<Team> teamRealmQuery = mainActivity.realm.where(Team.class);
            teamRealmQuery.equalTo("number", Constants.currentTeamNumber);
            team = teamRealmQuery.findFirst();
            mainActivity.realm.refresh();
        } catch (RealmMigrationNeededException rmne) {
            Toaster.makeErrorToast("Need to migrate database...", Toast.LENGTH_LONG);
            mainActivity.realm = null;
        }


        if (team != null) {
            mainActivity.team = team;
        } else {
            mainActivity.getFirstTeam();
        }
        Log.e("test", "Current team number is " + Constants.currentTeamNumber);
        if (Constants.currentTeamNumber == -1) {
            mainActivity.updateTeam(false);
        } else {
            mainActivity.updateTeam(true);
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

        Log.e("test", "CANCELLED THREAD!");

        try {
            fis.close();
            Log.e("test", "Closed.");
        } catch (IOException ios) {
            Log.e("test", "Could not close!");
        }

        realmFile.close();
    }
}