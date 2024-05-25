package project.luxnovel.Handler;

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

    public ModelCategory findCategory(int find_id)
    {
        ModelCategory category = new ModelCategory();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Category where id_Category = ?", new String[] {String.valueOf(find_id)});

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                category.setId(cursor.getInt(0));
                category.setName(cursor.getString(1));

            }
            while (cursor.moveToNext());
            cursor.close();
        }

        assert cursor != null;
        cursor.close();
        database.close();
        return category;
    }
}
