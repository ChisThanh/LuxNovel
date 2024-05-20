package project.luxnovel.Activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Helper.HelperShared;
import project.luxnovel.R;

public class ActivityAccount extends AppCompatActivity
{
    DrawerLayout lDrawer_aAccount_Drawer;
    Toolbar uToolbar_aAccount_Toolbar;
    NavigationView vNavigation_aAccount_Navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        addControls();
        addDrawer();
        HelperShared.linkDrawer(ActivityAccount.this, lDrawer_aAccount_Drawer, uToolbar_aAccount_Toolbar, vNavigation_aAccount_Navigation);
        addEvents();

    }

    private void addControls()
    {
        lDrawer_aAccount_Drawer = findViewById(R.id.lDrawer_aAccount_Drawer);
        uToolbar_aAccount_Toolbar = findViewById(R.id.uToolbar_aAccount_Toolbar);
        vNavigation_aAccount_Navigation = findViewById(R.id.vNavigation_aAccount_Navigation);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aAccount_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityAccount.this, lDrawer_aAccount_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
    }

    private void addEvents()
    {

    }
}
