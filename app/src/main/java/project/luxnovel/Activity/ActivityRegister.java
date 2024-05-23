package project.luxnovel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.Database.UserHandler;
import project.luxnovel.Helper.HelperShared;
import project.luxnovel.Model.User;
import project.luxnovel.R;

public class ActivityRegister extends AppCompatActivity {
    EditText uText_aRegister_Username, uText_aRegister_Password, uText_aRegister_Email;
    TextView vText_aRegister_UsernameAnnotation, vText_aRegister_PasswordAnnotation, vText_aRegister_EmailAnnotation, vText_aRegister_Login;
    Button uButton_aRegister_Register;
    Intent intent;

    UserHandler userHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userHandler = new UserHandler(this, "Novel.db", null, 1);


        addControls();
        vText_aRegister_UsernameAnnotation.setText("");
        vText_aRegister_PasswordAnnotation.setText("");
        vText_aRegister_EmailAnnotation.setText("");

        addEvents();
        HelperShared.toggleVisibility(uText_aRegister_Password);
    }

    private void addControls() {
        uText_aRegister_Username = findViewById(R.id.uText_aRegister_Username);
        uText_aRegister_Password = findViewById(R.id.uText_aRegister_Password);
        uText_aRegister_Email = findViewById(R.id.uText_aRegister_Email);
        vText_aRegister_UsernameAnnotation = findViewById(R.id.vText_aRegister_UsernameAnnotation);
        vText_aRegister_PasswordAnnotation = findViewById(R.id.vText_aRegister_PasswordAnnotation);
        vText_aRegister_EmailAnnotation = findViewById(R.id.vText_aRegister_EmailAnnotation);
        vText_aRegister_Login = findViewById(R.id.vText_aRegister_Login);
        uButton_aRegister_Register = findViewById(R.id.uButton_aRegister_Register);
    }

    private void addEvents() {
        vText_aRegister_Login.setOnClickListener(view ->
        {
            intent = new Intent(ActivityRegister.this, ActivityLogin.class);
            startActivity(intent);
        });

        uButton_aRegister_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameLogin = uText_aRegister_Username.getText().toString();
                String password = uText_aRegister_Password.getText().toString();
                String email = uText_aRegister_Email.getText().toString();
                User user = CreateAccount();
                if (nameLogin.equals("") || password.equals("") || email.equals("")) {
                    Log.e("Thông báo", "Chưa nhập đủ thông tin");

                } else if (!isValidEmail(email)) {
                    Log.e("Thông báo", "Email không hợp lệ");
                }
                //Đầy đủ thông tin thì cho add tài khoản vào database
                else {
                    userHandler.AddAccount(user);
                    Toast.makeText(ActivityRegister.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    private User CreateAccount() {
        String account = uText_aRegister_Username.getText().toString();
        String pass = uText_aRegister_Password.getText().toString();
        String email = uText_aRegister_Email.getText().toString();
        User ac = new User(account, pass, email);
        return ac;
    }


}


