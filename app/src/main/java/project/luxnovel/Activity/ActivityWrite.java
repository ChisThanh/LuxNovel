package project.luxnovel.Activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Helper.HelperShared;
import project.luxnovel.R;

public class ActivityWrite extends AppCompatActivity
{
    DrawerLayout lDrawer_aWrite_Drawer;
    Toolbar uToolbar_aWrite_Toolbar;
    NavigationView vNavigation_aWrite_Navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        addControls();
        addDrawer();
        HelperShared.linkDrawer(ActivityWrite.this, lDrawer_aWrite_Drawer, uToolbar_aWrite_Toolbar, vNavigation_aWrite_Navigation);
        addEvents();

    }

    private void addControls()
    {
        lDrawer_aWrite_Drawer = findViewById(R.id.lDrawer_aWrite_Drawer);
        uToolbar_aWrite_Toolbar = findViewById(R.id.uToolbar_aWrite_Toolbar);
        vNavigation_aWrite_Navigation = findViewById(R.id.vNavigation_aWrite_Navigation);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aWrite_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityWrite.this, lDrawer_aWrite_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
    }

    private void addEvents()
    {

    }
}
