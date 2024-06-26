package project.luxnovel.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.luxnovel.Model.ModelNovel;
import project.luxnovel.R;

public class AdapterDisplay extends RecyclerView.Adapter<AdapterDisplay.ViewDisplay> {
    LayoutInflater layout_inflater;
    Integer layout;
    ArrayList<ModelNovel> novel_list;
    OnItemClickListener listener;

    public AdapterDisplay(Activity activity, Integer layout, ArrayList<ModelNovel> novel_list) {
        this.layout_inflater = activity.getLayoutInflater();
        this.layout = layout;
        this.novel_list = novel_list;
    }

    @NonNull
    @Override
    public AdapterDisplay.ViewDisplay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layout_inflater.inflate(layout, null, false);
        return new AdapterDisplay.ViewDisplay(view);
    }

    @SuppressLint({"DiscouragedApi", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull AdapterDisplay.ViewDisplay holder, int position) {
        ModelNovel novel = novel_list.get(position);
        try {
            int imageResource = layout_inflater.getContext().getResources().getIdentifier(novel.getCover(), "drawable", layout_inflater.getContext().getPackageName());
            if (imageResource != 0) {
                holder.vImage_dDisplay_Cover.setImageResource(imageResource);
            } else {
                holder.vImage_dDisplay_Cover.setImageResource(R.drawable.hoahongdo);
            }
        } catch (Exception e) {
            holder.vImage_dDisplay_Cover.setImageResource(R.drawable.hoahongdo);
        }

        holder.vText_dDisplay_Name.setText(novel.getName());
    }

    public interface OnItemClickListener {
        void onItemClick(ModelNovel novel);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return novel_list.size();
    }

    public class ViewDisplay extends RecyclerView.ViewHolder {
        ImageView vImage_dDisplay_Cover;
        TextView vText_dDisplay_Name;

        public ViewDisplay(@NonNull View view) {
            super(view);

            vImage_dDisplay_Cover = view.findViewById(R.id.vImage_dDisplay_Cover);
            vText_dDisplay_Name = view.findViewById(R.id.vText_dDisplay_Name);

            view.setOnClickListener(mini_view ->
            {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClick(novel_list.get(getAdapterPosition()));
                }
            });
        }

    }
}
