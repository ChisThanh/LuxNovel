package project.luxnovel.Activity;

import android.annotation.SuppressLint;
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

public class ActivityRead extends AppCompatActivity
{
    DrawerLayout lDrawer_aRead_Drawer;
    Toolbar uToolbar_aRead_Toolbar;
    FrameLayout lFrame_aRead_Content;
    NavigationView vNavigation_aRead_Navigation;

    static final String PREFS_NAME = "novel_shared_preferences";
    static final String KEY_CURRENT_ID = "shared_id";
    static final String KEY_CURRENT_TYPE = "type";
    String type = null;
    int _id = -99;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        addControls();
        addDrawer();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("novel_id")) {
            if (intent.hasExtra("type")) {
                type = intent.getStringExtra("type");
            }
            _id = intent.getIntExtra("novel_id", -1);
            if (_id != -1) {
                clearSharedPreferences();
                saveSharedPreferences(_id);
                loadNovel(_id);
                return;
            }
        }

        int save_id = getSharedPreferences();
        loadNovel(save_id);
    }

    private void saveSharedPreferences(Integer id)
    {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_CURRENT_ID, id);
        if (type != null) {
            editor.putString(KEY_CURRENT_TYPE, type);
        }
        editor.apply();
    }

    @SuppressLint("CommitPrefEdits")
    private int getSharedPreferences()
    {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        try {
            type = preferences.getString(KEY_CURRENT_TYPE, null);
        } catch (Exception e) {
            type = null;
        }
        return preferences.getInt(KEY_CURRENT_ID, -1);
    }
    private void clearSharedPreferences()
    {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(KEY_CURRENT_TYPE);
        editor.apply();
        editor.apply();
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
        HelperInterface.linkDrawer(ActivityRead.this, lDrawer_aRead_Drawer, uToolbar_aRead_Toolbar, vNavigation_aRead_Navigation);
    }

    private void loadNovel(Integer novel_id)
    {
        FragmentNovel fragment = new FragmentNovel();
        Bundle bundle = new Bundle();
        bundle.putInt("novel_id", novel_id);
        if (type != null) {
            bundle.putString("type", "api");
        }

        fragment.setArguments(bundle);

        FragmentManager fragment_manager = getSupportFragmentManager();
        FragmentTransaction fragment_transaction = fragment_manager.beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aRead_Content, fragment);
        fragment_transaction.commit();
    }
}
