package project.luxnovel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import project.luxnovel.Adapter.AdapterDisplay;
import project.luxnovel.Handler.HandlerAuthor;
import project.luxnovel.Handler.HandlerNovel;
import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.Model.ModelAuthor;
import project.luxnovel.Model.ModelNovel;
import project.luxnovel.R;

public class ActivityAuthor extends AppCompatActivity
{
    DrawerLayout lDrawer_aAuthor_Drawer;
    Toolbar uToolbar_aAuthor_Toolbar;
    TextView vText_aAuthor_Name;
    RecyclerView vRecycler_aAuthor_Novel;
    NavigationView vNavigation_aAuthor_Navigation;
    AdapterDisplay display_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        addControls();
        addData();
        addDrawer();
        addEvents();
    }

    private void addControls()
    {
        lDrawer_aAuthor_Drawer = findViewById(R.id.lDrawer_aAuthor_Drawer);
        uToolbar_aAuthor_Toolbar = findViewById(R.id.uToolbar_aAuthor_Toolbar);
        vText_aAuthor_Name = findViewById(R.id.vText_aAuthor_Name);
        vRecycler_aAuthor_Novel = findViewById(R.id.vRecycler_aAuthor_Novel);
        vNavigation_aAuthor_Navigation = findViewById(R.id.vNavigation_aAuthor_Navigation);
    }

    private void addData()
    {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("author_id"))
        {
            int author_id = intent.getIntExtra("author_id", -1);
            //noinspection resource
            HandlerAuthor author_hanlder = new HandlerAuthor(ActivityAuthor.this, "Novel.db", null, 1);
            ModelAuthor author = author_hanlder.findAuthor(author_id);

            vText_aAuthor_Name.setText(author.getName());

            //noinspection resource
            HandlerNovel novel_hanlder = new HandlerNovel(ActivityAuthor.this, "Novel.db", null, 1);
            ArrayList<ModelNovel> novel_list = new ArrayList<>(novel_hanlder.filterNovelAuthor(author_id));

            GridLayoutManager layout_manager = new GridLayoutManager(ActivityAuthor.this, 2);
            display_adapter = new AdapterDisplay(ActivityAuthor.this, R.layout.adapter_display, novel_list);
            vRecycler_aAuthor_Novel.setLayoutManager(layout_manager);
            vRecycler_aAuthor_Novel.setAdapter(display_adapter);
        }
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aAuthor_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityAuthor.this, lDrawer_aAuthor_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
        HelperInterface.linkDrawer(ActivityAuthor.this, lDrawer_aAuthor_Drawer, uToolbar_aAuthor_Toolbar, vNavigation_aAuthor_Navigation);
    }

    private void addEvents()
    {
        display_adapter.setOnItemClickListener(novel ->
        {
            Intent intent = new Intent(ActivityAuthor.this, ActivityRead.class);
            intent.putExtra("novel_id", novel.getId());
            startActivity(intent);
        });
    }
}
