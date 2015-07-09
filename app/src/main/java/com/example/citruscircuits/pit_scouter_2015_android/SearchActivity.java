package com.example.citruscircuits.pit_scouter_2015_android;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.citruscircuits.pit_scouter_2015_android.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SearchActivity extends ListActivity {
    TreeMap<Integer, Integer> teams;
    TreeMap<Integer, Integer> teamsSearched = new TreeMap<Integer, Integer>();
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teams = Constants.sortedTeamsToTransfer;

        Log.e("test", "The length of teams is " + teams.size());


        context = getApplicationContext();

        setContentView(R.layout.activity_list);

        EditText searchText = (EditText)findViewById(R.id.searchBar);
        searchText.setHint("Search teams...");
        //LinearLayout rootLayout = (LinearLayout)findViewById(R.id.rootLayout);
        searchText.clearFocus();

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchWithText(s.toString());
            }
        });

        searchWithText("");
        resetListAdapter();
    }

    public void resetListAdapter() {
        setListAdapter(new BaseAdapter() {

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
                return teamsSearched.size();
            }

            @Override
            public Object getItem(int i) {
                Log.e("test", "GETTING ITEM!");
                TreeMap<Integer, Integer> team = new TreeMap<Integer, Integer>();
                Integer teamNumber = (Integer) teamsSearched.keySet().toArray()[i];
                team.put(teamNumber, (Integer) teamsSearched.get(teamNumber));
                return team;
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
                    rowView = inflater.inflate(R.layout.activity_search, viewGroup, false);
                }

                TextView teamText = (TextView) rowView.findViewById(R.id.teamText);
                TreeMap<Integer, Integer> teamInfo = (TreeMap<Integer, Integer>) getItem(i);
                Integer teamNumber = (Integer) teamInfo.keySet().toArray()[0];
                teamText.setText(teamNumber.toString());

                TextView numPhotosText = (TextView) rowView.findViewById(R.id.photoCountText);
                Log.e("test", teamInfo.get(teamNumber).toString());
                Integer numPhotos = teamInfo.get(teamNumber);
                numPhotosText.setText(numPhotos.toString());
                if (numPhotos > 0) {
                    numPhotosText.setTextColor(Color.BLACK);
                } else {
                    numPhotosText.setTextColor(Color.RED);
                }

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

    public void searchWithText(String searchString) {
        teamsSearched.clear();

        teamsSearched = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer1, Integer integer2) {
                if(teams.get(integer1).compareTo(teams.get(integer2)) == 0) {
                    int compare = integer1.compareTo(integer2);
                    return compare;
                } else {
                    return teams.get(integer1).compareTo(teams.get(integer2));
                }
            }
        });

        for (Integer number : teams.keySet()) {
            if (number.toString().startsWith(searchString) || searchString.isEmpty()) {
                teamsSearched.put(number, teams.get(number));
            }
        }

        resetListAdapter();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. T
        // he action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TextView teamNumberView = (TextView) v.findViewById(R.id.teamText);
        String teamNumberText = teamNumberView.getText().toString();
        Integer selectedTeamNumber = Integer.parseInt(teamNumberText);
        Intent finishIntent = new Intent();
        finishIntent.putExtra("selectedTeam", selectedTeamNumber);
        setResult(Activity.RESULT_OK, finishIntent);
        finish();
    }
}
