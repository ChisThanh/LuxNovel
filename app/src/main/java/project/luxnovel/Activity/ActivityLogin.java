package project.luxnovel.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.ActivityMain;
import project.luxnovel.Handler.HandlerUser;
import project.luxnovel.Helper.HelperAuthentication;
import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.Model.ModelUser;
import project.luxnovel.R;

public class ActivityLogin extends AppCompatActivity
{
    EditText uText_aLogin_Username, uText_aLogin_Password;
    TextView vText_aLogin_UsernameAnnotation, vText_aLogin_PasswordAnnotation, vText_aLogin_Forgot, vText_aLogin_Register;
    CheckBox uCheck_aLogin_Remember;
    Button uButton_aLogin_Login;
    Intent intent;
    HandlerUser user_handler;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_handler = new HandlerUser(ActivityLogin.this, "Novel.db", null, 1);

        addControls();
        vText_aLogin_UsernameAnnotation.setText("");
        vText_aLogin_PasswordAnnotation.setText("");

        addEvents();
        HelperInterface.toggleVisibility(uText_aLogin_Password);
    }

    private void addControls()
    {
        uText_aLogin_Username = findViewById(R.id.uText_aLogin_Username);
        uText_aLogin_Password = findViewById(R.id.uText_aLogin_Password);
        vText_aLogin_UsernameAnnotation = findViewById(R.id.vText_aLogin_UsernameAnnotation);
        vText_aLogin_PasswordAnnotation = findViewById(R.id.vText_aLogin_PasswordAnnotation);
        vText_aLogin_Forgot = findViewById(R.id.vText_aLogin_Forgot);
        vText_aLogin_Register = findViewById(R.id.vText_aLogin_Register);
        uCheck_aLogin_Remember = findViewById(R.id.uCheck_aLogin_Remember);
        uButton_aLogin_Login = findViewById(R.id.uButton_aLogin_Login);
    }

    @SuppressLint("SetTextI18n")
    private void addEvents()
    {
        vText_aLogin_Forgot.setOnClickListener(view ->
        {
            intent = new Intent(ActivityLogin.this, ActivityForgot.class);
            startActivity(intent);
        });

        vText_aLogin_Register.setOnClickListener(view ->
        {
            intent = new Intent(ActivityLogin.this, ActivityRegister.class);
            startActivity(intent);
        });

        uButton_aLogin_Login.setOnClickListener(view ->
        {
            String input_username = uText_aLogin_Username.getText().toString();
            String input_password = uText_aLogin_Password.getText().toString();


            boolean error = false;

            if(input_username.isEmpty())
            {
                vText_aLogin_UsernameAnnotation.setText("Missing");
                error = true;
            }
            if(input_password.isEmpty())
            {
                vText_aLogin_PasswordAnnotation.setText("Missing");
                error = true;
            }

            if(error) return;

            Cursor cursor = user_handler.getCursorUser();
            cursor.moveToFirst();
            while ((cursor.moveToNext()))
            {
                String temp_username = cursor.getString(1);
                String temp_password = cursor.getString(2);

                if (temp_username.equals(input_username) && temp_password.equals(input_password))
                {
                    String username = cursor.getString(1);

                    HelperAuthentication authentication = HelperAuthentication.getAuthentication();
                    authentication.setUser(new ModelUser(username));

                    cursor.close();

                    Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                    startActivity(intent);

                    break;
                }
            }

            if(cursor.isAfterLast())
            {
                vText_aLogin_UsernameAnnotation.setText("Wrong Username");
                vText_aLogin_PasswordAnnotation.setText("Wrong Password");
            }
            cursor.close();
        });
    }


}


