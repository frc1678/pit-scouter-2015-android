package com.example.citruscircuits.pit_scouter_2015_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class NotesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        final EditText notesField = (EditText)findViewById(R.id.notesField);
        Log.e("test", "Creating menu");
        notesField.setText(getIntent().getStringExtra("previousNotes"));
        notesField.setSelection(notesField.getText().length());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean toReturn = super.onOptionsItemSelected(item);

        Intent finishIntent = new Intent();
        EditText notesField = (EditText)findViewById(R.id.notesField);
        finishIntent.putExtra("result", notesField.getText().toString());
        setResult(RESULT_OK, finishIntent);
        finish();

        return toReturn;
    }
}
