package project.luxnovel.Handler;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import project.luxnovel.Model.ModelUser;

public class HandlerUser extends SQLiteOpenHelper
{
    public String DB_NAME;
    public int DB_VERSION;
    public String path;
    public Context context;

    public HandlerUser(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);

        this.DB_NAME = name;
        this.context = context;
        this.DB_VERSION = version;
        assert context != null;
        this.path = context.getFilesDir() + "/db/" + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int old_version, int new_version)
    {

    }

    @SuppressLint("Range")
    public ModelUser loadAccount(String username)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from User where name_Login=?", new String[]{username});

        cursor.moveToFirst();
        ModelUser user = new ModelUser();

        user.setId(cursor.getInt(0));
        user.setUsername(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        user.setName(cursor.getString(4));
        user.setDob(cursor.getString(5));
        user.setGender(cursor.getString(6));

        cursor.close();
        database.close();
        return user;
    }

    public Cursor getCursorUser()
    {
        SQLiteDatabase database;

        database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        return database.rawQuery("select * from User", null);
    }

    public boolean insertAccount(ModelUser user)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();

        values.put("name_Login", user.getUsername());
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());

        long result = database.insert("User", null, values);
        if (result == -1)
        {
            Log.e("UserHandler", "Insert Account Successfully!");
            database.close();
            return true;
        }
        else
        {
            Log.d("UserHandler", "Insert Account Unsuccessfully!");
            database.close();
            return false;
        }
    }
}
