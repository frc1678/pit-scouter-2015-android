package com.example.citruscircuits.pit_scouter_2015_android;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Looper;

import com.dropbox.sync.android.DbxFileSystem;

/**
 * Created by citruscircuits on 2/16/15.
 */
public class RealmFilesUploadTask extends AsyncTask {

    private static int activeThreadCount = 0;

    public static int getActiveThreadCount() {
        return activeThreadCount;
    }

    private String fileName;

    @Override
    protected Object doInBackground(Object[] objects) {
        activeThreadCount++;

        if (Looper.myLooper() == null) {
            Looper.prepare();
        }

        DbxFileSystem dbxFs = (DbxFileSystem)objects[0];
        Activity activity = (Activity)objects[1];
        fileName = (String)objects[2];

        RealmUtils.uploadChangePacket(dbxFs, activity, fileName);

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        activeThreadCount--;

        if (!fileName.contains("realm")) {
            Toaster.makeErrorToast("Failed to upload " + fileName, 200);
        }
    }
}
