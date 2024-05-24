package project.luxnovel.Activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Fragment.FragmentChapter;
import project.luxnovel.Fragment.FragmentNovel;
import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.R;

public class ActivityRead extends AppCompatActivity
{
    DrawerLayout lDrawer_aRead_Drawer;
    Toolbar uToolbar_aRead_Toolbar;
    FrameLayout lFrame_aRead_Content;
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

        loadFragment(new FragmentNovel());
        Toast.makeText(ActivityRead.this, "Open Fragment Novel", Toast.LENGTH_SHORT).show();
    }

    private void addControls()
    {
        lDrawer_aRead_Drawer = findViewById(R.id.lDrawer_aRead_Drawer);
        uToolbar_aRead_Toolbar = findViewById(R.id.uToolbar_aRead_Toolbar);
        lFrame_aRead_Content = findViewById(R.id.lFrame_aRead_Content);
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

    private void loadFragment(Fragment fragment)
    {
        FragmentManager fragment_manager = getSupportFragmentManager();
        FragmentTransaction fragment_transaction = fragment_manager.beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aRead_Content, fragment);
        fragment_transaction.commit();
    }
}
