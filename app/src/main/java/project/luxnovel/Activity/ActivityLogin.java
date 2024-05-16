package project.luxnovel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.Helper.HelperShared;
import project.luxnovel.R;

public class ActivityLogin extends AppCompatActivity
{
    EditText uText_aLogin_Username, uText_aLogin_Password;
    TextView vText_aLogin_UsernameAnnotation, vText_aLogin_PasswordAnnotation, vText_aLogin_Forgot, vText_aLogin_Register;
    CheckBox uCheck_aLogin_Remember;
    Button uButton_aLogin_Login;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addControls();
        HelperShared.removeAnnotation(vText_aLogin_UsernameAnnotation);
        HelperShared.removeAnnotation(vText_aLogin_PasswordAnnotation);

        addEvents();
        HelperShared.toggleVisibility(uText_aLogin_Password);
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
    }
}
