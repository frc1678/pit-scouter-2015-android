package com.example.citruscircuits.pit_scouter_2015_android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by citruscircuits on 2/15/15.
 */
public class ZoomListener implements View.OnClickListener {
    MainActivity mainActivity;

    public ZoomListener(MainActivity activityParam) {
        mainActivity = activityParam;
    }

    @Override
    public void onClick(View view) {
        mainActivity.isStartingActivity = true;
        Log.e("test", "zoomListener");

        Log.e("test", "Clicked");
        ImageView photoView = (ImageView)view;

        int photoViewId = photoView.getId();
        String path = (String)mainActivity.photos.get(photoViewId);

        Drawable drawablePhoto = photoView.getDrawable();

        Bitmap bitmapPhoto = Bitmap.createBitmap(drawablePhoto.getIntrinsicWidth(), drawablePhoto.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapPhoto);
        drawablePhoto.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawablePhoto.draw(canvas);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmapPhoto.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] bytes = stream.toByteArray();

        Intent photoIntent = new Intent(MyApplication.getAppContext(), PhotosViewActivity.class);
        photoIntent.putExtra("path", path);
        photoIntent.putExtra("photo", bytes);
        photoIntent.putExtra("number", mainActivity.team.getNumber());
        photoIntent.putExtra("name", mainActivity.team.getName());
        mainActivity.startActivityForResult(photoIntent, Constants.PHOTOS_VIEW_REQUEST_CODE);
    }
}
