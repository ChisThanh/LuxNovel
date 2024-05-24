package project.luxnovel.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.luxnovel.Activity.ActivityRead;
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

    @SuppressLint("DiscouragedApi")
    @Override
    public void onBindViewHolder(@NonNull ViewBanner holder, int position)
    {
        ModelNovel novel = novel_list.get(position);

        holder.vImage_dBanner_Cover.setImageResource(layout_inflater.getContext().getResources().getIdentifier(novel.getCover(), "drawable", layout_inflater.getContext().getPackageName()));
        if(novel.getName().length() >= 25) holder.vText_dBanner_Name.setText(novel.getName().substring(0, 24));
        else holder.vText_dBanner_Name.setText(novel.getName());
        holder.vText_dBanner_Author.setText(String.valueOf(novel.getAuthor()));
        holder.vText_dBanner_Category.setText(String.valueOf(novel.getCategory()));
        if(novel.getDescription().length() >= 100) holder.vText_dBanner_Description.setText(novel.getDescription().substring(0, 99));
        else holder.vText_dBanner_Description.setText(novel.getDescription());

        holder.uButton_dBanner_Read.setOnClickListener(view ->
        {
            Intent intent = new Intent(layout_inflater.getContext(), ActivityRead.class);
            intent.putExtra("id", novel.getId());
            layout_inflater.getContext().startActivity(intent);
        });
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

        public ViewBanner(@NonNull View view)
        {
            super(view);
            vImage_dBanner_Cover = view.findViewById(R.id.vImage_dBanner_Cover);
            vText_dBanner_Name = view.findViewById(R.id.vText_dBanner_Name);
            vText_dBanner_Author = view.findViewById(R.id.vText_dBanner_Author);
            vText_dBanner_Category = view.findViewById(R.id.vText_dBanner_Category);
            vText_dBanner_Description = view.findViewById(R.id.vText_dBanner_Description);
            uButton_dBanner_Read = view.findViewById(R.id.uButton_dBanner_Read);
        }
    }
}
