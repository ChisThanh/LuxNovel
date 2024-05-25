package project.luxnovel.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import project.luxnovel.Handler.HandlerChapter;
import project.luxnovel.Model.ModelChapter;
import project.luxnovel.R;

public class FragmentChapter extends Fragment
{
    View view;
    TextView vText_fChapter_Name, vText_fChapter_Content;
    ImageButton uImage_fChapter_Previous, uImage_fChapter_Next;
    Spinner uSpinner_fChapter_List;
    ArrayList<ModelChapter> chapter_list;
    Integer chapter_position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_chapter, container, false);

        addControls();
        addData();
        addEvents();

        return view;
    }

    public void addControls()
    {
        vText_fChapter_Name = view.findViewById(R.id.vText_fChapter_Name);
        vText_fChapter_Content = view.findViewById(R.id.vText_fChapter_Content);
        uImage_fChapter_Previous = view.findViewById(R.id.uImage_fChapter_Previous);
        uImage_fChapter_Next = view.findViewById(R.id.uImage_fChapter_Next);
        uSpinner_fChapter_List = view.findViewById(R.id.uSpinner_fChapter_List);
    }

    public void addData()
    {
        Bundle bundle = getArguments();

        if(bundle != null)
        {
            Integer chapter_id = bundle.getInt("chapter_id");
            Integer novel_id = bundle.getInt("novel_id");

            //noinspection resource
            HandlerChapter chapter_hanlder = new HandlerChapter(requireContext(), "Novel.db", null, 1);

            chapter_list = chapter_hanlder.loadChapter(novel_id);
            ArrayList<String> string_list = new ArrayList<>();
            for(int index = 0; index < chapter_list.size(); index = index + 1)
            {
                if(chapter_list.get(index).getId().equals(chapter_id))
                {
                    ModelChapter chapter = chapter_list.get(index);
                    chapter_position = index;

                    updateData(chapter);
                }
                string_list.add("Chapter " + (index + 1));
            }
            ArrayAdapter<String> string_adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, string_list);
            uSpinner_fChapter_List.setAdapter(string_adapter);

            uSpinner_fChapter_List.setSelection(chapter_position);
        }
    }

    public void addEvents()
    {
        uSpinner_fChapter_List.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapter_view, View view, int position, long id)
            {
                updateData(chapter_list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter_view)
            {

            }
        });

        uImage_fChapter_Previous.setOnClickListener(view ->
        {
            if(chapter_position != 0)
            {
                chapter_position = chapter_position - 1;
                updateData(chapter_list.get(chapter_position));
                uSpinner_fChapter_List.setSelection(chapter_position);
            }
        });

        uImage_fChapter_Next.setOnClickListener(view ->
        {
            if(chapter_position != chapter_list.size() - 1)
            {
                chapter_position = chapter_position + 1;
                updateData(chapter_list.get(chapter_position));
                uSpinner_fChapter_List.setSelection(chapter_position);
            }
        });
    }

    public void updateData(ModelChapter chapter)
    {
        String name;
        if(chapter.getName().length() > 10) name = chapter.getName().substring(0, 8) + "\n" + chapter.getName().substring(10);
        else name = chapter.getName();

        vText_fChapter_Name.setText(name);
        vText_fChapter_Content.setText(chapter.getContent());
    }
}
