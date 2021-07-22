package com.enveriesagestudios.jfood_android.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.enveriesagestudios.jfood_android.activity.LoginActivity;

import java.util.HashMap;

/**
 * Session Manager Class
 * Description : This Class is to manage user session
 *
 * @author Ilham Mulya Rafid
 * @version 28.05.2020
 *
 * @param for every mutator and every variables that called in Constructor
 *
 * @return for every class with it's parameter
 *
 */

public class SessionManager {
    //Variable Declaration
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;

    //Defining used Variable
    private static final String PREF_NAME = "UserLoginSession";
    private static final String LOGIN_STATUS = "isLoggedIn";
    public static final String KEY_ID = "id";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NAME = "name";

    public SessionManager(Context _context) {
        this._context = _context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    //Creating Login Session Method
    public void createLoginSession(int userId, String name,String email) {
        editor.putInt(KEY_ID, userId);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_NAME, name);
        editor.putBoolean(LOGIN_STATUS, true);
        editor.apply();
    }

    //Mapping the user
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_ID, String.valueOf(sharedPreferences.getInt(KEY_ID, 0)));
        user.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
        return user;
    }

    //Getter
    public int getUserId(){
        return sharedPreferences.getInt(KEY_ID, 0);
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public String getUserName() {
        return sharedPreferences.getString(KEY_NAME, null);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(LOGIN_STATUS, false);
    }

    public void checkLogin() {
        if (getUserEmail() == null || getUserId() > 0) {
            Intent intent = new Intent(_context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(intent);
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
    }
}