package project.luxnovel;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.Activity.ActivityAccount;
import project.luxnovel.Activity.ActivityHome;

public class ActivityMain extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        String nameLogin=intent.getStringExtra("name_Login");
        Intent intent1=new Intent(ActivityMain.this, ActivityAccount.class);
        intent1.putExtra("name_Login",nameLogin);
        startActivity(intent1);
        startActivity(new Intent(ActivityMain.this, ActivityHome.class));
    }
}