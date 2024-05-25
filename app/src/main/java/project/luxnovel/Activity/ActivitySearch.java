package project.luxnovel.Activity;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import project.luxnovel.Adapter.AdapterDisplay;
import project.luxnovel.Handler.HandlerNovel;
import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.Model.ModelNovel;
import project.luxnovel.R;

public class ActivitySearch extends AppCompatActivity
{
    DrawerLayout lDrawer_aSearch_Drawer;
    Toolbar uToolbar_aSearch_Toolbar;
    SearchView vSearch_aSearch_Search;
    RecyclerView vRecycler_aSearch_Result;
    NavigationView vNavigation_aSearch_Navigation;
    ArrayList<ModelNovel> novel_list;
    AdapterDisplay display_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        addControls();
        addDrawer();
        addAdapter();
        addEvents();
    }

    private void addControls()
    {
        lDrawer_aSearch_Drawer = findViewById(R.id.lDrawer_aSearch_Drawer);
        uToolbar_aSearch_Toolbar = findViewById(R.id.uToolbar_aSearch_Toolbar);
        vNavigation_aSearch_Navigation = findViewById(R.id.vNavigation_aSearch_Navigation);
        vSearch_aSearch_Search = findViewById(R.id.vSearch_aSearch_Search);
        vRecycler_aSearch_Result = findViewById(R.id.vRecycler_aSearch_Result);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aSearch_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivitySearch.this, lDrawer_aSearch_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
        HelperInterface.linkDrawer(ActivitySearch.this, lDrawer_aSearch_Drawer, uToolbar_aSearch_Toolbar, vNavigation_aSearch_Navigation);
    }

    private void addAdapter()
    {
        novel_list = new ArrayList<>();
        GridLayoutManager layout_manager = new GridLayoutManager(ActivitySearch.this, 2);
        display_adapter = new AdapterDisplay(ActivitySearch.this, R.layout.adapter_display, novel_list);
        vRecycler_aSearch_Result.setLayoutManager(layout_manager);
        vRecycler_aSearch_Result.setAdapter(display_adapter);
    }

    private void addEvents()
    {
        vSearch_aSearch_Search.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                searchNovel(query);

                if(novel_list.isEmpty())
                    new AlertDialog.Builder(ActivitySearch.this, R.style.Custom_AlertDialog).setTitle("Empty").setMessage("There Is No Category With That Name").setPositiveButton("OK", (dialog, number) -> dialog.dismiss()).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String new_text)
            {
                searchNovel(new_text);
                return false;
            }
        });

        vSearch_aSearch_Search.setOnClickListener(view -> vSearch_aSearch_Search.setIconified(false));
    }

    @SuppressLint("NotifyDataSetChanged")
    private void searchNovel(String query)
    {
        //noinspection resource
        HandlerNovel novel_hanlder = new HandlerNovel(ActivitySearch.this, "Novel.db", null, 1);
        novel_list.clear();
        novel_list.addAll(novel_hanlder.searchNovel(query));
        display_adapter.notifyDataSetChanged();
    }
}
