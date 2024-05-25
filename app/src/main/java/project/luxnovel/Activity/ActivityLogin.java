package project.luxnovel.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
    TextView vText_aLogin_UsernameAnnotation, vText_aLogin_PasswordAnnotation, vText_aLogin_Recover, vText_aLogin_Register;
    CheckBox uCheck_aLogin_Remember;
    Button uButton_aLogin_Login;
    Intent intent;
    HandlerUser user_handler;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_handler = new HandlerUser(ActivityLogin.this, "Novel.db", null, 1);

        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        addControls();

        vText_aLogin_UsernameAnnotation.setText("");
        vText_aLogin_PasswordAnnotation.setText("");

        loadLoginData();
        addEvents();
        HelperInterface.toggleVisibility(uText_aLogin_Password);
    }
    private void loadLoginData() {
        boolean isRemembered = sharedPreferences.getBoolean("remember", false);
        if (isRemembered) {
            String savedUsername = sharedPreferences.getString("username", "");
            String savedPassword = sharedPreferences.getString("password", "");
            uText_aLogin_Username.setText(savedUsername);
            uText_aLogin_Password.setText(savedPassword);
            uCheck_aLogin_Remember.setChecked(true);
        }
    }
    private void addControls()
    {
        uText_aLogin_Username = findViewById(R.id.uText_aLogin_Username);
        uText_aLogin_Password = findViewById(R.id.uText_aLogin_Password);
        vText_aLogin_UsernameAnnotation = findViewById(R.id.vText_aLogin_UsernameAnnotation);
        vText_aLogin_PasswordAnnotation = findViewById(R.id.vText_aLogin_PasswordAnnotation);
        vText_aLogin_Recover = findViewById(R.id.vText_aLogin_Recover);
        vText_aLogin_Register = findViewById(R.id.vText_aLogin_Register);
        uCheck_aLogin_Remember = findViewById(R.id.uCheck_aLogin_Remember);
        uButton_aLogin_Login = findViewById(R.id.uButton_aLogin_Login);
    }

    @SuppressLint("SetTextI18n")
    private void addEvents() {
        vText_aLogin_Recover.setOnClickListener(view ->
        {
            intent = new Intent(ActivityLogin.this, ActivityRecover.class);
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

            if (input_username.isEmpty()) {
                vText_aLogin_UsernameAnnotation.setText("Missing");
                error = true;
            }
            if (input_password.isEmpty()) {
                vText_aLogin_PasswordAnnotation.setText("Missing");
                error = true;
            }

            if (error) return;

            Cursor cursor = user_handler.getCursorUser();
            cursor.moveToFirst();
            while ((cursor.moveToNext())) {
                String temp_username = cursor.getString(1);
                String temp_password = cursor.getString(2);

                if (temp_username.equals(input_username) && temp_password.equals(input_password)) {
                    String username = cursor.getString(1);

                    HelperAuthentication authentication = HelperAuthentication.getAuthentication();
                    authentication.setUser(new ModelUser(username));

                    if (uCheck_aLogin_Remember.isChecked()) {
                        editor.putBoolean("remember", true);
                        editor.putString("username", input_username);
                        editor.putString("password", input_password);
                        editor.apply();
                    } else {
                        editor.putBoolean("remember", false);
                        editor.remove("username");
                        editor.remove("password");
                        editor.apply();
                    }

                    cursor.close();

                    Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                    startActivity(intent);

                    break;
                }
            }

            if (cursor.isAfterLast()) {
                vText_aLogin_UsernameAnnotation.setText("Wrong Username");
                vText_aLogin_PasswordAnnotation.setText("Wrong Password");
            }
            cursor.close();
        });
    }
}


