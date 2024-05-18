package project.luxnovel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.R;

public class ActivityForgot extends AppCompatActivity
{
    EditText uText_aForgot_Username, uText_aForgot_Email;
    TextView vText_aForgot_UsernameAnnotation, vText_aForgot_EmailAnnotation, vText_aForgot_Register;
    Button uButton_aForgot_RecoverPassword;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        addControls();
        vText_aForgot_UsernameAnnotation.setText("");
        vText_aForgot_EmailAnnotation.setText("");

        addEvents();
    }

    private void addControls()
    {
        uText_aForgot_Username = findViewById(R.id.uText_aForgot_Username);
        uText_aForgot_Email = findViewById(R.id.uText_aForgot_Email);
        vText_aForgot_UsernameAnnotation = findViewById(R.id.vText_aForgot_UsernameAnnotation);
        vText_aForgot_EmailAnnotation = findViewById(R.id.vText_aForgot_EmailAnnotation);
        uButton_aForgot_RecoverPassword = findViewById(R.id.uButton_aForgot_RecoverPassword);
        vText_aForgot_Register = findViewById(R.id.vText_aForgot_Register);
    }

    private void addEvents()
    {
        vText_aForgot_Register.setOnClickListener(view ->
        {
            intent = new Intent(ActivityForgot.this, ActivityRegister.class);
            startActivity(intent);
        });
    }
}
