package project.luxnovel.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import project.luxnovel.Model.ModelState;

public class HandlerState extends SQLiteOpenHelper
{
    String DB_NAME, path;
    int DB_VERSION;
    Context context;

    public HandlerState(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
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

    public ArrayList<ModelState> loadState()
    {
        ArrayList<ModelState> state_list = new ArrayList<>();
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = database.rawQuery("select * from ReadState",null);

        cursor.moveToFirst();
        do
        {
            ModelState state = new ModelState();

            state.setId(cursor.getInt(0));
            state.setUser(cursor.getInt(1));
            state.setNovel(cursor.getInt(2));
            state.setState(cursor.getString(3));
            state.setRating(cursor.getInt(4));
            state.setComment(cursor.getString(5));
            state.setTime(cursor.getString(6));

            state_list.add(state);
        }
        while(cursor.moveToNext());

        cursor.close();
        database.close();

        return state_list;
    }

    public boolean updateState(ModelState update_state)
    {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();

        values.put("id_User", update_state.getId());
        values.put("id_Novel", update_state.getNovel());
        values.put("readState", update_state.getState());
        values.put("rating", update_state.getRating());
        values.put("comment", update_state.getComment());
        values.put("evaluationTime", update_state.getTime());

        int updated_rows = database.update("ReadState", values, "id_ReadState=?", new String[]{String.valueOf(update_state.getId())});
        database.close();

        return updated_rows > 0;
    }
}
