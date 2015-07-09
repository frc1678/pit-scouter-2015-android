package com.example.citruscircuits.pit_scouter_2015_android;

import java.util.ArrayList;
import java.util.TreeMap;

import io.realm.Realm;

/**
 * Created by citruscircuits on 10/23/14.
 */
public class Constants {
    public static final String APP_KEY = "fu1drprr1bha4zl";
    public static final String APP_SECRET = "x8f4ehb2qyk30r4";
    public static final String COMPETITION_CODE = "SVR";
    public static final String DBX_ROBOT_PHOTOS_PATH = "/Robot Photos/";
    public static final String DBX_REALM_DB_PATH = "/Database File/realm.realm";
    public static final String DBX_CHANGE_PACKETS_PATH = "/Change Packets/Unprocessed/";
    public static final int REQUEST_LINK_TO_DBX = 29;
    public static final int IMAGE_BUTTON_SIDE_PADDING = 30;
    public static final int IMAGE_BUTTON_VERTICAL_PADDING = 0;
    public static final int PHOTOS_VIEW_REQUEST_CODE = 300;
    public static final int PHOTOS_VIEW_RESULT_CODE = 1;
    public static final int LOAD_IMAGE_REQUEST_CODE = 2;
    public static final int PIT_REQUEST_CODE = 4;
    public static final int TEAM_SEARCH_REQUEST_CODE = 8;
    public static final int PROGRAMMING_LANGUAGE_REQUEST_CODE = 9;
    public static final int PIT_NOTES_REQUEST_CODE = 10;
    public static final String[] PIT_OPTIONS = {"1 - Horrendous", "2 - Messy", "3 - Decent", "4 - Great", "5 - Impeccable"};
    public static final String[] PROGRAMMING_LANGUAGE_OPTIONS = {"Java", "C++", "LabView", "Python", "Other"};
    public static final String PIT_TYPE = "pitOrganization";
    public static final String PROGRAMMING_LANGUAGE_TYPE = "programmingLanguage";
    public static final String PIT_NOTES_TYPE = "pitNotes";

    public static final String NUMBER_PROPERTY = "number";
    public static long DEFAULT_CACHE_SIZE = 0;

    public static int currentTeamNumber = -1;
    public static TreeMap<Integer, Integer> sortedTeamsToTransfer;
    public static int numPhotosAdded = 0;
    public static int numFiles = 0;
}
