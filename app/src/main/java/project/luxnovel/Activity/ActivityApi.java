package project.luxnovel.Activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.R;

public class ActivityApi extends AppCompatActivity
{
    DrawerLayout lDrawer_aApi_Drawer;
    Toolbar uToolbar_aApi_Toolbar;

    NavigationView vNavigation_aApi_Navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        addControls();
        addDrawer();
        addEvents();
    }

    private void addControls()
    {
        lDrawer_aApi_Drawer = findViewById(R.id.lDrawer_aApi_Drawer);
        uToolbar_aApi_Toolbar = findViewById(R.id.uToolbar_aApi_Toolbar);
        vNavigation_aApi_Navigation = findViewById(R.id.vNavigation_aApi_Navigation);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aApi_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityApi.this, lDrawer_aApi_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
        HelperInterface.linkDrawer(ActivityApi.this, lDrawer_aApi_Drawer, uToolbar_aApi_Toolbar, vNavigation_aApi_Navigation);
    }

    private void addEvents()
    {

    }
}
