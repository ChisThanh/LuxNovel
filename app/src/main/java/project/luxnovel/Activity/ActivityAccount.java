package project.luxnovel.Activity;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import project.luxnovel.Database.UserHandler;
import project.luxnovel.Helper.Auth;
import project.luxnovel.Helper.HelperShared;
import project.luxnovel.Model.User;
import project.luxnovel.R;

public class ActivityAccount extends AppCompatActivity {
    DrawerLayout lDrawer_aAccount_Drawer;
    Toolbar uToolbar_aAccount_Toolbar;
    NavigationView vNavigation_aAccount_Navigation;
    private EditText uText_aAccount_NameLogin, uText_aAccount_Password, uText_aAccount_Email, uText_aAccount_Username, uText_aAccount_Dob, uText_aAccount_Gender;
    private UserHandler userHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        userHandler = new UserHandler(this, "Novel.db", null, 1);
        addControls();
        addDrawer();

        Auth auth = Auth.getAuth();
        User userAuth = auth.getUser();

        String loggedInUserName = userAuth.getUserName();
        Toast.makeText(this, "Logged in as " + loggedInUserName, Toast.LENGTH_SHORT).show();
        if (loggedInUserName == null || loggedInUserName.isEmpty()) {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        loadUserData(loggedInUserName);
        addControls();
        addDrawer();
        HelperShared.linkDrawer(ActivityAccount.this, lDrawer_aAccount_Drawer, uToolbar_aAccount_Toolbar, vNavigation_aAccount_Navigation);
        addEvents();
    }


    private void loadUserData(String loggedInUserName) {
        ArrayList<User> users = userHandler.loadAccount(loggedInUserName);
        if (users.isEmpty()) {
            Toast.makeText(this, "No user data found", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = users.get(0);
        uText_aAccount_NameLogin.setText(user.getName_Login());
        uText_aAccount_Password.setText(user.getPassword());
        uText_aAccount_Email.setText(user.getEmail());
        uText_aAccount_Username.setText(user.getUserName());
        uText_aAccount_Dob.setText(user.getDob());
        uText_aAccount_Gender.setText(user.getGender());
    }

    private void addControls() {
        lDrawer_aAccount_Drawer = findViewById(R.id.lDrawer_aAccount_Drawer);
        uToolbar_aAccount_Toolbar = findViewById(R.id.uToolbar_aAccount_Toolbar);
        uText_aAccount_NameLogin = findViewById(R.id.uText_aAccount_NameLogin);
        uText_aAccount_Password = findViewById(R.id.uText_aAccount_Password);
        uText_aAccount_Email = findViewById(R.id.uText_aAccount_Email);
        uText_aAccount_Username = findViewById(R.id.uText_aAccount_Username);
        uText_aAccount_Dob = findViewById(R.id.uText_aAccount_Dob);
        uText_aAccount_Gender = findViewById(R.id.uText_aAccount_Gender);
        vNavigation_aAccount_Navigation = findViewById(R.id.vNavigation_aAccount_Navigation);

    }

    private void addDrawer() {
        setSupportActionBar(uToolbar_aAccount_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityAccount.this, lDrawer_aAccount_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();
    }

    private void addEvents() {

    }
}
