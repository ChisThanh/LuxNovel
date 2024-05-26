package project.luxnovel.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Fragment.FragmentNovel;
import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.R;

public class ActivityReadAPI extends AppCompatActivity
{
    DrawerLayout lDrawer_aRead_Drawer;
    Toolbar uToolbar_aRead_Toolbar;
    FrameLayout lFrame_aRead_Content;
    NavigationView vNavigation_aRead_Navigation;

    static final String PREFS_NAME = "novel_shared_preferences";
    static final String KEY_CURRENT_ID = "shared_id";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_api);

        addControls();
        addDrawer();

    }

    private void saveSharedPreferences(Integer id)
    {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_CURRENT_ID, id);
        editor.apply();
    }

    private int getSharedPreferences()
    {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return preferences.getInt(KEY_CURRENT_ID, -1);
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
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityReadAPI.this, lDrawer_aRead_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
        HelperInterface.linkDrawer(ActivityReadAPI.this, lDrawer_aRead_Drawer, uToolbar_aRead_Toolbar, vNavigation_aRead_Navigation);
    }

    private void loadNovel(Integer novel_id)
    {
        FragmentNovel fragment = new FragmentNovel();
        Bundle bundle = new Bundle();
        bundle.putInt("novel_id", novel_id);
        fragment.setArguments(bundle);

        FragmentManager fragment_manager = getSupportFragmentManager();
        FragmentTransaction fragment_transaction = fragment_manager.beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aRead_Content, fragment);
        fragment_transaction.commit();
    }
}
