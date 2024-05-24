package project.luxnovel.Activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.R;

public class ActivitySettings extends AppCompatActivity
{
    DrawerLayout lDrawer_aSettings_Drawer;
    Toolbar uToolbar_aSettings_Toolbar;
    NavigationView vNavigation_aSettings_Navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        addControls();
        addDrawer();
        HelperInterface.linkDrawer(ActivitySettings.this, lDrawer_aSettings_Drawer, uToolbar_aSettings_Toolbar, vNavigation_aSettings_Navigation);
        addEvents();

    }

    private void addControls()
    {
        lDrawer_aSettings_Drawer = findViewById(R.id.lDrawer_aSettings_Drawer);
        uToolbar_aSettings_Toolbar = findViewById(R.id.uToolbar_aSettings_Toolbar);
        vNavigation_aSettings_Navigation = findViewById(R.id.vNavigation_aSettings_Navigation);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aSettings_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivitySettings.this, lDrawer_aSettings_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
    }

    private void addEvents()
    {

    }
}
