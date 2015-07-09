package com.example.citruscircuits.pit_scouter_2015_android;

/**
 * Created by citruscircuits on 11/6/14.
 */

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Toaster {
    public static void makeToast(String text, int length)
    {
        Toast toast = Toast.makeText(MyApplication.getAppContext(), text, length);
        toast.show();
    }

    public static void makeErrorToast(String text, int length)
    {
        Toast toast = Toast.makeText(MyApplication.getAppContext(), text, length);
        TextView textVew = (TextView) toast.getView().findViewById(android.R.id.message);
        textVew.setTextColor(Color.RED);
        toast.show();
    }

    public static void makeWarningToast(String text, int length)
    {
        Toast toast = Toast.makeText(MyApplication.getAppContext(), text, length);
        TextView textVew = (TextView) toast.getView().findViewById(android.R.id.message);
        textVew.setTextColor(Color.YELLOW);
        toast.show();
    }

    public static void makeToastOnMainThread(final String text, final int length, final Activity context)
    {
        context.runOnUiThread(new Runnable()
        {

            @Override
            public void run()
            {
                makeToast(text, length);
            }
        });

    }

    public static void makeWarningToastOnMainThread(final String text, final int length, final Activity context)
    {
        context.runOnUiThread(new Runnable()
        {

            @Override
            public void run()
            {
                makeWarningToast(text, length);
            }
        });

    }

    public static void makeErrorToastOnMainThread(final String text, final int length, final Activity context)
    {
        context.runOnUiThread(new Runnable()
        {

            @Override
            public void run()
            {
                makeErrorToast(text, length);
            }
        });

    }
}
