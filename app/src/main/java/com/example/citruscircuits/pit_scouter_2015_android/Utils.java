package com.example.citruscircuits.pit_scouter_2015_android;

import android.util.Log;
import android.widget.Toast;

import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxFile;
import com.dropbox.sync.android.DbxFileInfo;
import com.dropbox.sync.android.DbxFileSystem;
import com.dropbox.sync.android.DbxPath;
import com.example.citruscircuits.pit_scouter_2015_android.realm.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by citruscircuits on 1/1/15.
 */
public class Utils {
    static public String getDbxFileName(DbxPath dbxPath) {
        String path = dbxPath.toString();

        int lastSlash = path.lastIndexOf("/");
        int lastDot = path.lastIndexOf(".");

        Log.e("test", "File is " + path);
        String fileName = path.substring(lastSlash + 1, lastDot);

        return fileName;
    }

    public static List<DbxFileInfo> listFilesInDbx(DbxFileSystem dbxFs, DbxPath path) {
        List<DbxFileInfo> photosInFolder = new ArrayList<DbxFileInfo>();
        try {
            photosInFolder = dbxFs.listFolder(path);
        } catch (DbxException.NotFound nfe) {
            Toaster.makeErrorToast("Folder does not exist...", Toast.LENGTH_LONG);
        } catch (DbxException e) {
            Toaster.makeErrorToast(e.getMessage(), Toast.LENGTH_LONG);
        }

        return photosInFolder;
    }

    public static DbxPath getCurrentPath() {
        DbxPath currentPath = new DbxPath(Constants.DBX_ROBOT_PHOTOS_PATH + Constants.currentTeamNumber + "/");
        return currentPath;
    }

    public static DbxPath getSelectedImagePath() {
        DbxPath selectedImagePath = new DbxPath(Constants.DBX_ROBOT_PHOTOS_PATH + Constants.currentTeamNumber + "/selectedImage.jpg");
        return selectedImagePath;
    }

    public static DbxPath getChangePacketsPath() {
        DbxPath changePacketsPath = new DbxPath(Constants.DBX_CHANGE_PACKETS_PATH);
        return changePacketsPath;
    }

    public static DbxPath getRealmPath() {
        DbxPath realmPath = new DbxPath(Constants.DBX_REALM_DB_PATH);
        return realmPath;
    }

    public static DbxPath getTeamPath(int teamNumber) {
        DbxPath teamPath = new DbxPath(Constants.DBX_ROBOT_PHOTOS_PATH + teamNumber + "/");
        return teamPath;
    }

    public static DbxPath pathFromFileName(String fileName) {
        DbxPath newPath = new DbxPath(Utils.getCurrentPath().toString() + "/" + fileName + ".jpg");
        return newPath;
    }

    public static DbxPath pathFromFileNameForTeamWithNumber(int number, String fileName) {
        DbxPath newPath = new DbxPath(Utils.getTeamPath(number).toString() + "/" + fileName + ".jpg");
        return newPath;
    }

    public static DbxPath getCachePath() {
        DbxPath cachePath = pathFromFileName(Integer.MAX_VALUE + "");
        return cachePath;
    }

    public static boolean isNoActiveThreads() {
        boolean isNoActiveThreads = OrganizeDbxTask.getActiveThreadCount() == 0 && PhotoDownloadTask.getActiveThreadCount() == 0 && RealmDownloadTask.getActiveThreadCount() == 0;
        return isNoActiveThreads;
    }

    public static List<DbxFileInfo> fileInfosNamesAreNumbersInPath(DbxPath path, DbxFileSystem dbxFs) {
        List<DbxFileInfo> fileInfos;
        try {
            fileInfos = dbxFs.listFolder(path);
        } catch (DbxException dbxe) {
            fileInfos = new ArrayList<DbxFileInfo>();
        }

        ArrayList<DbxFileInfo> toRemove = new ArrayList<DbxFileInfo>();
        for (DbxFileInfo fileInfo : fileInfos) {
            try {
                Integer.parseInt(getDbxFileName(fileInfo.path));
            } catch (Exception e) {
                toRemove.add(fileInfo);
            }
        }

        fileInfos.removeAll(toRemove);

        return fileInfos;
    }

    public static DbxPath getNewImagePathForTeam(int number, DbxFileSystem dbxFs) {
        DbxPath newImagePath;
        try {
            if (dbxFs.isFolder(getTeamPath(number))) {
//                if (dbxFs.isFile(Utils.getSelectedImagePath())) {
                    newImagePath = new DbxPath(Constants.DBX_ROBOT_PHOTOS_PATH + number + "/" + (Utils.fileInfosNamesAreNumbersInPath(Utils.getTeamPath(number), dbxFs).size()) + ".jpg");
//                } else {
//                    newImagePath = new DbxPath(Constants.DBX_ROBOT_PHOTOS_PATH + number + "/" + dbxFs.listFolder(Utils.getTeamPath(number)).toArray().length + ".jpg");
//                }
            } else {
                newImagePath = new DbxPath(Constants.DBX_ROBOT_PHOTOS_PATH + number + "/0" + ".jpg");
            }

            return newImagePath;
        } catch (DbxException e) {
            Log.e("test", "The error in the newImagePath is " + e.getMessage());
            Toaster.makeErrorToast(e.getMessage(), Toast.LENGTH_LONG);
            newImagePath = new DbxPath(Constants.DBX_ROBOT_PHOTOS_PATH + new Random().nextInt() + ".jpg");

            return newImagePath;
        }
    }

    public static void organizeAllTeams(DbxFileSystem dbxFs, MainActivity ma) {
        RealmResults<Team> teams = ma.realm.allObjects(Team.class);

        Toaster.makeErrorToast("WAIT A VERY LONG TIME, THEN KILL THE APP AND OPEN IT AGAIN. I HOPE YOU KNOW WHAT YOU ARE DOING BECAUSE WHAT YOU JUST DID WAS VERY VERY VERY VERY VERY VERY DANGEROUS", Toast.LENGTH_LONG * 50);

        for (Team team : teams) {
            new OrganizeDbxTask().execute(dbxFs, ma, team.getNumber());
        }
    }
}
