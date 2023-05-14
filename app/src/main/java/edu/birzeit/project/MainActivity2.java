package edu.birzeit.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import edu.birzeit.project.ui.Home2Activity;

public class MainActivity2 extends AppCompatActivity {

    private static int Splash_timeout = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar action = getSupportActionBar();
        action.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashintent = new Intent(MainActivity2.this, Home2Activity.class);
                startActivity(splashintent);
                finish();
            }
        }, Splash_timeout);
        Animation myan = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.anination2);
    }
}