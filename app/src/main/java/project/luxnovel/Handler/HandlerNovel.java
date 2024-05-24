package project.luxnovel.Handler;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import project.luxnovel.Model.ModelNovel;

public class HandlerNovel extends SQLiteOpenHelper
{
    String DB_NAME, path;
    int DB_VERSION;
    Context context;

    public HandlerNovel(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
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

    public ArrayList<ModelNovel> loadNovel()
    {
        ArrayList<ModelNovel> novel_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Novel",null);

        cursor.moveToFirst();
        do
        {
            ModelNovel novel = new ModelNovel();

            novel.setId(cursor.getInt(0));
            novel.setName(cursor.getString(1));
            novel.setAuthor(cursor.getInt(2));
            novel.setCategory(cursor.getInt(3));
            novel.setDescribe(cursor.getString(4));
            novel.setState(cursor.getString(5));
            novel.setCover(cursor.getString(6));

            novel_list.add(novel);
        }
        while(cursor.moveToNext());

        cursor.close();
        database.close();

        return novel_list;
    }
    public ArrayList<ModelNovel> searchNovel(String search_name)
    {
        ArrayList<ModelNovel> novel_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Chapter where Novel_name='" + search_name + "'", null);

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                ModelNovel novel = new ModelNovel();

                novel.setId(cursor.getInt(0));
                novel.setName(cursor.getString(1));
                novel.setAuthor(cursor.getInt(2));
                novel.setCategory(cursor.getInt(3));
                novel.setDescribe(cursor.getString(4));
                novel.setState(cursor.getString(5));
                novel.setCover(cursor.getString(6));

                novel_list.add(novel);
            }
            while (cursor.moveToNext());

            cursor.close();
        }
        else new AlertDialog.Builder(context).setTitle("Announcement").setMessage("There Is No Novel With That Name").setPositiveButton(android.R.string.ok, (dialog, number) -> dialog.dismiss()).show();

        assert cursor != null;
        cursor.close();
        database.close();
        return novel_list;
    }

    public boolean insertNovel(ModelNovel insert_novel)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();

        values.put("id_Novel", insert_novel.getId());
        values.put("Novel_name", insert_novel.getName());
        values.put("id_Author", insert_novel.getAuthor());
        values.put("id_Category", insert_novel.getCategory());
        values.put("Describe", insert_novel.getDescribe());
        values.put("Novel_state", insert_novel.getState());
        values.put("Novel_img", insert_novel.getCover());

        int inserted_rows = (int) database.insert("Novel",null,values);
        database.close();

        return inserted_rows > 0;
    }

    public boolean deleteNovel(String delete_name)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String selection = "Novel_name=?";
        String[] parameter = {delete_name};

        int deleted_rows = database.delete("Novel", selection, parameter);
        database.close();

        return deleted_rows > 0;
    }

    public boolean updateNovel(ModelNovel update_novel)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();

        values.put("id_Novel", update_novel.getId());
        values.put("Novel_name", update_novel.getName());
        values.put("id_Author", update_novel.getAuthor());
        values.put("id_Category", update_novel.getCategory());
        values.put("Describe", update_novel.getDescribe());
        values.put("Novel_state", update_novel.getState());
        values.put("Novel_img", update_novel.getCover());

        int updated_rows = database.update("Novel", values, "id_Novel=?", new String[]{String.valueOf(update_novel.getId())});
        database.close();

        return updated_rows > 0;
    }
}
