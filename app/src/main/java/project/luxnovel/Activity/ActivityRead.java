package project.luxnovel.Activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.R;

public class ActivityRead extends AppCompatActivity
{
    DrawerLayout lDrawer_aRead_Drawer;
    Toolbar uToolbar_aRead_Toolbar;
    NavigationView vNavigation_aRead_Navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        addControls();
        addDrawer();
        HelperInterface.linkDrawer(ActivityRead.this, lDrawer_aRead_Drawer, uToolbar_aRead_Toolbar, vNavigation_aRead_Navigation);
        addEvents();

    }

    private void addControls()
    {
        lDrawer_aRead_Drawer = findViewById(R.id.lDrawer_aRead_Drawer);
        uToolbar_aRead_Toolbar = findViewById(R.id.uToolbar_aRead_Toolbar);
        vNavigation_aRead_Navigation = findViewById(R.id.vNavigation_aRead_Navigation);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aRead_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityRead.this, lDrawer_aRead_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
    }

    private void addEvents()
    {

    }
}
