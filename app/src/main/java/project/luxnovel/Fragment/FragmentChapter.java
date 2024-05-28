package project.luxnovel.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.luxnovel.Adapter.AdapterChapter;
import project.luxnovel.Handler.HandlerChapter;
import project.luxnovel.Model.ModelChapter;
import project.luxnovel.R;

public class FragmentChapter extends Fragment
{
    View view;
    TextView vText_fChapter_Name, vText_fChapter_Content;
    ImageButton uImage_fChapter_Previous, uImage_fChapter_Next;
    Spinner uSpinner_fChapter_List;
    ArrayList<ModelChapter> chapter_list = new ArrayList<>();
    Integer chapter_position;
    String type = null;
    RequestQueue requestQueue;
    String url = "http://172.28.240.1:8080/";
    Integer chapter_id = -1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_chapter, container, false);
        requestQueue = Volley.newRequestQueue(requireContext());
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

            chapter_id = bundle.getInt("chapter_id");
            Integer novel_id = bundle.getInt("novel_id");
            try {
                type = bundle.getString("type");
            } catch (Exception e) {
                type = null;
            }

            if(type != null)
            {
                getAllDataChapter(url + "api/chapter/" + novel_id);
                return;
            }

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

    public void getAllDataChapter(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        if (response != null && !response.isEmpty()) {
                            parseJsonData(response);
                        } else {
                            Toast.makeText(getContext(), "Empty response", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "Error while parsing JSON: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("JSON", e.toString());
                    }
                },
                error -> {
                    Toast.makeText(getContext(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Volley Error", error.toString());
                });
        requestQueue.add(stringRequest);
    }

    public void parseJsonData(String response) throws JSONException {
        JSONArray objArray = new JSONArray(response);
        for (int i = 0; i < objArray.length(); i++) {
            JSONObject obj = objArray.getJSONObject(i);
            Integer id = obj.getInt("id_Chapter");
            String name = obj.getString("Chapter_name");
            String content = obj.getString("Chapter_content");
            Integer serial = obj.getInt("Numerical");
            String date = obj.getString("dateSubmitted");
            ModelChapter a = new ModelChapter(id, 0, serial, name + "--api", content, date);
            chapter_list.add(a);
        }

        ArrayList<String> string_list = new ArrayList<>();
        for(int index = 0; index < chapter_list.size(); index = index + 1)
        {
            if(chapter_list.get(index).getId().equals(chapter_id))
            {
                ModelChapter chapter = chapter_list.get(index);
                chapter_position = index;

                updateData(chapter);
            }
            string_list.add("Chapter api" + (index + 1));
        }
        ArrayAdapter<String> string_adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, string_list);
        uSpinner_fChapter_List.setAdapter(string_adapter);

        uSpinner_fChapter_List.setSelection(chapter_position);
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
        name = chapter.getName();

        vText_fChapter_Name.setText(name);
        vText_fChapter_Content.setText(chapter.getContent());
    }
}
