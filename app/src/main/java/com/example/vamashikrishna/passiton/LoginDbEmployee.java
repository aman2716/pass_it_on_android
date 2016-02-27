package com.example.vamashikrishna.passiton;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vamashikrishna on 2/26/2016.
 */
public class LoginDbEmployee {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT ,MySQLiteHelper.PASSWORD};

    public LoginDbEmployee(Context context) {

        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Login createLogin(String user,String password) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, user);
        values.put(MySQLiteHelper.PASSWORD, password);
        long insertId = database.insert(MySQLiteHelper.TABLE_LOGIN, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_LOGIN,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Login newLogin = cursorToLogin(cursor);
        cursor.close();
        return newLogin;
    }

    public void deleteComment(Login login) {
        long id = login.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_LOGIN, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }
    public List<Login> getAllDetails() {
        List<Login> details = new ArrayList<Login>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_LOGIN,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Login detail = cursorToLogin(cursor);
            details.add(detail);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return details;
    }

    private Login cursorToLogin(Cursor cursor) {
        Login login = new Login();
        login.setId(cursor.getLong(0));
        login.setUser(cursor.getString(1));
        login.setPassword(cursor.getString(2));
        return login;
    }
}
