package project.luxnovel.Activity;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Handler.HandlerUser;
import project.luxnovel.Helper.HelperAuthentication;
import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.Model.ModelUser;
import project.luxnovel.R;

public class ActivityAccount extends AppCompatActivity
{
    DrawerLayout lDrawer_aAccount_Drawer;
    Toolbar uToolbar_aAccount_Toolbar;
    NavigationView vNavigation_aAccount_Navigation;
    EditText uText_aAccount_Username, uText_aAccount_Password, uText_aAccount_Email, uText_aAccount_Name, uText_aAccount_Dob, uText_aAccount_Gender;
    HandlerUser user_handler;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        user_handler = new HandlerUser(ActivityAccount.this, "Novel.db", null, 1);
        addControls();
        addDrawer();
        HelperInterface.linkDrawer(ActivityAccount.this, lDrawer_aAccount_Drawer, uToolbar_aAccount_Toolbar, vNavigation_aAccount_Navigation);
        HelperInterface.toggleVisibility(uText_aAccount_Password);
        addEvents();

        HelperAuthentication authentication = HelperAuthentication.getAuthentication();
        ModelUser user = authentication.getUser();

        String login_in = user.getUsername();
        Toast.makeText(ActivityAccount.this, "Login as " + login_in + "!", Toast.LENGTH_SHORT).show();

        if (login_in == null || login_in.isEmpty())
        {
            Toast.makeText(ActivityAccount.this, "No Login!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadUserData(login_in);
    }


    private void loadUserData(String login_username)
    {
        ModelUser user = user_handler.loadOneUser(login_username);
        if(user == null)
        {
            Toast.makeText(ActivityAccount.this, "Empty User Data!", Toast.LENGTH_SHORT).show();
            return;
        }

        uText_aAccount_Username.setText(user.getUsername());
        uText_aAccount_Password.setText(user.getPassword());
        uText_aAccount_Email.setText(user.getEmail());
        uText_aAccount_Name.setText(user.getName());
        uText_aAccount_Dob.setText(user.getDob());
        uText_aAccount_Gender.setText(user.getGender());
    }

    private void addControls()
    {
        lDrawer_aAccount_Drawer = findViewById(R.id.lDrawer_aAccount_Drawer);
        uToolbar_aAccount_Toolbar = findViewById(R.id.uToolbar_aAccount_Toolbar);
        uText_aAccount_Username = findViewById(R.id.uText_aAccount_Username);
        uText_aAccount_Password = findViewById(R.id.uText_aAccount_Password);
        uText_aAccount_Email = findViewById(R.id.uText_aAccount_Email);
        uText_aAccount_Name = findViewById(R.id.uText_aAccount_Name);
        uText_aAccount_Dob = findViewById(R.id.uText_aAccount_Dob);
        uText_aAccount_Gender = findViewById(R.id.uText_aAccount_Gender);
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
