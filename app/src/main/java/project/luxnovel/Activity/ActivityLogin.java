package project.luxnovel.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.ActivityMain;
import project.luxnovel.Database.UserHandler;
import project.luxnovel.Helper.Auth;
import project.luxnovel.Helper.HelperShared;
import project.luxnovel.Model.User;
import project.luxnovel.R;

public class ActivityLogin extends AppCompatActivity {
    EditText uText_aLogin_Username, uText_aLogin_Password;
    TextView vText_aLogin_UsernameAnnotation, vText_aLogin_PasswordAnnotation, vText_aLogin_Forgot, vText_aLogin_Register;
    CheckBox uCheck_aLogin_Remember;
    Button uButton_aLogin_Login;
    Intent intent;
    UserHandler userHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userHandler = new UserHandler(this, "Novel.db", null, 1);

        addControls();
        vText_aLogin_UsernameAnnotation.setText("");
        vText_aLogin_PasswordAnnotation.setText("");

        addEvents();
        HelperShared.toggleVisibility(uText_aLogin_Password);
    }

    private void addControls() {
        uText_aLogin_Username = findViewById(R.id.uText_aLogin_Username);
        uText_aLogin_Password = findViewById(R.id.uText_aLogin_Password);
        vText_aLogin_UsernameAnnotation = findViewById(R.id.vText_aLogin_UsernameAnnotation);
        vText_aLogin_PasswordAnnotation = findViewById(R.id.vText_aLogin_PasswordAnnotation);
        vText_aLogin_Forgot = findViewById(R.id.vText_aLogin_Forgot);
        vText_aLogin_Register = findViewById(R.id.vText_aLogin_Register);
        uCheck_aLogin_Remember = findViewById(R.id.uCheck_aLogin_Remember);
        uButton_aLogin_Login = findViewById(R.id.uButton_aLogin_Login);
    }

    private void addEvents() {
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

        uButton_aLogin_Login.setOnClickListener(v -> {
            String name_Login = uText_aLogin_Username.getText().toString();
            String password = uText_aLogin_Password.getText().toString();
            Cursor cursor = userHandler.getDataUser();

            while ((cursor.moveToNext())) {
                String datanameLogin = cursor.getString(1);
                String dataPassword = cursor.getString(2);
                if (datanameLogin.equals(name_Login) && dataPassword.equals(password)) {
                    int ID_User = cursor.getInt(0);
                    String email = cursor.getString(3);
                    String nameLogin = cursor.getString(1);
                    String UserName = cursor.getString(4);
                    Auth auth = Auth.getAuth();
                    auth.setUser(new User(UserName));
                    Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                    startActivity(intent);
                }
            }
            cursor.moveToFirst();
            cursor.close();
        });
    }


}


