package project.luxnovel.Handler;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    public ArrayList<ModelAuthor> loadAuthor()
    {
        ArrayList<ModelAuthor> author_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Author",null);

        cursor.moveToFirst();
        do
        {
            ModelAuthor author = new ModelAuthor();

            author.setId(cursor.getInt(0));
            author.setName(cursor.getString(1));

            author_list.add(author);
        }
        while(cursor.moveToNext());

        cursor.close();
        database.close();

        return author_list;
    }

    public ArrayList<ModelAuthor> searchAuthor(String search_name)
    {
        ArrayList<ModelAuthor> author_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Author where Author_name='" + search_name + "'", null);

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                ModelAuthor author = new ModelAuthor();

                author.setId(cursor.getInt(0));
                author.setName(cursor.getString(1));

                author_list.add(author);
            }
            while (cursor.moveToNext());

            cursor.close();
        }
        else new AlertDialog.Builder(context).setTitle("Announcement").setMessage("There Is No Author With That Name").setPositiveButton(android.R.string.ok, (dialog, number) -> dialog.dismiss()).show();

        assert cursor != null;
        cursor.close();
        database.close();
        return author_list;
    }

    public boolean insertAuthor(ModelAuthor insert_author)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();

        values.put("id_Author", insert_author.getId());
        values.put("Author_name", insert_author.getName());

        int inserted_rows = (int) database.insert("Author",null,values);
        database.close();

        return inserted_rows > 0;
    }

    public boolean deleteAuthor(String delete_name)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String selection = "Author_name=?";
        String[] parameter = {delete_name};

        int deleted_rows = database.delete("Author", selection, parameter);
        database.close();

        return deleted_rows > 0;
    }

    public boolean updateAuthor(ModelAuthor update_author)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();

        values.put("id_Author", update_author.getId());
        values.put("Author_name", update_author.getName());

        int updated_rows = database.update("Author", values, "id_Author=?", new String[]{String.valueOf(update_author.getId())});
        database.close();

        return updated_rows > 0;
    }
}
