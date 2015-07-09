package com.example.citruscircuits.pit_scouter_2015_android;

import java.io.FileInputStream;
import java.lang.String;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.Display;
import android.graphics.Point;
import android.widget.Button;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.dropbox.sync.android.DbxAccountManager;
import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxFileInfo;
import com.dropbox.sync.android.DbxFileSystem;
import com.dropbox.sync.android.DbxPath;
import com.dropbox.sync.android.DbxFile;
import com.dropbox.sync.android.DbxRuntimeException;
import com.example.citruscircuits.pit_scouter_2015_android.realm.Team;
import com.example.citruscircuits.pit_scouter_2015_android.realm.UploadedTeamData;

import android.widget.ImageView.ScaleType;
import android.widget.ToggleButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {

    private DbxAccountManager mDbxAcctMgr;
    private DbxFileSystem dbxFs;
    public Map<Integer, Object> photos = new HashMap<Integer, Object>();
    Realm realm;
    int status;
    int uploadedImage;
    Map<Integer, Integer> teams = new HashMap<Integer, Integer>(); //Do not touch this. It is not pretty, but it works.
    public boolean isStartingActivity = false;
    public Team team;
    int numPhotosInCurrentPath;
    DbxFileSystem.PathListener currentPathListener;
    boolean isSettingUp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        Log.e("test", "Creating menu");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.organizeTeam) {
            try {
                if (realm != null || team != null || Constants.currentTeamNumber != -1) {
                    new OrganizeDbxTask().execute(dbxFs, this, Constants.currentTeamNumber);
                }
            } catch (NullPointerException npe) {
                Toaster.makeErrorToast("No teams...", Toast.LENGTH_SHORT);
            }

        } else if (id == R.id.refreshButton) {
            //new RealmDownloadTask().execute(dbxFs, this);
            getDbxImages();
        } else if (id == R.id.action_search) {
            if (!isStartingActivity) {
                transferToSearchActivity();
                isStartingActivity = true;
            }
        } else if (id == R.id.uploadToDbx) {
            uploadAllChangePackets();
        } else if (id == R.id.realm) {
            new RealmDownloadTask().execute(dbxFs, this);
        } else if (id == R.id.organizeDbx) {
            Utils.organizeAllTeams(dbxFs, this);
        }

        return super.onOptionsItemSelected(item);
    }

    public void uploadAllChangePackets() {
        String[] files = this.fileList();

        for (String file : files) {
            if (RealmFilesUploadTask.getActiveThreadCount() < Constants.numFiles) {
                new RealmFilesUploadTask().execute(dbxFs, this, file);
            }
        }
    }

    public void setup() {
        mDbxAcctMgr = DbxAccountManager.getInstance(getApplicationContext(), Constants.APP_KEY, Constants.APP_SECRET);

        if(!mDbxAcctMgr.hasLinkedAccount()) {
            mDbxAcctMgr.startLink(this, Constants.REQUEST_LINK_TO_DBX);
        } else {
            prepareDbxFs();
//            new RealmDownloadTask().execute(dbxFs, this);
            realm = Realm.getInstance(MyApplication.getAppContext(), "realm.realm");
            setupUI();
        }

        Constants.numFiles = countFiles();
    }

    public int countFiles() {
        return fileList().length;
    }

    public void setupUI() {
        Button addPhotoButton = (Button)findViewById(R.id.addPhotoButton);
        Button pitButton = (Button)findViewById(R.id.pitButton);
        Button programmingLanguageButton = (Button)findViewById(R.id.programmingLanguageButton);
        Button notesButton = (Button)findViewById(R.id.notes);

        addPhotoButton.setOnClickListener(clickListener);
        pitButton.setOnClickListener(clickListener);
        programmingLanguageButton.setOnClickListener(clickListener);
        notesButton.setOnClickListener(clickListener);

        updateTeam(Constants.currentTeamNumber != -1);
    }



    public void getFirstTeam() {
        try {
            RealmQuery<Team> firstTeamQuery = realm.where(Team.class);
            team = firstTeamQuery.findFirst();
            Constants.currentTeamNumber = team.getNumber();
        } catch (NullPointerException npe) {
            Log.e("test", npe.getMessage() + " is cause");
            Constants.currentTeamNumber = -1;
        }
    }

    public void updateTeam(boolean hasTeamsInRealm) {
        Log.e("test", "Updating team");
        if (hasTeamsInRealm) {
            try {
                if (!dbxFs.isFolder(Utils.getCurrentPath())) {
                    dbxFs.createFolder(Utils.getCurrentPath());
                }
            } catch (DbxException dbxe) {
                Toaster.makeErrorToast(dbxe.getMessage(), Toast.LENGTH_LONG);
            }

            RealmQuery<Team> realmQuery = realm.where(Team.class);
            realmQuery.equalTo("number", Constants.currentTeamNumber);
            team = realmQuery.findFirst();

            Log.e("test", "Adding PathListener for path " + Utils.getCurrentPath().toString());
            dbxFs.addPathListener(new DbxFileSystem.PathListener() {
                @Override
                public void onPathChange(DbxFileSystem dbxFileSystem, DbxPath dbxPath, Mode mode) {
                    Log.e("test", "Path changed!");
                    try {
                        Log.e("Test", "The numPhotosInCurrentPath is " + numPhotosInCurrentPath);
                        Log.e("test", "The number of files in the currentPath is " + dbxFs.listFolder(Utils.getCurrentPath()).size());
                        if (numPhotosInCurrentPath != dbxFs.listFolder(Utils.getCurrentPath()).size() && Utils.isNoActiveThreads() || dbxFs.listFolder(Utils.getCurrentPath()).size() == 1) {
                            Log.e("test", "Reloading images...");
                            getDbxImages();
                        }
                    } catch (DbxException dbxe) {
                        Toaster.makeErrorToast(dbxe.getMessage(), Toast.LENGTH_LONG);
                    }
                }
            }, Utils.getCurrentPath(), DbxFileSystem.PathListener.Mode.PATH_OR_DESCENDANT);

            if (Utils.isNoActiveThreads()) {
                Log.e("test", "Reloading images...z");
                getDbxImages();
            }
        }

        changeUITeamExistsStatus(hasTeamsInRealm);
    }

    public void changeUITeamExistsStatus(boolean isTeamsInRealm) {
        Button pitButton = (Button) findViewById(R.id.pitButton);
        Button addPhotosButton = (Button) findViewById(R.id.addPhotoButton);
        Button programmingLanguageButton = (Button) findViewById(R.id.programmingLanguageButton);
        Button notesButton = (Button) findViewById(R.id.notes);

        pitButton.setEnabled(isTeamsInRealm);
        addPhotosButton.setEnabled(isTeamsInRealm);
        programmingLanguageButton.setEnabled(isTeamsInRealm);
        notesButton.setEnabled(isTeamsInRealm);

        if (isTeamsInRealm) {
            this.setTitle("Team " + team.getNumber());
            try {
                this.getActionBar().setSubtitle(team.getName());
            } catch (Exception e) {
                this.getActionBar().setSubtitle("Error getting name...");
            }

            UploadedTeamData teamData = team.getUploadedData();
            String pit = teamData.getPitOrganization();
            String programmingLanguage = teamData.getProgrammingLanguage();

            if (pit != null && !pit.equals("")) {
                pitButton.setText("Pit: " + pit.charAt(0));
            } else {
                pitButton.setText(getResources().getString(R.string.pitButton));
            }

            if (currentPathListener != null) {
                dbxFs.removePathListenerForAll(currentPathListener);
            }

            if (programmingLanguage != null && !programmingLanguage.equals("")) {
                programmingLanguageButton.setText(programmingLanguage);
            } else {
                programmingLanguageButton.setText(getResources().getString(R.string.programmingLanguageButton));
            }

            notesButton.setText(R.string.pitNotes);

            isSettingUp = true;

            addPhotosButton.setText(R.string.addPhotoButton);
        } else {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.photosView);
            linearLayout.removeAllViews();

            String noTeams = "No teams";

            this.setTitle(noTeams);
            this.getActionBar().setSubtitle(noTeams);

            pitButton.setText(noTeams);
            addPhotosButton.setText(noTeams);
            programmingLanguageButton.setText(noTeams);
            notesButton.setText(noTeams);
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!isStartingActivity) {
                isStartingActivity = true;
                switch (view.getId()) {
                    case R.id.addPhotoButton:
                        Intent addPhotoIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        addPhotoIntent.putExtra("path", (String)photos.get(view.getId()));
                        startActivityForResult(addPhotoIntent, Constants.LOAD_IMAGE_REQUEST_CODE);
                        break;
                    case R.id.pitButton:
                        Intent pitIntent = new Intent(MainActivity.this, ListViewActivity.class);
                        pitIntent.putStringArrayListExtra("options", new ArrayList<String>(Arrays.asList(Constants.PIT_OPTIONS)));
                        pitIntent.putExtra("numerical", false);
                        startActivityForResult(pitIntent, Constants.PIT_REQUEST_CODE);
                        break;
                    case R.id.programmingLanguageButton:
                        Intent programmingLanguageIntent = new Intent(MainActivity.this, ListViewActivity.class);
                        programmingLanguageIntent.putStringArrayListExtra("options", new ArrayList<String>(Arrays.asList(Constants.PROGRAMMING_LANGUAGE_OPTIONS)));
                        programmingLanguageIntent.putExtra("numerical", false);
                        startActivityForResult(programmingLanguageIntent, Constants.PROGRAMMING_LANGUAGE_REQUEST_CODE);
                        break;
                    case R.id.notes:
                        Log.e("test", "Notes");
                        Intent notesIntent = new Intent(MainActivity.this, NotesActivity.class);
                        notesIntent.putExtra("previousNotes", team.getUploadedData().getPitNotes());
                        startActivityForResult(notesIntent, Constants.PIT_NOTES_REQUEST_CODE);
                        break;
                }
            }
        }
    };

    public void prepareDbxFs() {
        try {
            Log.e("test", "Setting up dbxFs");
            dbxFs = DbxFileSystem.forAccount(mDbxAcctMgr.getLinkedAccount());
            Constants.DEFAULT_CACHE_SIZE = dbxFs.getMaxFileCacheSize();
        } catch(DbxException.Unauthorized ue) {
            Toaster.makeErrorToast("Inadequate authorization...", Toast.LENGTH_LONG);
        } catch (DbxException e) {
            Toaster.makeErrorToast(e.getMessage(), Toast.LENGTH_LONG);
        }

        dbxFs.addSyncStatusListener(new DbxFileSystem.SyncStatusListener() {
            @Override
            public void onSyncStatusChange(DbxFileSystem dbxFileSystem) {
                try {
                    if (!dbxFs.getSyncStatus().upload.inProgress && !dbxFs.getSyncStatus().download.inProgress && status == -1 && uploadedImage == 1) {
                        uploadedImage = 0;
                    }

                    if (dbxFs.getSyncStatus().download.inProgress) {
                        status = 1;
                    } else if (dbxFs.getSyncStatus().upload.inProgress){
                        status = -1;
                    } else {
                        status = 0;
                    }
                } catch (DbxException dbxe) {
                    Toaster.makeErrorToast(dbxe.getMessage(), Toast.LENGTH_LONG);
                }
            }
        });
    }

    public void getDbxImages() {
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.photosView);
        linearLayout.removeAllViews();
        Constants.numPhotosAdded = 0;

        try {
            dbxFs.setMaxFileCacheSize(0);
            photos.clear();
            dbxFs.setMaxFileCacheSize(Constants.DEFAULT_CACHE_SIZE);
        } catch (DbxException.NotFound nfe) {
            Toaster.makeErrorToast("Folder does not exist...", Toast.LENGTH_LONG);
        } catch (DbxException e) {
            Toaster.makeErrorToast(e.getMessage(), Toast.LENGTH_LONG);
        }

        List<DbxFileInfo> photosInFolder = new ArrayList<DbxFileInfo>();
        try {
            photosInFolder = dbxFs.listFolder(Utils.getCurrentPath());
            numPhotosInCurrentPath = photosInFolder.size();
        } catch (DbxException.NotFound nfe) {
            Toaster.makeErrorToast("Folder does not exist...", Toast.LENGTH_LONG);
        } catch (DbxException e) {
            Toaster.makeErrorToast(e.getMessage(), Toast.LENGTH_LONG);
        }

        try {
            DbxFileInfo selectedImageInfo = dbxFs.getFileInfo(Utils.getSelectedImagePath());
            photosInFolder.remove(selectedImageInfo);
            if (PhotoDownloadTask.getActiveThreadCount() < numPhotosInCurrentPath) {
                new PhotoDownloadTask().execute(dbxFs, selectedImageInfo, this);
            } else {
                Log.e("test", "NO! TOO MANY THREADZ!!!");
            }
        } catch (DbxException dbxe) {
            Toaster.makeWarningToast("No selected image", Toast.LENGTH_SHORT);
        }

        Log.e("test", "Beginning to load images " + photosInFolder.size());
        for (DbxFileInfo photoInfo : photosInFolder) {
            Log.e("test", "Loading " + photoInfo.path);
            if (PhotoDownloadTask.getActiveThreadCount() < numPhotosInCurrentPath) {
                Log.e("test", "Downloading..." + photoInfo.path.toString());
                new PhotoDownloadTask().execute(dbxFs, photoInfo, this);
            } else {
                Log.e("test", "NO! TOO MANY THREADZ!!!");
            }
        }
    }

    public void transferToSearchActivity() {
        try {

            RealmQuery<Team> teamsQuery = realm.where(Team.class);
            RealmResults<Team> teamsResults = teamsQuery.findAll();

            try {
                for (Team team : teamsResults) {
                    int teamNumber = team.getNumber();
                    int numPhotos = 0;
                    if (dbxFs.isFolder(Utils.getTeamPath(teamNumber))) {
                        numPhotos = dbxFs.listFolder(Utils.getTeamPath(teamNumber)).size();
                    }

                    teams.put(teamNumber, numPhotos);
                }
            } catch (DbxException dbxe) {
                Toaster.makeErrorToast(dbxe.getMessage(), Toast.LENGTH_LONG);
            }

            Log.e("test", "The teams are " + teams.toString());

            TreeMap<Integer, Integer> sortedTeams = new TreeMap<Integer, Integer>(new Comparator<Integer>() {
                @Override
                public int compare(Integer integer1, Integer integer2) {
                    if (teams.get(integer1).compareTo(teams.get(integer2)) == 0) {
                        return integer1.compareTo(integer2);

                    } else {
                        return teams.get(integer1).compareTo(teams.get(integer2));
                    }
                }
            });

            for (Integer teamNumber : teams.keySet()) {
                sortedTeams.put(teamNumber, teams.get(teamNumber));
            }

            Constants.sortedTeamsToTransfer = sortedTeams;

            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivityForResult(intent, Constants.TEAM_SEARCH_REQUEST_CODE);
        } catch (Exception e) {
            Toaster.makeErrorToast("No teams...", Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        isStartingActivity = false;
        switch(requestCode) {
            case Constants.REQUEST_LINK_TO_DBX:
                if (resultCode == Activity.RESULT_OK) {
                    prepareDbxFs();
                    //new RealmDownloadTask().execute(dbxFs, this);
                    setupUI();
                } else {
                    Log.e("test", resultCode + "");
                    Toaster.makeErrorToast("Could not connect to Dropbox...", Toast.LENGTH_LONG);

                    Log.e("test", "Could not connect to Dbx");
                }
                break;
            case Constants.PHOTOS_VIEW_REQUEST_CODE:
                if (resultCode == Constants.PHOTOS_VIEW_RESULT_CODE) {
                    String pathString = data.getStringExtra("path");
                    Log.e("asfd", pathString);
                    DbxPath path = new DbxPath(pathString);
                    Log.e("test", "The path to the new selectedImage is " + data.getStringExtra("path"));
                    Toaster.makeToast("Syncing...", Toast.LENGTH_LONG);
                    if (!Utils.getSelectedImagePath().equals(path)) {
                        try {
                            Log.e("test", "Photos is " + photos.toString());
                            Log.e("test", "Selected path is " + Utils.getSelectedImagePath().toString());
                            if (photos.containsValue(Utils.getSelectedImagePath().toString())) {
                                Log.e("test", "Contains value");
                                dbxFs.move(Utils.getSelectedImagePath(), Utils.getCachePath());
                            }
                            dbxFs.move(path, Utils.getSelectedImagePath());
                        } catch (DbxException dbxe) {
                            Toaster.makeErrorToast(dbxe.getMessage(), Toast.LENGTH_LONG);
                        }

                        new OrganizeDbxTask().execute(dbxFs, this, Constants.currentTeamNumber);
                    }
                }
                break;
            case Constants.LOAD_IMAGE_REQUEST_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = data.getData();
                    Toaster.makeToast("Syncing...", Toast.LENGTH_LONG);

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                        Log.e("test", "The newImagePath is " + Utils.getNewImagePathForTeam(Constants.currentTeamNumber, dbxFs).toString());
                        DbxFile newImageFile = dbxFs.create(Utils.getNewImagePathForTeam(Constants.currentTeamNumber, dbxFs));
                        FileOutputStream imageOutStream = newImageFile.getWriteStream();

                        bitmap.compress(Bitmap.CompressFormat.JPEG, 15, imageOutStream);

                        imageOutStream.close();
                        newImageFile.close();

                        uploadedImage = 1;
                    } catch (FileNotFoundException fnfe) {
                        Toaster.makeErrorToast("Could not find image file...", Toast.LENGTH_LONG);
                    } catch (IOException ioe) {
                        Toaster.makeErrorToast(ioe.getMessage(), Toast.LENGTH_LONG);
                    }
                }
                break;
            case Constants.PIT_REQUEST_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    String result = data.getStringExtra("result");
                    Toaster.makeToast(result, 1000);
                    Button pitButton = (Button)findViewById(R.id.pitButton);
                    pitButton.setText("Pit: " + result.charAt(0));

                    RealmUtils.saveChangePacket(dbxFs, this, Constants.COMPETITION_CODE, Constants.currentTeamNumber, Constants.PIT_TYPE, result);
                    realm.beginTransaction();
                    team.getUploadedData().setPitOrganization(result);
                    realm.commitTransaction();
                }
                break;
            case Constants.TEAM_SEARCH_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    int newTeamNumber = data.getIntExtra("selectedTeam", 1678);
                    Log.e("test", "The new team is " + newTeamNumber);
                    Toaster.makeToast(newTeamNumber + "", Toast.LENGTH_SHORT);

                    Constants.currentTeamNumber = newTeamNumber;

                    Toaster.makeWarningToast("Refresh page if incorrect images are loaded...", Toast.LENGTH_SHORT);
                    setupUI();
                }
                break;
            case Constants.PROGRAMMING_LANGUAGE_REQUEST_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    String result = data.getStringExtra("result");
                    Toaster.makeToast(result, 1000);
                    Button programmingLanguageButton = (Button)findViewById(R.id.programmingLanguageButton);
                    programmingLanguageButton.setText(result);

                    RealmUtils.saveChangePacket(dbxFs, this, Constants.COMPETITION_CODE, Constants.currentTeamNumber, Constants.PROGRAMMING_LANGUAGE_TYPE, result);
                    realm.beginTransaction();
                    team.getUploadedData().setProgrammingLanguage(result);
                    realm.commitTransaction();
                }
                break;
            case Constants.PIT_NOTES_REQUEST_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    String result = data.getStringExtra("result");

                    RealmUtils.saveChangePacket(dbxFs, this, Constants.COMPETITION_CODE, Constants.currentTeamNumber, Constants.PIT_NOTES_TYPE, result);
                    realm.beginTransaction();
                    team.getUploadedData().setPitNotes(result);
                    realm.commitTransaction();
                }
                break;
        default:
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
