package project.luxnovel.Handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import project.luxnovel.Model.ModelAuthor;

public class HandlerAuthor extends SQLiteOpenHelper
{
    String DB_NAME, path;
    int DB_VERSION;
    Context context;

    public HandlerAuthor(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
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

    public ModelAuthor findAuthor(int search_id)
    {
        ModelAuthor author = new ModelAuthor();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Author where id_Author = ?", new String[] {String.valueOf(search_id)});

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                author.setId(cursor.getInt(0));
                author.setName(cursor.getString(1));
            }
            while (cursor.moveToNext());
            cursor.close();
        }

        assert cursor != null;
        cursor.close();
        database.close();
        return author;
    }
}
