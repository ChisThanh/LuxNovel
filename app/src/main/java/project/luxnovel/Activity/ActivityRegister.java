package project.luxnovel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.Helper.HelperShared;
import project.luxnovel.R;

public class ActivityRegister extends AppCompatActivity
{
    EditText uText_aRegister_Username, uText_aRegister_Password, uText_aRegister_Email;
    TextView vText_aRegister_UsernameAnnotation, vText_aRegister_PasswordAnnotation, vText_aRegister_EmailAnnotation, vText_aRegister_Login;
    Button uButton_aRegister_Register;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        addControls();
        vText_aRegister_UsernameAnnotation.setText("");
        vText_aRegister_PasswordAnnotation.setText("");
        vText_aRegister_EmailAnnotation.setText("");

        addEvents();
        HelperShared.toggleVisibility(uText_aRegister_Password);
    }

    private void addControls()
    {
        uText_aRegister_Username = findViewById(R.id.uText_aRegister_Username);
        uText_aRegister_Password = findViewById(R.id.uText_aRegister_Password);
        uText_aRegister_Email = findViewById(R.id.uText_aRegister_Email);
        vText_aRegister_UsernameAnnotation = findViewById(R.id.vText_aRegister_UsernameAnnotation);
        vText_aRegister_PasswordAnnotation = findViewById(R.id.vText_aRegister_PasswordAnnotation);
        vText_aRegister_EmailAnnotation = findViewById(R.id.vText_aRegister_EmailAnnotation);
        vText_aRegister_Login = findViewById(R.id.vText_aRegister_Login);
        uButton_aRegister_Register = findViewById(R.id.uButton_aRegister_Register);
    }

    private void addEvents()
    {
        vText_aRegister_Login.setOnClickListener(view ->
        {
            intent = new Intent(ActivityRegister.this, ActivityLogin.class);
            startActivity(intent);
        });
    }
}


