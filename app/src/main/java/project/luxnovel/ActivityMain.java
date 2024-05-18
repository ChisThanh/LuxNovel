package project.luxnovel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Activity.ActivityLogin;

public class ActivityMain extends AppCompatActivity
{
    DrawerLayout lDrawer_aMain_Drawer;
    Toolbar uToolbar_aMain_Toolbar;
    FrameLayout lFrame_aMain_Content;
    NavigationView vNavigation_aMain_Navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addDrawer();
        addEvents();

        Intent intent = new Intent(ActivityMain.this, ActivityLogin.class);
        startActivity(intent);
    }

    private void addControls()
    {
        lDrawer_aMain_Drawer = findViewById(R.id.lDrawer_aMain_Drawer);
        uToolbar_aMain_Toolbar = findViewById(R.id.uToolbar_aMain_Toolbar);
        lFrame_aMain_Content = findViewById(R.id.lFrame_aMain_Content);
        vNavigation_aMain_Navigation = findViewById(R.id.vNavigation_aMain_Navigation);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aMain_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityMain.this, lDrawer_aMain_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
    }

    private void addEvents()
    {
        uToolbar_aMain_Toolbar.setNavigationOnClickListener(view -> lDrawer_aMain_Drawer.openDrawer(GravityCompat.START));
    }
}