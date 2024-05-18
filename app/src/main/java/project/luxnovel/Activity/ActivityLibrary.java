package project.luxnovel.Activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Helper.HelperShared;
import project.luxnovel.R;

public class ActivityLibrary extends AppCompatActivity
{
    DrawerLayout lDrawer_aLibrary_Drawer;
    Toolbar uToolbar_aLibrary_Toolbar;
    NavigationView vNavigation_aLibrary_Navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        addControls();
        addDrawer();
        HelperShared.linkDrawer(ActivityLibrary.this, lDrawer_aLibrary_Drawer, uToolbar_aLibrary_Toolbar, vNavigation_aLibrary_Navigation);
        addEvents();

    }

    private void addControls()
    {
        lDrawer_aLibrary_Drawer = findViewById(R.id.lDrawer_aLibrary_Drawer);
        uToolbar_aLibrary_Toolbar = findViewById(R.id.uToolbar_aLibrary_Toolbar);
        vNavigation_aLibrary_Navigation = findViewById(R.id.vNavigation_aLibrary_Navigation);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aLibrary_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityLibrary.this, lDrawer_aLibrary_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
    }

    private void addEvents()
    {

    }
}
