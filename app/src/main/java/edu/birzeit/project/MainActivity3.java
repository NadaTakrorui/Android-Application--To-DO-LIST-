package edu.birzeit.project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import edu.birzeit.project.ui.Home2Activity;

public class MainActivity3 extends AppCompatActivity {

    private static int Splash_timeout = 7000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ActionBar action = getSupportActionBar();
        action.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashintent=new Intent(MainActivity3.this, Home2Activity.class);
                startActivity(splashintent);
                finish();
            }
        }, Splash_timeout);
        Animation myan = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.anination2);

    }
}