package project.luxnovel.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.Handler.HandlerUser;
import project.luxnovel.Helper.HelperInterface;
import project.luxnovel.Model.ModelUser;
import project.luxnovel.R;

public class ActivityRegister extends AppCompatActivity
{
    EditText uText_aRegister_Username, uText_aRegister_Password, uText_aRegister_Email;
    TextView vText_aRegister_UsernameAnnotation, vText_aRegister_PasswordAnnotation, vText_aRegister_EmailAnnotation, vText_aRegister_Login;
    Button uButton_aRegister_Register;
    Intent intent;
    HandlerUser user_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_handler = new HandlerUser(ActivityRegister.this, "Novel.db", null, 1);

        addControls();
        vText_aRegister_UsernameAnnotation.setText("");
        vText_aRegister_PasswordAnnotation.setText("");
        vText_aRegister_EmailAnnotation.setText("");

        addEvents();
        HelperInterface.toggleVisibility(uText_aRegister_Password);
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

    @SuppressLint("SetTextI18n")
    private void addEvents()
    {
        vText_aRegister_Login.setOnClickListener(view ->
        {
            intent = new Intent(ActivityRegister.this, ActivityLogin.class);
            startActivity(intent);
        });

        uButton_aRegister_Register.setOnClickListener(view ->
        {
            boolean error = false;

            String input_username = uText_aRegister_Username.getText().toString();
            String input_password = uText_aRegister_Password.getText().toString();
            String input_email = uText_aRegister_Email.getText().toString();
            ModelUser user = CreateAccount();

            if(input_username.isEmpty())
            {
                vText_aRegister_UsernameAnnotation.setText("Missing");
                error = true;
            }
            if(input_password.isEmpty())
            {
                vText_aRegister_PasswordAnnotation.setText("Missing");
                error = true;
            }
            if(input_email.isEmpty())
            {
                vText_aRegister_EmailAnnotation.setText("Missing");
                error = true;
            }
            else if (!isValidEmail(input_email))
            {
                vText_aRegister_EmailAnnotation.setText("Wrong Email Format");
                error = true;
            }

            if(!error)
            {
                if(user_handler.insertAccount(user)) Toast.makeText(ActivityRegister.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                else Toast.makeText(ActivityRegister.this, "Register Unsuccessfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidEmail(String email)
    {
        String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(email_pattern);
    }

    private ModelUser CreateAccount()
    {
        String input_username = uText_aRegister_Username.getText().toString();
        String input_password = uText_aRegister_Password.getText().toString();
        String input_email = uText_aRegister_Email.getText().toString();

        return new ModelUser(input_username, input_password, input_email);
    }


}


