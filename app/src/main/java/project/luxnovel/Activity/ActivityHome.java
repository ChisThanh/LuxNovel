package project.luxnovel.Activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Helper.HelperShared;
import project.luxnovel.R;

public class ActivityHome extends AppCompatActivity
{
    DrawerLayout lDrawer_aHome_Drawer;
    Toolbar uToolbar_aHome_Toolbar;
    NavigationView vNavigation_aHome_Navigation;
    RecyclerView vRecycler_aHome_Continue, vRecycler_aHome_Picks, vRecycler_aHome_Top;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addControls();
        addDrawer();
        HelperShared.linkDrawer(ActivityHome.this, lDrawer_aHome_Drawer, uToolbar_aHome_Toolbar, vNavigation_aHome_Navigation);
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
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aHome_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityHome.this, lDrawer_aHome_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
    }

    private void addEvents()
    {

    }
}
