package com.example.citruscircuits.pit_scouter_2015_android;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by citruscircuits on 12/7/14.
 */
public class ListViewActivity extends ListActivity {
    List<? extends Parcelable> options = new ArrayList<Parcelable>();
    Context context;
    ArrayList<Map<String, Boolean>> checked;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        options = getIntent().getParcelableArrayListExtra("options");
        context = this;

        setListAdapter(new ListAdapter() {

            @Override
            public boolean areAllItemsEnabled() {
                return true;
            }

            @Override
            public boolean isEnabled(int i) {
                return true;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return options.size();
            }

            @Override
            public Object getItem(int position) {
                return options.get(position);
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View rowView = view;


                if (rowView == null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    rowView = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
                }

                TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
                textView.setText(getItem(i) + "");
                return rowView;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
    }

    public void onListItemClick(ListView listView, View view, int position, long id) {
        Intent finishIntent = new Intent();
        TextView textView = (TextView)view.findViewById(android.R.id.text1);
        finishIntent.putExtra("result", textView.getText());
        setResult(RESULT_OK, finishIntent);
        finish();
    }
}
