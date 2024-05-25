package project.luxnovel.Handler;

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

    public ArrayList<ModelChapter> loadChapter(Integer load_id)
    {
        ArrayList<ModelChapter> chapter_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Chapter where id_Novel = ?", new String[] {String.valueOf(load_id)});

        cursor.moveToFirst();
        do
        {
            ModelChapter chapter = new ModelChapter();

            chapter.setId(cursor.getInt(0));
            chapter.setNovel(cursor.getInt(1));
            chapter.setSerial(cursor.getInt(2));
            chapter.setName(cursor.getString(3));
            chapter.setContent(cursor.getString(4));
            chapter.setDate(cursor.getString(5));

            chapter_list.add(chapter);
        }
        while(cursor.moveToNext());

        cursor.close();
        database.close();
        return chapter_list;
    }
}
