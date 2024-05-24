package project.luxnovel.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import project.luxnovel.Model.ModelComment;
import project.luxnovel.R;

public class AdapterComment extends BaseAdapter
{
    LayoutInflater layout_inflater;
    Integer layout;
    ArrayList<ModelComment> comment_list;

    public AdapterComment(Activity activity, Integer layout, ArrayList<ModelComment> comment_list)
    {
        layout_inflater = activity.getLayoutInflater();
        this.layout = layout;
        this.comment_list = comment_list;
    }

    @Override
    public int getCount() {
        return comment_list.size();
    }

    @Override
    public Object getItem(int position) {
        return comment_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup view_group)
    {
        ModelComment comment = comment_list.get(position);
        @SuppressLint("ViewHolder") View item_view = layout_inflater.inflate(layout, null, false);

        TextView vText_dComment_User = item_view.findViewById(R.id.vText_dComment_User);
        vText_dComment_User.setText(comment.getUser());

        TextView vText_dComment_Rating = item_view.findViewById(R.id.vText_dComment_Rating);
        vText_dComment_Rating.setText(String.valueOf(comment.getRating()));

        TextView vText_dComment_Comment = item_view.findViewById(R.id.vText_dComment_Comment);
        vText_dComment_Comment.setText(comment.getComment());

        return item_view;
    }
}
