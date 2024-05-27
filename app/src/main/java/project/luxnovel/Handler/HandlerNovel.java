package project.luxnovel.Handler;

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
            novel.setDescription(cursor.getString(4));
            novel.setState(cursor.getString(5));
            novel.setCover(cursor.getString(6));

            novel_list.add(novel);
        }
        while(cursor.moveToNext());

        cursor.close();
        database.close();

        return novel_list;
    }

    public ModelNovel findNovel(Integer find_id)
    {
        ModelNovel novel = new ModelNovel();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Novel where id_Novel = ?", new String[] {String.valueOf(find_id)});

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                novel.setId(cursor.getInt(0));
                novel.setName(cursor.getString(1));
                novel.setAuthor(cursor.getInt(2));
                novel.setCategory(cursor.getInt(3));
                novel.setDescription(cursor.getString(4));
                novel.setState(cursor.getString(5));
                novel.setCover(cursor.getString(6));
            }
            while (cursor.moveToNext());

            cursor.close();
        }

        assert cursor != null;
        cursor.close();
        database.close();
        return novel;
    }
    public ArrayList<ModelNovel> loadNovelNew()
    {
        ArrayList<ModelNovel> novel_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("SELECT Novel.id_Novel,Novel_name,Novel.id_Author,Novel.id_Category,Novel.Describe,Novel_state,Novel_img\n" +
                "FROM Novel JOIN Chapter\n" +
                "ON Novel.id_Novel=Chapter.id_Novel\n" +
                "GROUP BY Novel_name\n" +
                "order by dateSubmiteted DESC\n" +
                "LIMIT 5;",null);
        cursor.moveToFirst();
        do
        {
            ModelNovel novel = new ModelNovel();

            novel.setId(cursor.getInt(0));
            novel.setName(cursor.getString(1));
            novel.setAuthor(cursor.getInt(2));
            novel.setCategory(cursor.getInt(3));
            novel.setDescription(cursor.getString(4));
            novel.setState(cursor.getString(5));
            novel.setCover(cursor.getString(6));

            novel_list.add(novel);
        }
        while(cursor.moveToNext());

        cursor.close();
        database.close();

        return novel_list;
    }
    public ArrayList<ModelNovel> loadNovelFavorite() {
        ArrayList<ModelNovel> novelList = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        // Truy vấn SQL để lấy dữ liệu
        Cursor cursor = database.rawQuery(
                "SELECT n.id_Novel, n.Novel_name, n.id_Author, n.id_Category, n.Describe, n.Novel_state, n.Novel_img, AVG(r.rating) AS avg_rating " +
                        "FROM Novel n " +
                        "JOIN readState r ON n.id_Novel = r.id_Novel " +
                        "GROUP BY n.id_Novel, n.Novel_name " +
                        "ORDER BY avg_rating DESC " +
                        "LIMIT 5;", null);

        // Kiểm tra xem con trỏ có chứa dữ liệu hay không
        if (cursor.moveToFirst()) {
            do {

                ModelNovel novel = new ModelNovel();
                novel.setId(cursor.getInt(0));
                novel.setName(cursor.getString(1));
                novel.setAuthor(cursor.getInt(2));
                novel.setCategory(cursor.getInt(3));
                novel.setDescription(cursor.getString(4));
                novel.setState(cursor.getString(5));
                novel.setCover(cursor.getString(6));
                novelList.add(novel);
            } while (cursor.moveToNext());
        }

        // Đóng con trỏ và cơ sở dữ liệu
        cursor.close();
        database.close();

        return novelList;
    }

    public ArrayList<ModelNovel> filterNovelCategory(Integer filter_category)
    {
        ArrayList<ModelNovel> novel_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select  * from Novel where id_Category = ?", new String[] {String.valueOf(filter_category)});

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                ModelNovel novel = new ModelNovel();

                novel.setId(cursor.getInt(0));
                novel.setName(cursor.getString(1));
                novel.setAuthor(cursor.getInt(2));
                novel.setCategory(cursor.getInt(3));
                novel.setDescription(cursor.getString(4));
                novel.setState(cursor.getString(5));
                novel.setCover(cursor.getString(6));

                novel_list.add(novel);
            }
            while (cursor.moveToNext());

            cursor.close();
        }

        assert cursor != null;
        cursor.close();
        database.close();
        return novel_list;
    }

    public ArrayList<ModelNovel> filterNovelAuthor(Integer filter_author)
    {
        ArrayList<ModelNovel> novel_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Novel where id_Author = ?", new String[] {String.valueOf(filter_author)});

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                ModelNovel novel = new ModelNovel();

                novel.setId(cursor.getInt(0));
                novel.setName(cursor.getString(1));
                novel.setAuthor(cursor.getInt(2));
                novel.setCategory(cursor.getInt(3));
                novel.setDescription(cursor.getString(4));
                novel.setState(cursor.getString(5));
                novel.setCover(cursor.getString(6));

                novel_list.add(novel);
            }
            while (cursor.moveToNext());

            cursor.close();
        }

        assert cursor != null;
        cursor.close();
        database.close();
        return novel_list;
    }

    public ArrayList<ModelNovel> searchNovelName(String search_name)
    {
        ArrayList<ModelNovel> novel_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from Novel where Novel_name like '%" + search_name + "%'", null);

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                ModelNovel novel = new ModelNovel();

                novel.setId(cursor.getInt(0));
                novel.setName(cursor.getString(1));
                novel.setAuthor(cursor.getInt(2));
                novel.setCategory(cursor.getInt(3));
                novel.setDescription(cursor.getString(4));
                novel.setState(cursor.getString(5));
                novel.setCover(cursor.getString(6));

                novel_list.add(novel);
            }
            while (cursor.moveToNext());

            cursor.close();
        }

        assert cursor != null;
        cursor.close();
        database.close();
        return novel_list;
    }
}
