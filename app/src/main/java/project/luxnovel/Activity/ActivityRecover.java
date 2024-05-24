package project.luxnovel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.R;

public class ActivityRecover extends AppCompatActivity
{
    EditText uText_aRecover_Username, uText_aRecover_Email;
    TextView vText_aRecover_UsernameAnnotation, vText_aRecover_EmailAnnotation, vText_aRecover_Register;
    Button uButton_aRecover_RecoverPassword;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);

        addControls();
        vText_aRecover_UsernameAnnotation.setText("");
        vText_aRecover_EmailAnnotation.setText("");

        addEvents();
    }

    private void addControls()
    {
        uText_aRecover_Username = findViewById(R.id.uText_aRecover_Username);
        uText_aRecover_Email = findViewById(R.id.uText_aRecover_Email);
        vText_aRecover_UsernameAnnotation = findViewById(R.id.vText_aRecover_UsernameAnnotation);
        vText_aRecover_EmailAnnotation = findViewById(R.id.vText_aRecover_EmailAnnotation);
        uButton_aRecover_RecoverPassword = findViewById(R.id.uButton_aRecover_RecoverPassword);
        vText_aRecover_Register = findViewById(R.id.vText_aRecover_Register);
    }

    private void addEvents()
    {
        vText_aRecover_Register.setOnClickListener(view ->
        {
            intent = new Intent(ActivityRecover.this, ActivityRegister.class);
            startActivity(intent);
        });
    }
}