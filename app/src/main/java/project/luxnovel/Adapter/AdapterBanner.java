package project.luxnovel.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.luxnovel.Activity.ActivityRead;
import project.luxnovel.Handler.HandlerAuthor;
import project.luxnovel.Handler.HandlerCategory;
import project.luxnovel.Handler.HandlerState;
import project.luxnovel.Model.ModelAuthor;
import project.luxnovel.Model.ModelCategory;
import project.luxnovel.Model.ModelNovel;
import project.luxnovel.R;

public class AdapterBanner extends RecyclerView.Adapter<AdapterBanner.ViewBanner>
{
    LayoutInflater layout_inflater;
    Integer layout;
    ArrayList<ModelNovel> novel_list;

    public AdapterBanner(Activity activity, Integer layout, ArrayList<ModelNovel> novel_list)
    {
        this.layout_inflater = activity.getLayoutInflater();
        this.layout = layout;
        this.novel_list = novel_list;
    }

    @NonNull
    @Override
    public ViewBanner onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = layout_inflater.inflate(layout, null, false);
        return new ViewBanner(view);
    }

    @SuppressLint({"DiscouragedApi", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewBanner holder, int position)
    {
        ModelNovel novel = novel_list.get(position);

        try {
            int imageResource = layout_inflater.getContext().getResources().getIdentifier(novel.getCover(), "drawable", layout_inflater.getContext().getPackageName());
            if (imageResource != 0) {
                holder.vImage_dBanner_Cover.setImageResource(imageResource);
            } else {
                holder.vImage_dBanner_Cover.setImageResource(R.drawable.hoahongdo);
            }
        } catch (Exception e) {
            holder.vImage_dBanner_Cover.setImageResource(R.drawable.hoahongdo);
        }
        holder.vText_dBanner_Name.setText(novel.getName());

        Integer author_id = novel.getAuthor();
        //noinspection resource
        HandlerAuthor author_hanlder = new HandlerAuthor(layout_inflater.getContext(), "Novel.db", null, 1);
        ModelAuthor author = author_hanlder.findAuthor(author_id);
        holder.vText_dBanner_Author.setText(author.getName());

        Integer category_id = novel.getCategory();
        //noinspection resource
        HandlerCategory category_handler = new HandlerCategory(layout_inflater.getContext(), "Novel.db", null, 1);
        ModelCategory category = category_handler.findCategory(category_id);
        holder.vText_dBanner_Category.setText(category.getName());

        if(novel.getDescription().length() >= 100) holder.vText_dBanner_Description.setText(novel.getDescription().substring(0, 96) + "...") ;
        else holder.vText_dBanner_Description.setText(novel.getDescription());

        holder.uButton_dBanner_Read.setOnClickListener(view ->
        {
            Intent intent = new Intent(layout_inflater.getContext(), ActivityRead.class);
            intent.putExtra("novel_id", novel.getId());
            layout_inflater.getContext().startActivity(intent);
        });
        HandlerState handlerState = new HandlerState(layout_inflater.getContext(), "Novel.db", null, 1);
        float danhGiaTruyen= handlerState.danhGiaTruyen(novel.getId());
        holder.rb_DanhGiaTruyen.setRating(danhGiaTruyen);
    }

    @Override
    public int getItemCount()
    {
        return novel_list.size();
    }

    public static class ViewBanner extends RecyclerView.ViewHolder
    {
        ImageView vImage_dBanner_Cover;
        TextView vText_dBanner_Name, vText_dBanner_Author, vText_dBanner_Category, vText_dBanner_Description;
        Button uButton_dBanner_Read;
        RatingBar rb_DanhGiaTruyen;

        public ViewBanner(@NonNull View view)
        {
            super(view);

            rb_DanhGiaTruyen=view.findViewById(R.id.rb_DanhGiaTruyen);
            vImage_dBanner_Cover = view.findViewById(R.id.vImage_dBanner_Cover);
            vText_dBanner_Name = view.findViewById(R.id.vText_dBanner_Name);
            vText_dBanner_Author = view.findViewById(R.id.vText_dBanner_Author);
            vText_dBanner_Category = view.findViewById(R.id.vText_dBanner_Category);
            vText_dBanner_Description = view.findViewById(R.id.vText_dBanner_Description);
            uButton_dBanner_Read = view.findViewById(R.id.uButton_dBanner_Read);
        }
    }
}
