package project.luxnovel;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.Activity.ActivityHome;

public class ActivityMain extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(ActivityMain.this, ActivityHome.class));
    }
}