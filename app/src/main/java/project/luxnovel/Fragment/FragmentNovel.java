package project.luxnovel.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import project.luxnovel.Activity.ActivityLibrary;
import project.luxnovel.Activity.ActivitySearch;
import project.luxnovel.Adapter.AdapterChapter;
import project.luxnovel.Adapter.AdapterComment;
import project.luxnovel.Handler.HandlerAuthor;
import project.luxnovel.Handler.HandlerCategory;
import project.luxnovel.Handler.HandlerChapter;
import project.luxnovel.Handler.HandlerNovel;
import project.luxnovel.Model.ModelAuthor;
import project.luxnovel.Model.ModelCategory;
import project.luxnovel.Model.ModelChapter;
import project.luxnovel.Model.ModelComment;
import project.luxnovel.Model.ModelNovel;
import project.luxnovel.R;

public class FragmentNovel extends Fragment
{
    View view;
    ImageView vImage_fNovel_Cover;
    TextView vText_fNovel_Name, vText_fNovel_Author, vText_fNovel_Category, vText_fNovel_State, vText_fNovel_Description, vText_fNovel_Rating;
    ListView vList_fNovel_Chapter, vList_fNovel_Comment;
    ModelNovel novel;
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
        addData();
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

    @SuppressLint("DiscouragedApi")
    private void addData()
    {
        //noinspection resource
        HandlerNovel novel_handler = new HandlerNovel(requireContext(), "Novel.db", null, 1);
        Bundle bundle = getArguments();

        if(bundle != null)
        {
            Integer novel_id = bundle.getInt("novel_id");
            novel = novel_handler.findNovel(novel_id);

            if(novel != null)
            {
                Integer author_id = novel.getAuthor();
                //noinspection resource
                HandlerAuthor author_hanlder = new HandlerAuthor(requireContext(), "Novel.db", null, 1);
                ModelAuthor author = author_hanlder.findAuthor(author_id);

                Integer category_id = novel.getCategory();
                //noinspection resource
                HandlerCategory category_handler = new HandlerCategory(requireContext(), "Novel.db", null, 1);
                ModelCategory category = category_handler.findCategory(category_id);

                vImage_fNovel_Cover.setImageResource(requireContext().getResources().getIdentifier(novel.getCover(), "drawable", requireContext().getPackageName()));
                vText_fNovel_Name.setText(novel.getName());
                vText_fNovel_Author.setText(author.getName());
                vText_fNovel_Category.setText(category.getName());
                vText_fNovel_State.setText(novel.getState());
                vText_fNovel_Description.setText(novel.getDescription());
            }
            else new AlertDialog.Builder(requireContext(), R.style.Custom_AlertDialog)
                        .setTitle("Empty").setMessage("There Is No Novel With That ID")
                        .setPositiveButton("OK", (dialog, number) ->
                        {
                            dialog.dismiss();
                            Intent intent = new Intent(requireContext(), ActivityLibrary.class);
                            startActivity(intent);
                        }).show();
        }
    }

    private void addAdapters()
    {
        //noinspection resource
        HandlerChapter chapter_hanlder = new HandlerChapter(requireContext(), "Novel.db", null, 1);
        chapter_list = chapter_hanlder.loadChapter(novel.getId());
        AdapterChapter chapter_adapter = new AdapterChapter(requireActivity(), R.layout.adapter_chapter, chapter_list);
        vList_fNovel_Chapter.setAdapter(chapter_adapter);

        AdapterComment comment_adapter = new AdapterComment(requireActivity(), R.layout.adapter_comment, comment_list);
        vList_fNovel_Comment.setAdapter(comment_adapter);
    }

    private void addEvents()
    {

    }
}
