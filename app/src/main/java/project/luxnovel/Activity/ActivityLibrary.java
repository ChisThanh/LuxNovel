package project.luxnovel.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import project.luxnovel.Adapter.AdapterDisplay;
import project.luxnovel.Handler.HandlerCategory;
import project.luxnovel.Handler.HandlerNovel;
import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.Model.ModelCategory;
import project.luxnovel.Model.ModelNovel;
import project.luxnovel.R;

public class ActivityLibrary extends AppCompatActivity
{
    DrawerLayout lDrawer_aLibrary_Drawer;
    Toolbar uToolbar_aLibrary_Toolbar;
    TabLayout lTab_aLibrary_Category;
    RecyclerView vRecycler_aLibrary_Library;
    NavigationView vNavigation_aLibrary_Navigation;
    ArrayList<ModelCategory> category_list;
    ArrayList<ModelNovel> novel_list;
    AdapterDisplay display_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        addControls();
        addDrawer();
        addCategories();
        loadDisplay(0);
        addAdapter();
        addEvents();
    }

    private void addControls()
    {
        lDrawer_aLibrary_Drawer = findViewById(R.id.lDrawer_aLibrary_Drawer);
        uToolbar_aLibrary_Toolbar = findViewById(R.id.uToolbar_aLibrary_Toolbar);
        vNavigation_aLibrary_Navigation = findViewById(R.id.vNavigation_aLibrary_Navigation);
        lTab_aLibrary_Category = findViewById(R.id.lTab_aLibrary_Category);
        vRecycler_aLibrary_Library = findViewById(R.id.vRecycler_aLibrary_Library);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aLibrary_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityLibrary.this, lDrawer_aLibrary_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
        HelperInterface.linkDrawer(ActivityLibrary.this, lDrawer_aLibrary_Drawer, uToolbar_aLibrary_Toolbar, vNavigation_aLibrary_Navigation);
    }

    private void addCategories()
    {
        category_list = new ArrayList<>();

        //noinspection resource
        HandlerCategory category_handler = new HandlerCategory(ActivityLibrary.this, "Novel.db", null, 1);
        category_list.addAll(category_handler.loadCategory());

        lTab_aLibrary_Category.addTab(lTab_aLibrary_Category.newTab().setId(0).setText("All"));

        for(ModelCategory category : category_list)
            lTab_aLibrary_Category.addTab(lTab_aLibrary_Category.newTab().setId(category.getId()).setText(category.getName()));

        lTab_aLibrary_Category.selectTab(lTab_aLibrary_Category.getTabAt(0));
    }

    private void addAdapter()
    {
        GridLayoutManager layout_manager = new GridLayoutManager(ActivityLibrary.this, 2);
        display_adapter = new AdapterDisplay(ActivityLibrary.this, R.layout.adapter_display, novel_list);
        vRecycler_aLibrary_Library.setLayoutManager(layout_manager);
        vRecycler_aLibrary_Library.setAdapter(display_adapter);
    }

    private void loadDisplay(int category_id)
    {
        if(novel_list != null) novel_list.clear();
        else novel_list = new ArrayList<>();

        //noinspection resource
        HandlerNovel novel_hanlder = new HandlerNovel(ActivityLibrary.this, "Novel.db", null, 1);
        novel_list.addAll(novel_hanlder.loadNovel());
    }

    private void addEvents()
    {
        lTab_aLibrary_Category.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                loadDisplay(tab.getId());
                display_adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

        display_adapter.setOnItemClickListener(novel ->
        {
            Intent intent = new Intent(ActivityLibrary.this, ActivityRead.class);
            intent.putExtra("novel_id", novel.getId());
            startActivity(intent);
        });
    }
}
