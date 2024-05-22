package project.luxnovel.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.ActivityMain;
import project.luxnovel.Database.UserHandler;
import project.luxnovel.Helper.HelperShared;
import project.luxnovel.R;

public class ActivityLogin extends AppCompatActivity
{
    EditText uText_aLogin_Username, uText_aLogin_Password;
    TextView vText_aLogin_UsernameAnnotation, vText_aLogin_PasswordAnnotation, vText_aLogin_Forgot, vText_aLogin_Register;
    CheckBox uCheck_aLogin_Remember;
    Button uButton_aLogin_Login;
    Intent intent;
    UserHandler userHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userHandler = new UserHandler(this, "Novel.db", null, 1);
        addControls();
        vText_aLogin_UsernameAnnotation.setText("");
        vText_aLogin_PasswordAnnotation.setText("");

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
        uButton_aLogin_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_Login=uText_aLogin_Username.getText().toString();
                String password=uText_aLogin_Password.getText().toString();
                Cursor cursor=userHandler.getDataUser();

                while ((cursor.moveToNext())){
                    //Lấy dữ liệu và gán dữ liệu vào
                    String datanameLogin=cursor.getString(1);
                    String dataPassword=cursor.getString(2);
                    //Nhập từ edt khớp ms cho vào
                    if(datanameLogin.equals(name_Login) && dataPassword.equals(password)){
                        //int phanquyen=cursor.getInt(4);
                        int ID_User=cursor.getInt(0);
                        String email=cursor.getString(3);
                        String nameLogin=cursor.getString(1);
                        String UserName=cursor.getString(4);
                        //Chuyển qua màn main
                        Intent intent=new Intent(ActivityLogin.this, ActivityMain.class);
                        //gửi dữ liệu qua Main
                        intent.putExtra("id",ID_User);
                        intent.putExtra("email",email);
                        intent.putExtra("name_Login",nameLogin);

                        intent.putExtra("UserName",UserName);
                        startActivity(intent);
                    }
                }
                //Thực hiện trả cursor về đầu
                cursor.moveToFirst();
                cursor.close();
            }
        });
    }

}
