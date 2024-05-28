package project.luxnovel.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import project.luxnovel.Handler.HandlerUser;
import project.luxnovel.Helper.HelperAuthentication;
import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.Model.ModelUser;
import project.luxnovel.R;

public class ActivityWrite extends AppCompatActivity {
    DrawerLayout lDrawer_aWrite_Drawer;
    Toolbar uToolbar_aWrite_Toolbar;
    NavigationView vNavigation_aWrite_Navigation;
    TextView vText_aWrite_UsernameAnnotation, vText_aWrite_EmailAnnotation;
    EditText uText_aWrite_Username, uText_aWrite_Authorname, uText_aWrite_Email, uText_aWrite_Novelname, uText_aWrite_Chaptername, uText_aWrite_Content;
    HandlerUser user_handler;
    Button uButton_aWrite_Send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_write);
        user_handler = new HandlerUser(ActivityWrite.this, "Novel.db", null, 1);
        addControls();
        addDrawer();
        addEvents();

        HelperAuthentication authentication = HelperAuthentication.getAuthentication();
        ModelUser user = authentication.getUser();

        int login_in = user.getId();
        if (login_in == 0)
        {
            Toast.makeText(ActivityWrite.this, "No Login!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(ActivityWrite.this, "Empty User Data!", Toast.LENGTH_SHORT).show();
            return;
        }
        uText_aWrite_Username.setText(user.getUsername());
        uText_aWrite_Email.setText(user.getEmail());
    }

    private void addControls()
    {
        lDrawer_aWrite_Drawer = findViewById(R.id.lDrawer_aWrite_Drawer);
        uToolbar_aWrite_Toolbar = findViewById(R.id.uToolbar_aWrite_Toolbar);
        uText_aWrite_Username = findViewById(R.id.uText_aWrite_Username);
        uText_aWrite_Authorname = findViewById(R.id.uText_aWrite_Authorname);
        uText_aWrite_Email = findViewById(R.id.uText_aWrite_Email);
        uText_aWrite_Novelname = findViewById(R.id.uText_aWrite_Novelname);
        uText_aWrite_Chaptername = findViewById(R.id.uText_aWrite_Chapter);
        uText_aWrite_Content = findViewById(R.id.uText_aWrite_Content);
        vNavigation_aWrite_Navigation = findViewById(R.id.vNavigation_aWrite_Navigation);
        vText_aWrite_EmailAnnotation = findViewById(R.id.vText_aWrite_EmailAnnotation);
        vText_aWrite_UsernameAnnotation = findViewById(R.id.vText_aWrite_UsernameAnnotation);
        uButton_aWrite_Send = findViewById(R.id.uButton_aWrite_Send);
    }

    private void addDrawer()
    {
        setSupportActionBar(uToolbar_aWrite_Toolbar);
        ActionBarDrawerToggle action_bar_drawer_toggle = new ActionBarDrawerToggle(ActivityWrite.this, lDrawer_aWrite_Drawer, R.string.navigation_open, R.string.navigation_close);
        action_bar_drawer_toggle.syncState();

        HelperInterface.linkDrawer(ActivityWrite.this, lDrawer_aWrite_Drawer, uToolbar_aWrite_Toolbar, vNavigation_aWrite_Navigation);
        //HelperInterface.toggleVisibility(uText_aAccount_Password);
    }

    private void addEvents()
    {
        uButton_aWrite_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;

                String input_username = uText_aWrite_Username.getText().toString();
                String input_authorname = uText_aWrite_Authorname.getText().toString();
                String input_email = uText_aWrite_Email.getText().toString();
                String input_novelname=uText_aWrite_Novelname.getText().toString();
                String input_chaptername=uText_aWrite_Chaptername.getText().toString();
                String input_content=uText_aWrite_Content.getText().toString();
                //ModelUser user = RepairAccount();

                if(input_username.isEmpty())
                {
                    vText_aWrite_UsernameAnnotation.setText("Missing");
                    error = true;
                }

                if(input_email.isEmpty())
                {
                    vText_aWrite_EmailAnnotation.setText("Missing");
                    error = true;
                }
                else if (!isValidEmail(input_email))
                {
                    vText_aWrite_EmailAnnotation.setText("Wrong Email Format");
                    error = true;
                }

                if (!error) {
                sendEmail(input_username, input_authorname, input_email, input_novelname, input_chaptername, input_content);
            }
            }
        });
    }

    private void sendEmail(String username, String authorname, String email, String novelname, String chaptername, String content) {
        String subject = "Novel Submission: " + novelname + " - " + chaptername;
        String message = "Username: " + username + "\n"
                + "Author Name: " + authorname + "\n"
                + "Email: " + email + "\n"
                + "Novel Name: " + novelname + "\n"
                + "Chapter Name: " + chaptername + "\n\n"
                + "Content:\n" + content;
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        //emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"huynhvuonghuukhanhb6@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ActivityWrite.this, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isValidEmail(String email)
    {
        String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(email_pattern);
    }
}