package project.luxnovel.Activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import project.luxnovel.Adapter.AdapterBanner;
import project.luxnovel.Handler.HandlerNovel;
import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.Model.ModelNovel;
import project.luxnovel.R;

public class ActivityHome extends AppCompatActivity
{
    DrawerLayout lDrawer_aHome_Drawer;
    Toolbar uToolbar_aHome_Toolbar;
    NavigationView vNavigation_aHome_Navigation;
    RecyclerView vRecycler_aHome_Continue, vRecycler_aHome_Picks, vRecycler_aHome_Top, vRecycler_aHome_Favorite, vRecycler_aHome_New;
    ArrayList<ModelNovel> continue_novel, picks_novel, top_novel, favorite_novel, new_novel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addControls();
        addData();

        addAdapter(vRecycler_aHome_Continue, continue_novel);
        addAdapter(vRecycler_aHome_Picks, picks_novel);
        addAdapter(vRecycler_aHome_Top, top_novel);
        addAdapter(vRecycler_aHome_Favorite, favorite_novel);
        addAdapter(vRecycler_aHome_New, new_novel);

        addDrawer();
        addEvents();
    }

    private void addControls()
    {
        lDrawer_aHome_Drawer = findViewById(R.id.lDrawer_aHome_Drawer);
        uToolbar_aHome_Toolbar = findViewById(R.id.uToolbar_aHome_Toolbar);
        vNavigation_aHome_Navigation = findViewById(R.id.vNavigation_aHome_Navigation);
        vRecycler_aHome_Continue = findViewById(R.id.vRecycler_aHome_Continue);
        vRecycler_aHome_Picks = findViewById(R.id.vRecycler_aHome_Picks);
        vRecycler_aHome_Top = findViewById(R.id.vRecycler_aHome_Top);
        vRecycler_aHome_Favorite = findViewById(R.id.vRecycler_aHome_Favorite);
        vRecycler_aHome_New = findViewById(R.id.vRecycler_aHome_New);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aHome_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityHome.this, lDrawer_aHome_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
        HelperInterface.linkDrawer(ActivityHome.this, lDrawer_aHome_Drawer, uToolbar_aHome_Toolbar, vNavigation_aHome_Navigation);
    }

    private void addData()
    {
        continue_novel = new ArrayList<>();
        picks_novel = new ArrayList<>();
        top_novel = new ArrayList<>();
        favorite_novel = new ArrayList<>();
        new_novel = new ArrayList<>();

        //noinspection resource
        HandlerNovel novel_handler = new HandlerNovel(ActivityHome.this, "Novel.db", null, 1);
        continue_novel.addAll(novel_handler.loadNovel());
        picks_novel.addAll(novel_handler.loadNovel());
        top_novel.addAll(novel_handler.loadNovel());
        favorite_novel.addAll(novel_handler.loadNovelFavorite());
        new_novel.addAll(novel_handler.loadNovelNew());
    }

    private void addAdapter(RecyclerView recycler_view, ArrayList<ModelNovel> novel_list)
    {
        LinearLayoutManager layout_manager = new LinearLayoutManager(ActivityHome.this, LinearLayoutManager.HORIZONTAL, false);
        LinearSnapHelper snap_helper = new LinearSnapHelper();
        AdapterBanner banner_adapter = new AdapterBanner(ActivityHome.this, R.layout.adapter_banner, novel_list);
        recycler_view.setLayoutManager(layout_manager);
        recycler_view.setAdapter(banner_adapter);
        snap_helper.attachToRecyclerView(recycler_view);
    }

    private void addEvents()
    {

    }
}






