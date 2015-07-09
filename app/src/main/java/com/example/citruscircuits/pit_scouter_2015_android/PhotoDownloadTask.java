package com.example.citruscircuits.pit_scouter_2015_android;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxFile;
import com.dropbox.sync.android.DbxFileInfo;
import com.dropbox.sync.android.DbxFileSystem;
import com.dropbox.sync.android.DbxPath;
import com.dropbox.sync.android.DbxRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by citruscircuits on 2/14/15.
 */
public class PhotoDownloadTask extends AsyncTask {
    DbxFileInfo fileInfo;
    MainActivity mainActivity;

    private static int activeThreadCount = 0;

    public static int getActiveThreadCount() {
        return activeThreadCount;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        activeThreadCount++;

        Log.e("test", "doInBackground");
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }

        DbxFileSystem dbxFs = (DbxFileSystem)params[0];
        fileInfo = (DbxFileInfo)params[1];
        mainActivity = (MainActivity)params[2];

        try {
            dbxFs.setMaxFileCacheSize(0);
            dbxFs.setMaxFileCacheSize(Constants.DEFAULT_CACHE_SIZE);
        } catch (DbxException.NotFound nfe) {
            Toaster.makeErrorToast("Folder does not exist...", Toast.LENGTH_LONG);
        } catch (DbxException e) {
            Toaster.makeErrorToast(e.getMessage(), Toast.LENGTH_LONG);
        }

        ImageView newImage = null;
        try {
            DbxFile currentPhoto = dbxFs.openThumbnail(fileInfo.path, DbxFileSystem.ThumbSize.L, DbxFileSystem.ThumbFormat.JPG);
            currentPhoto.update();
            InputStream imageStream = currentPhoto.getReadStream();

            newImage = new ImageView(MyApplication.getAppContext());

            newImage.setImageBitmap(BitmapFactory.decodeStream(imageStream));

            imageStream.close();
            currentPhoto.close();

            newImage.setOnClickListener(new ZoomListener(mainActivity));
            newImage.setId(newImage.generateViewId());

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(Constants.IMAGE_BUTTON_SIDE_PADDING, Constants.IMAGE_BUTTON_VERTICAL_PADDING, Constants.IMAGE_BUTTON_SIDE_PADDING, Constants.IMAGE_BUTTON_VERTICAL_PADDING);

            newImage.setLayoutParams(layoutParams);
            newImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } catch (DbxException e) {
            Toaster.makeErrorToast(e.getMessage(), Toast.LENGTH_LONG);
        } catch (DbxRuntimeException dbxre) {
            Toaster.makeErrorToast(dbxre.getMessage(), Toast.LENGTH_LONG);
        } catch (IOException ioe) {
            Toaster.makeErrorToast(ioe.getMessage(), Toast.LENGTH_LONG);
        }

        Log.e("test", "returning an image " + fileInfo.path.toString());
        return newImage;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o != null) {
            ImageView newImage = (ImageView)o;
            LinearLayout linearLayout = (LinearLayout) mainActivity.findViewById(R.id.photosView);

            mainActivity.photos.put(newImage.getId(), fileInfo.path.toString());
            Log.e("test", "ID is " + newImage.getId());
            Log.e("test", "path is " + fileInfo.path.toString());

            if (fileInfo.path.equals(Utils.getSelectedImagePath())) {
                linearLayout.addView(newImage, 0);
            } else {
                linearLayout.addView(newImage);
            }
            Log.e("test", "Photos are " + mainActivity.photos.toString());
        }


        activeThreadCount--;
    }
}
