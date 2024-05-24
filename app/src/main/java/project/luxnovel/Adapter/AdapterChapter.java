package project.luxnovel.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import project.luxnovel.Model.ModelChapter;
import project.luxnovel.R;

public class AdapterChapter extends BaseAdapter
{
    LayoutInflater layout_inflater;
    Integer layout;
    ArrayList<ModelChapter> chapter_list;

    public AdapterChapter(Activity activity, Integer layout, ArrayList<ModelChapter> chapter_list)
    {
        layout_inflater = activity.getLayoutInflater();
        this.layout = layout;
        this.chapter_list = chapter_list;
    }

    @Override
    public int getCount() {
        return chapter_list.size();
    }

    @Override
    public Object getItem(int position) {
        return chapter_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup view_group)
    {
        ModelChapter chapter = chapter_list.get(position);
        @SuppressLint("ViewHolder") View item_view = layout_inflater.inflate(layout, null, false);

        TextView vText_dChapter_Name = item_view.findViewById(R.id.vText_dChapter_Name);
        vText_dChapter_Name.setText(String.valueOf(chapter.getName()));

        TextView vText_dChapter_Serial = item_view.findViewById(R.id.vText_dChapter_Serial);
        vText_dChapter_Serial.setText(String.valueOf(chapter.getSerial()));

        TextView vText_dChapter_Date = item_view.findViewById(R.id.vText_dChapter_Date);
        vText_dChapter_Date.setText(chapter.getDate());

        return item_view;
    }
}
