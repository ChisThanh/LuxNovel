package project.luxnovel.Activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    TextView vText_aAccount_UsernameAnnotation, vText_aAccount_PasswordAnnotation, vText_aAccount_EmailAnnotation, vText_aAccount_NameAnnotation, vText_aAccount_DobAnnotation, vText_aAccount_GenderAnnotation;
    EditText uText_aAccount_Username, uText_aAccount_Password, uText_aAccount_Email, uText_aAccount_Name, uText_aAccount_Dob, uText_aAccount_Gender;
    HandlerUser user_handler;
    Button uButton_aAccount_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        user_handler = new HandlerUser(ActivityAccount.this, "Novel.db", null, 1);
        addControls();
        addDrawer();
        addEvents();

        HelperAuthentication authentication = HelperAuthentication.getAuthentication();
        ModelUser user = authentication.getUser();

        int login_in = user.getId();
        if (login_in == 0)
        {
            Toast.makeText(ActivityAccount.this, "No Login!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadUserData(login_in);
    }

    private void loadUserData(int login_username)
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
        vText_aAccount_UsernameAnnotation = findViewById(R.id.vText_aAccount_UsernameAnnotation);
        vText_aAccount_PasswordAnnotation = findViewById(R.id.vText_aAccount_PasswordAnnotation);
        vText_aAccount_EmailAnnotation = findViewById(R.id.vText_aAccount_EmailAnnotation);
        vText_aAccount_NameAnnotation = findViewById(R.id.vText_aAccount_NameAnnotation);
        vText_aAccount_DobAnnotation = findViewById(R.id.vText_aAccount_DobAnnotation);
        uButton_aAccount_Update=findViewById(R.id.uButton_aAccount_Update);
        uText_aAccount_Gender = findViewById(R.id.uText_aAccount_Gender);
        vText_aAccount_GenderAnnotation=findViewById(R.id.vText_aAccount_GenderAnnotation);
        vNavigation_aAccount_Navigation = findViewById(R.id.vNavigation_aAccount_Navigation);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aAccount_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityAccount.this, lDrawer_aAccount_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();

        HelperInterface.linkDrawer(ActivityAccount.this, lDrawer_aAccount_Drawer, uToolbar_aAccount_Toolbar, vNavigation_aAccount_Navigation);
        HelperInterface.toggleVisibility(uText_aAccount_Password);
    }

    private void addEvents()
    {
        uButton_aAccount_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;

                String input_username = uText_aAccount_Username.getText().toString();
                String input_password = uText_aAccount_Password.getText().toString();
                String input_email = uText_aAccount_Email.getText().toString();
                String input_name=uText_aAccount_Name.getText().toString();
                String input_dob=uText_aAccount_Dob.getText().toString();
                String input_gender=uText_aAccount_Gender.getText().toString();
                ModelUser user = RepairAccount();

                if(input_username.isEmpty())
                {
                    vText_aAccount_UsernameAnnotation.setText("Missing");
                    error = true;
                }
                if(input_password.isEmpty())
                {
                    vText_aAccount_PasswordAnnotation.setText("Missing");
                    error = true;
                }
                if(input_email.isEmpty())
                {
                    vText_aAccount_EmailAnnotation.setText("Missing");
                    error = true;
                }
                else if (!isValidEmail(input_email))
                {
                    vText_aAccount_EmailAnnotation.setText("Wrong Email Format");
                    error = true;
                }
                if(!input_gender.equals("Nam") && !input_gender.equals("Nữ")) {
                    error = true;
                    vText_aAccount_GenderAnnotation.setText("Giới tính phải là Nam hoặc Nữ");
                    Toast.makeText(ActivityAccount.this, "Giới tính phải là Nam hoặc Nữ", Toast.LENGTH_SHORT).show();
                }
                if(!error)
                {
                    if(user_handler.updateAccount(user)) Toast.makeText(ActivityAccount.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(ActivityAccount.this, "Update Unsuccessfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private ModelUser RepairAccount() {
        String input_username = uText_aAccount_Username.getText().toString();
        String input_password = uText_aAccount_Password.getText().toString();
        String input_email = uText_aAccount_Email.getText().toString();
        String input_name = uText_aAccount_Name.getText().toString();
        String input_dob=uText_aAccount_Dob.getText().toString();
        String input_gender=uText_aAccount_Gender.getText().toString();
        return new ModelUser(input_username, input_password, input_email,input_name,input_dob,input_gender);
    }

    private boolean isValidEmail(String email)
    {
        String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(email_pattern);
    }
}
