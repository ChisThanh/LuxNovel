package project.luxnovel.Handler;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import project.luxnovel.Model.ModelCategory;

public class HandlerCategory extends SQLiteOpenHelper
{
    String DB_NAME, path;
    int DB_VERSION;
    Context context;

    public HandlerCategory(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
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

    public ArrayList<ModelCategory> loadCategory()
    {
        ArrayList<ModelCategory> category_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Category",null);

        cursor.moveToFirst();
        do
        {
            ModelCategory category = new ModelCategory();

            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));

            category_list.add(category);
        }
        while(cursor.moveToNext());

        cursor.close();
        database.close();

        return category_list;
    }

    public ArrayList<ModelCategory> searchCategory(String search_name)
    {
        ArrayList<ModelCategory> category_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Category where Category_name='" + search_name + "'", null);

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                ModelCategory category = new ModelCategory();
                category.setId(cursor.getInt(0));
                category.setName(cursor.getString(1));

                category_list.add(category);
            }
            while (cursor.moveToNext());

            cursor.close();
        }
        else new AlertDialog.Builder(context).setTitle("Announcement").setMessage("There Is No Category With That Name").setPositiveButton(android.R.string.ok, (dialog, number) -> dialog.dismiss()).show();

        assert cursor != null;
        cursor.close();
        database.close();
        return category_list;
    }

    public boolean insertCategory(ModelCategory insert_category)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();

        values.put("id_Category", insert_category.getId());
        values.put("Category_name", insert_category.getName());

        int inserted_rows = (int) database.insert("Category",null,values);
        database.close();

        return inserted_rows > 0;
    }

    public boolean deleteCategory(String delete_name)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String selection = "Category_name=?";
        String[] parameter = {delete_name};

        int deleted_rows = database.delete("Category", selection, parameter);
        database.close();

        return deleted_rows > 0;
    }
    public boolean updateCategory(ModelCategory update_category)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();

        values.put("id_Author", update_category.getId());
        values.put("Author_name", update_category.getName());

        int updated_rows = database.update("Category", values, "id_Category=?", new String[]{String.valueOf(update_category.getId())});
        database.close();

        return updated_rows > 0;
    }
}
