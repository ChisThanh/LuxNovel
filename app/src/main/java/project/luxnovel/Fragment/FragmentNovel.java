package project.luxnovel.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import project.luxnovel.Adapter.AdapterChapter;
import project.luxnovel.Adapter.AdapterComment;
import project.luxnovel.Model.ModelChapter;
import project.luxnovel.Model.ModelComment;
import project.luxnovel.R;

public class FragmentNovel extends Fragment
{
    View view;
    ImageView vImage_fNovel_Cover;
    TextView vText_fNovel_Name, vText_fNovel_Author, vText_fNovel_Category, vText_fNovel_State, vText_fNovel_Description, vText_fNovel_Rating;
    ListView vList_fNovel_Chapter, vList_fNovel_Comment;
    ArrayList<ModelChapter> chapter_list;
    ArrayList<ModelComment> comment_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_novel, container, false);

        chapter_list = new ArrayList<>();
        comment_list = new ArrayList<>();

        addControls();
        addAdapters();
        addEvents();

        return view;
    }

    private void addControls()
    {
        vImage_fNovel_Cover = view.findViewById(R.id.vImage_fNovel_Cover);
        vText_fNovel_Name = view.findViewById(R.id.vText_fNovel_Name);
        vText_fNovel_Author = view.findViewById(R.id.vText_fNovel_Author);
        vText_fNovel_Category = view.findViewById(R.id.vText_fNovel_Category);
        vText_fNovel_State = view.findViewById(R.id.vText_fNovel_State);
        vText_fNovel_Description = view.findViewById(R.id.vText_fNovel_Description);
        vText_fNovel_Rating = view.findViewById(R.id.vText_fNovel_Rating);
        vList_fNovel_Chapter = view.findViewById(R.id.vList_fNovel_Chapter);
        vList_fNovel_Comment = view.findViewById(R.id.vList_fNovel_Comment);
    }

    private void addAdapters()
    {
        AdapterChapter chapter_adapter = new AdapterChapter(requireActivity(), R.layout.adapter_chapter, chapter_list);
        vList_fNovel_Chapter.setAdapter(chapter_adapter);

        AdapterComment comment_adapter = new AdapterComment(requireActivity(), R.layout.adapter_comment, comment_list);
        vList_fNovel_Comment.setAdapter(comment_adapter);
    }

    private void addEvents()
    {

    }
}
