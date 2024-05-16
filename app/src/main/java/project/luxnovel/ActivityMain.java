package project.luxnovel;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.Activity.ActivityLogin;

public class ActivityMain extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(ActivityMain.this, ActivityLogin.class);
        startActivity(intent);
    }
}