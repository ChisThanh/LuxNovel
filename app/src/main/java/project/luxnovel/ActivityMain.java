package project.luxnovel;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import project.luxnovel.Activity.ActivityAccount;
import project.luxnovel.Activity.ActivityHome;
import project.luxnovel.Activity.ActivityRead;

public class ActivityMain extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(ActivityMain.this, ActivityRead.class);
        startActivity(intent);
    }
}