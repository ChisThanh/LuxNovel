package project.luxnovel.Handler;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import project.luxnovel.Model.ModelChapter;

public class HandlerChapter extends SQLiteOpenHelper
{
    String DB_NAME, path;
    int DB_VERSION;
    Context context;

    public HandlerChapter(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        this.DB_NAME=name;
        this.context = context;
        this.DB_VERSION=version;
        assert context != null;
        this.path=context.getFilesDir() + "/db/" + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int old_version, int new_version)
    {

    }

    public ArrayList<ModelChapter> loadChapter()
    {
        ArrayList<ModelChapter> chapter_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Chapter",null);

        cursor.moveToFirst();
        do
        {
            ModelChapter chapter = new ModelChapter();

            chapter.setId(cursor.getInt(0));
            chapter.setNovel(cursor.getInt(1));
            chapter.setSerial(cursor.getInt(2));
            chapter.setName(cursor.getInt(3));
            chapter.setContent(cursor.getInt(4));
            chapter.setDate(cursor.getString(5));

            chapter_list.add(chapter);
        }
        while(cursor.moveToNext());

        cursor.close();
        database.close();

        return chapter_list;
    }

    public ArrayList<ModelChapter> searchChapter(String search_name)
    {
        ArrayList<ModelChapter> chapter_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Chapter where Chapter_name='" + search_name + "'", null);

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                ModelChapter chapter = new ModelChapter();

                chapter.setId(cursor.getInt(0));
                chapter.setNovel(cursor.getInt(1));
                chapter.setSerial(cursor.getInt(2));
                chapter.setName(cursor.getInt(3));
                chapter.setContent(cursor.getInt(4));
                chapter.setDate(cursor.getString(5));

                chapter_list.add(chapter);
            }
            while (cursor.moveToNext());

            cursor.close();
        }
        else new AlertDialog.Builder(context).setTitle("Announcement").setMessage("There Is No Chapter With That Name").setPositiveButton(android.R.string.ok, (dialog, number) -> dialog.dismiss()).show();

        assert cursor != null;
        cursor.close();
        database.close();
        return chapter_list;
    }

    public boolean insertChapter(ModelChapter insert_chapter)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();

        values.put("id_Chapter", insert_chapter.getId());
        values.put("id_Novel", insert_chapter.getNovel());
        values.put("Numerical", insert_chapter.getSerial());
        values.put("Chapter_name", insert_chapter.getName());
        values.put("Chapter_content", insert_chapter.getContent());
        //noinspection SpellCheckingInspection
        values.put("dateSubmiteted", insert_chapter.getDate());

        int inserted_rows = (int) database.insert("Novel",null,values);
        database.close();

        return inserted_rows > 0;
    }

    public boolean deleteChapter(String delete_name)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String selection = "Chapter_name=?";
        String[] parameter = {delete_name};

        int deleted_rows = database.delete("Chapter", selection, parameter);
        database.close();

        return deleted_rows > 0;
    }

    public boolean updateChapter(ModelChapter update_chapter)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();

        values.put("id_Chapter", update_chapter.getId());
        values.put("id_Novel", update_chapter.getNovel());
        values.put("Numerical", update_chapter.getSerial());
        values.put("Chapter_name", update_chapter.getName());
        values.put("Chapter_content", update_chapter.getContent());
        //noinspection SpellCheckingInspection
        values.put("dateSubmiteted", update_chapter.getDate());

        int updated_rows = database.update("Chapter", values, "id_Chapter=?", new String[]{String.valueOf(update_chapter.getId())});
        database.close();

        return updated_rows > 0;
    }
}
