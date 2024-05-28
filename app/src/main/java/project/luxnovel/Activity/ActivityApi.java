package project.luxnovel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.luxnovel.Adapter.AdapterDisplay;
import project.luxnovel.Adapter.AdapterDisplayAPI;
import project.luxnovel.Handler.HandlerNovel;
import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.Model.ModelCategory;
import project.luxnovel.Model.ModelNovel;
import project.luxnovel.Model.ModelNovelAPI;
import project.luxnovel.Model.ModelUser;
import project.luxnovel.R;

public class ActivityApi extends AppCompatActivity
{
    DrawerLayout lDrawer_aApi_Drawer;
    Toolbar uToolbar_aApi_Toolbar;
    NavigationView vNavigation_aApi_Navigation;
    RecyclerView vRecycler_aApi_Api;
    ArrayList<ModelNovel> novel_list = new ArrayList<>();
    AdapterDisplayAPI display_adapter;
    RequestQueue requestQueue;
    String url = "http://172.28.240.1:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        addControls();
        addDrawer();
        getAllDataNovel(url + "api/novels");
    }

    private void addControls()
    {
        lDrawer_aApi_Drawer = findViewById(R.id.lDrawer_aApi_Drawer);
        uToolbar_aApi_Toolbar = findViewById(R.id.uToolbar_aApi_Toolbar);
        vNavigation_aApi_Navigation = findViewById(R.id.vNavigation_aApi_Navigation);
        vRecycler_aApi_Api = findViewById(R.id.vRecycler_aAPI_API);
        requestQueue = Volley.newRequestQueue(this);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aApi_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityApi.this, lDrawer_aApi_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
        HelperInterface.linkDrawer(ActivityApi.this, lDrawer_aApi_Drawer, uToolbar_aApi_Toolbar, vNavigation_aApi_Navigation);
    }
    private void addAdapter()
    {
        GridLayoutManager layout_manager = new GridLayoutManager(ActivityApi.this, 2);
        display_adapter = new AdapterDisplayAPI(ActivityApi.this, R.layout.adapter_display, novel_list);
        vRecycler_aApi_Api.setLayoutManager(layout_manager);
        vRecycler_aApi_Api.setAdapter(display_adapter);
    }

    public void getAllDataNovel(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        if (response != null && !response.isEmpty()) {
                            parseJsonData(response);
                        } else {
                            Toast.makeText(getApplicationContext(), "Empty response", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Error while parsing JSON: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("JSON", e.toString());
                    }
                },
                error -> {
                    Toast.makeText(getApplicationContext(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Volley Error", error.toString());
                });
        requestQueue.add(stringRequest);
    }

    public void parseJsonData(String response) throws JSONException {
        JSONArray objArray = new JSONArray(response);
        for (int i = 0; i < objArray.length(); i++) {
            JSONObject obj = objArray.getJSONObject(i);
            Integer id_Novel = obj.getInt("id_Novel");
            String Novel_name = obj.getString("Novel_name");
            String Describe = obj.getString("Describe");
            String Novel_img = url + obj.getString("Novel_img") ;
            ModelNovel a = new ModelNovel(id_Novel, Novel_name, 0, 0, Describe, null, Novel_img);
            novel_list.add(a);
        }
        addAdapter();
        addEvents();
    }
    private void addEvents()
    {
        display_adapter.setOnItemClickListener(novel ->
        {
            Intent intent = new Intent(ActivityApi.this, ActivityRead.class);
            intent.putExtra("novel_id", novel.getId());
            intent.putExtra("type", "api");
            startActivity(intent);
        });
    }
}
