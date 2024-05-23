package project.luxnovel.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import project.luxnovel.Model.User;

public class UserHandler extends SQLiteOpenHelper {
    public String DB_NAME;
    private int DB_VERSION;
    private String path;
    private Context context;

    public UserHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.DB_NAME = name;
        this.context = context;
        this.DB_VERSION = version;
        this.path = context.getFilesDir() + "/db/" + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @SuppressLint("Range")
    public ArrayList<User> loadAccount(String nameLogin) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE name_Login=?", new String[]{nameLogin});

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId_User(cursor.getInt(cursor.getColumnIndex("id_User")));
                user.setName_Login(cursor.getString(cursor.getColumnIndex("name_Login")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                user.setUserName(cursor.getString(cursor.getColumnIndex("username")));
                user.setDob(cursor.getString(cursor.getColumnIndex("dob")));
                user.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }

    public Cursor getDataUser() {
        SQLiteDatabase db;

        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("select * from User", null);
        return cursor;
    }

    public void AddAccount(User user) {
        SQLiteDatabase db;

        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put("name_Login", user.getName_Login());
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());

        long result = db.insert("User", null, values);
        if (result == -1) {
            Log.e("UserHandler", "Thêm tài khoản thất bại");
        } else {
            Log.d("UserHandler", "Thêm tài khoản thành công");
        }
        db.close();
    }

}
