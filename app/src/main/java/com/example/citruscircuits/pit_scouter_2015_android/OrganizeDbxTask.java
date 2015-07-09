package com.example.citruscircuits.pit_scouter_2015_android;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxFile;
import com.dropbox.sync.android.DbxFileInfo;
import com.dropbox.sync.android.DbxFileSystem;
import com.dropbox.sync.android.DbxPath;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

/**
 * Created by citruscircuits on 2/15/15.
 */
public class OrganizeDbxTask extends AsyncTask {
    MainActivity mainActivity;
    private static int activeThreadCount = 0;

    public static int getActiveThreadCount() {
        return activeThreadCount;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        activeThreadCount++;

        if (Looper.myLooper() == null) {
            Looper.prepare();
        }

        DbxFileSystem dbxFs = (DbxFileSystem)objects[0];
        mainActivity = (MainActivity)objects[1];
        int number = (Integer)objects[2];

        try {
            int i = 0;
            for (DbxFileInfo fileInfo : dbxFs.listFolder(Utils.getTeamPath(number))) {
                Log.e("test", "File to move is " + Utils.getDbxFileName(fileInfo.path));
                if (!Utils.getDbxFileName(fileInfo.path).equals("selectedImage")) {
                    i++;
                    dbxFs.move(fileInfo.path, Utils.pathFromFileNameForTeamWithNumber(number, "transfer_" + i + ".jpg"));
                }
            }

            for (DbxFileInfo fileInfo : dbxFs.listFolder(Utils.getTeamPath(number))) {
                if (Utils.getDbxFileName(fileInfo.path).contains("transfer")) {
                    dbxFs.move(fileInfo.path, Utils.getNewImagePathForTeam(number, dbxFs));
                }
            }
        } catch (DbxException dbxe) {
            Toaster.makeErrorToast("Error moving files...", Toast.LENGTH_LONG);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        activeThreadCount--;
        super.onPostExecute(o);

        Log.e("test", "There are no active threads. Answer: " + Utils.isNoActiveThreads());

        if (Utils.isNoActiveThreads()) {
            Log.e("test", "REORGANIZING");
//            mainActivity.getDbxImages();
        }
    }
}
