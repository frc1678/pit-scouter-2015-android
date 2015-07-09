package com.example.citruscircuits.pit_scouter_2015_android;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxFile;
import com.dropbox.sync.android.DbxFileInfo;
import com.dropbox.sync.android.DbxFileSystem;
import com.dropbox.sync.android.DbxPath;
import com.example.citruscircuits.pit_scouter_2015_android.realm.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmQuery;

/**
 * Created by citruscircuits on 1/1/15.
 */
public class RealmUtils {
    private static DbxPath getNewChangePacketPath(String competition, int team, String type) {
        String packetFileName = competition + "-" + team + "-" + type + "_pitscout" + "|" + System.currentTimeMillis() + ".json";
        DbxPath path = new DbxPath(Constants.DBX_CHANGE_PACKETS_PATH + packetFileName);
        return path;
    }

    public static <T> void saveChangePacket(DbxFileSystem dbxFs, Activity activity, String competition, int team, String type, T change) {
        try {
            String fileName = getNewChangePacketPath(competition, team, type).toString().replace("/", "`");

            Log.e("test", "the filename is " + fileName);

            FileOutputStream changeFileStream = activity.openFileOutput(fileName, Context.MODE_PRIVATE);

            JSONObject changePacket = new JSONObject();
            changePacket.put("class", "Team");
            changePacket.put("uniqueKey", Constants.NUMBER_PROPERTY);
            changePacket.put("uniqueValue", team);

            JSONObject jsonChange = new JSONObject();
            jsonChange.put("keyToChange", "uploadedData." + type);
            jsonChange.put("valueToChangeTo", change);

            JSONArray changes = new JSONArray();
            changes.put(0, jsonChange);

            changePacket.put("changes", changes);

            byte[] bytes = changePacket.toString().getBytes();
            ByteArrayInputStream bytesStream = new ByteArrayInputStream(bytes);

            int b;

            while ((b = bytesStream.read()) != -1) {
                changeFileStream.write(b);
            }

            changeFileStream.close();

            Constants.numFiles++;
        } catch (DbxException dbxe) {
            Log.e("test", "Dbx messed up..." + dbxe.getMessage());
        } catch (JSONException je) {
            Log.e("test", "JSON messed up..." + je.getMessage());
        } catch (IOException ioe) {
            Log.e("test", "IO messed up..." + ioe.getMessage());
        }
    }

    public static boolean uploadChangePacket(DbxFileSystem dbxFs, Activity activity, String fileName) {

        File file = new File(activity.getFilesDir(), fileName);

        DbxFile changeFile = null;
        boolean didSucceed = false;
        try {
                if (!file.getName().contains("realm")) {
                    Log.e("test", "Uploading " + file.getName());
                    fileName = fileName.replace("`", "/");

                    changeFile = dbxFs.create(new DbxPath(fileName));

                    changeFile.writeFromExistingFile(file, false);

                    boolean didDelete = file.delete();

                    if (didDelete) {
                        Constants.numFiles--;
                    }

                    Log.e("test", "Did delete");
                }
        } catch (IOException ioe) {
            Toaster.makeErrorToast("Upload failed.", Toast.LENGTH_LONG);
            didSucceed = false;
        }

        if (changeFile != null) {
            changeFile.close();
        }

        return didSucceed;
    }
}
